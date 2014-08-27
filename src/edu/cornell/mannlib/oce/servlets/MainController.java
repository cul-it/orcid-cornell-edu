/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.servlets;

import static edu.cornell.mannlib.orcidclient.actions.ApiAction.AUTHENTICATE;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.cornell.mannlib.oce.freemarker.FreemarkerProcessor;
import edu.cornell.mannlib.orcidclient.OrcidClientException;
import edu.cornell.mannlib.orcidclient.auth.AuthorizationManager;
import edu.cornell.mannlib.orcidclient.auth.AuthorizationStatus;
import edu.cornell.mannlib.orcidclient.context.OrcidClientContext;
import edu.cornell.mannlib.orcidclient.context.OrcidClientContext.Setting;

/**
 * Use a fresh instance of MainControllerCore in each request. so we can use
 * instance variables without worrying about thread safety.
 */
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		new MainControllerCore(req, resp).execute();
	}

}

class MainControllerCore {
	private static final Log log = LogFactory.getLog(MainControllerCore.class);
	private static final Log successLog = LogFactory.getLog("SUCCESS");
	private static final Log problemLog = LogFactory.getLog("PROBLEM");

	private static final String PATHINFO_PROCESS = "/process";
	private static final String PATH_PROCESS = "main" + PATHINFO_PROCESS;
	private static final String PATHINFO_CALLBACK = "/callback";
	private static final String HEADER_NAME = "LOGIN_ID";

	private enum Result {
		SUCCESS, FAILED, DENIED
	}

	private final HttpServletRequest req;
	private final HttpServletResponse resp;
	private final String loginId;
	private final String pathInfo;

	private final OrcidClientContext occ;
	private final AuthorizationManager authManager;
	private final AuthorizationStatus authStatus;

	private Result result;
	private String orcidId;

	public MainControllerCore(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		this.loginId = req.getHeader(HEADER_NAME);
		this.pathInfo = req.getPathInfo();
		log.debug("NetID=" + loginId + ", pathInfo=" + pathInfo);

		this.occ = OrcidClientContext.getInstance();
		this.authManager = this.occ.getAuthorizationManager(req);
		this.authStatus = authManager.getAuthorizationStatus(AUTHENTICATE);
		log.debug("AuthStatus: " + authStatus);
	}

	public void execute() throws IOException {
		if (!isOrcidConfigured()) {
			showOrcidNotConfigured();
			return;
		}

		if (!isLoggedIn()) {
			showNotLoggedIn();
			return;
		}

		if (PATHINFO_PROCESS.equals(pathInfo)) {
			processAuthorization();
			return;
		}

		if (PATHINFO_CALLBACK.equals(pathInfo)) {
			handleCallback();
			return;
		}

		startFromScratch();
		showWelcomeScreen();
	}

	private boolean isOrcidConfigured() {
		try {
			OrcidClientContext.getInstance().getSetting(Setting.CLIENT_ID);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isLoggedIn() {
		return (loginId != null) && (!loginId.isEmpty());
	}

	private void processAuthorization() throws IOException {
		if (authStatus.isNone()) {
			// we haven't started the process yet.
			seekAuthorizationForAuthenticate();
		} else if (authStatus.isSuccess()) {
			// the user authorized us -- we win.
			showSuccess();
		} else if (authStatus.isDenied()) {
			// the user denied us -- we lose.
			showAuthorizationDenied();
		} else {
			// something funky happened -- give up.
			showAuthorizationFailed();
		}
	}

	private void handleCallback() throws IOException {
		try {
			AuthorizationStatus auth = authManager
					.processAuthorizationResponse();
			if (auth.isSuccess()) {
				resp.sendRedirect(auth.getSuccessUrl());
			} else {
				resp.sendRedirect(auth.getFailureUrl());
			}
		} catch (OrcidClientException e) {
			log.error("Invalid authorization response", e);
			resp.sendError(SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void seekAuthorizationForAuthenticate() throws IOException {
		try {
			log.debug("Seeking authorization to authenticate.");
			String returnUrl = occ.resolvePathWithWebapp(PATH_PROCESS);
			String seekUrl = authManager.seekAuthorization(AUTHENTICATE,
					returnUrl);
			resp.sendRedirect(seekUrl);
		} catch (URISyntaxException | OrcidClientException e) {
			authStatus.setFailure("Failed to seek authorization from Orcid", e);
			resp.sendRedirect(PATH_PROCESS);
		}
	}

	private void showNotLoggedIn() throws IOException {
		log.error("Not logged in! How did CUWebAuth let us through?");
		resp.sendError(SC_FORBIDDEN);
	}

	private void showOrcidNotConfigured() throws IOException {
		log.error("Orcid not configured! "
				+ "How did we get through the StartupErrorFilter?");
		resp.sendError(SC_INTERNAL_SERVER_ERROR);
	}

	private void startFromScratch() {
		authManager.clearStatus(AUTHENTICATE);
	}
	
	private void showWelcomeScreen() throws IOException {
		renderTemplate("welcomeScreen.ftl");
	}

	private void showAuthorizationDenied() throws IOException {
		result = Result.DENIED;
		problemLogMessage();
		renderTemplate("resultScreen.ftl");
	}

	private void showAuthorizationFailed() throws IOException {
		result = Result.FAILED;
		log.error("Authorization failed: authStatus=" + authStatus);
		problemLogMessage();
		renderTemplate("resultScreen.ftl");
	}

	private void showSuccess() throws IOException {
		result = Result.SUCCESS;
		orcidId = authStatus.getAccessToken().getOrcid();
		successLogMessage();
		renderTemplate("resultScreen.ftl");
	}

	private void renderTemplate(String templateName) throws IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("loginId", loginId);
		map.put("result", String.valueOf(result));
		map.put("orcidId", String.valueOf(orcidId));
		map.put("baseUrl", occ.getSetting(Setting.WEBAPP_BASE_URL));
		new FreemarkerProcessor(req, resp).renderTemplate(templateName, map);
	}

	private void successLogMessage() {
		successLog.info("result=" + result + ", loginId=" + loginId
				+ ", orcidId=" + orcidId);
	}

	private void problemLogMessage() {
		problemLog.info("result=" + result + ", loginId=" + loginId
				+ ", orcidId=" + orcidId + ", authStatus=" + authStatus);
	}

}
