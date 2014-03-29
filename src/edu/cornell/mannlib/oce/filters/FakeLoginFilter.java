/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.filters;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.cornell.mannlib.oce.freemarker.FreemarkerProcessor;

/**
 * TODO
 */
public class FakeLoginFilter implements Filter {
	private static final Log log = LogFactory.getLog(FakeLoginFilter.class);

	private static final String ATTRIBUTE_LOGIN_ID = FakeLoginFilter.class
			.getName() + "_loginId";
	private static final String PARAMETER_LOGIN_ID = "loginId";
	
	private static final Map<String, Object> EMPTY_BODY_MAP = Collections.emptyMap();

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Nothing to set up.
	}

	@Override
	public void destroy() {
		// Nothing to tear down.
	}

	@Override
	public void doFilter(ServletRequest rawReq, ServletResponse rawResp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) rawReq;
		HttpServletResponse resp = (HttpServletResponse) rawResp;

		if (isLoggedIn(req)) {
			chain.doFilter(new LoggedInRequest(req), resp);
		} else if (isLoggingIn(req)) {
			acceptLoginInfo(req);
			chain.doFilter(new LoggedInRequest(req), resp);
		} else {
			sendToLoginScreen(req, resp);
		}
	}

	private boolean isLoggedIn(HttpServletRequest req) {
		return req.getSession().getAttribute(ATTRIBUTE_LOGIN_ID) instanceof String;
	}

	private boolean isLoggingIn(HttpServletRequest req) {
		return req.getParameter(PARAMETER_LOGIN_ID) != null;
	}

	private void acceptLoginInfo(HttpServletRequest req) {
		req.getSession().setAttribute(ATTRIBUTE_LOGIN_ID,
				req.getParameter(PARAMETER_LOGIN_ID));
	}

	private void sendToLoginScreen(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String html = new FreemarkerProcessor(req).renderTemplate(
				"fakeLoginScreen.ftl", EMPTY_BODY_MAP);

		resp.setContentType("text/html");
		resp.getWriter().print(html);
	}

	/**
	 * This request pretends that it has an additional HTTP Header.
	 */
	private class LoggedInRequest extends HttpServletRequestWrapper {
		private final static String HEADER_NAME = "LOGIN_ID";
		private final String loginId;

		public LoggedInRequest(HttpServletRequest req) {
			super(req);
			loginId = (String) req.getSession()
					.getAttribute(ATTRIBUTE_LOGIN_ID);
		}

		@Override
		public String getHeader(String name) {
			if (HEADER_NAME.equals(name)) {
				return loginId;
			} else {
				return super.getHeader(name);
			}
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Enumeration getHeaderNames() {
			Set<String> names = new HashSet<>();
			for (Enumeration en = super.getHeaderNames(); en.hasMoreElements();) {
				names.add((String) en.nextElement());
			}
			names.add(HEADER_NAME);
			return Collections.enumeration(names);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Enumeration getHeaders(String name) {
			if (HEADER_NAME.equals(name)) {
				return Collections.enumeration(Collections.singleton(loginId));
			} else {
				return super.getHeaders(name);
			}
		}

	}
}