/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.startup;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;

/**
 * Create the Freemarker configuration.
 */
public class FreemarkerSetup {
	public static final String ATTRIBUTE_NAME = FreemarkerSetup.class.getName();
	private final ServletContext ctx;
	
	public FreemarkerSetup(ServletContext ctx) {
		this.ctx = ctx;
	}

	void initialize() throws IOException {
		Configuration cfg = new Configuration();
		
		String templateDir = ctx.getRealPath("/templates");
		if (templateDir == null) {
			throw new IllegalStateException("Can't resolve the path "
					+ "for the template directory: '/templates'");
		}
		cfg.setDirectoryForTemplateLoading(new File(templateDir));

		cfg.setObjectWrapper(new DefaultObjectWrapper());

		cfg.setDefaultEncoding("UTF-8");

		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		
		ctx.setAttribute(ATTRIBUTE_NAME, cfg);
	}
	
}
