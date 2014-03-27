/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO
 */
public class FakeLoginFilter implements Filter {
	private static final Log log = LogFactory.getLog(FakeLoginFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Nothing to set up.
	}

	@Override
	public void destroy() {
		// Nothing to tear down.
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.warn("FakeLoginFilter.doFilter() not implemented.");
		chain.doFilter(req, resp);
	}

}
