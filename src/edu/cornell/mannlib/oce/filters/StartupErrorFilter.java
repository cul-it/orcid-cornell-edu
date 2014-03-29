/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import edu.cornell.mannlib.oce.startup.StartupListener;

/**
 * If startup detected a problem, don't allow anything except to display the
 * message.
 */
public class StartupErrorFilter implements Filter {
	private ServletContext ctx;

	@Override
	public void init(FilterConfig fc) throws ServletException {
		ctx = fc.getServletContext();
	}

	@Override
	public void destroy() {
		// Nothing to tear down.
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		Object o = ctx.getAttribute(StartupListener.ATTRIBUTE_NAME);
		if (o == null) {
			chain.doFilter(req, resp);
		} else {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html><head></head><body>");
			out.println("<h1>Problem at startup:</h1>");
			out.println("<pre>" + o + "</pre>");
			out.println("</body></html>");
		}
	}

}
