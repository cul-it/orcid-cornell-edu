/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.freemarker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.cornell.mannlib.oce.startup.FreemarkerSetup;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Take a template name and a body map, and process it.
 */
public class FreemarkerProcessor {
	private static final Log log = LogFactory.getLog(FreemarkerProcessor.class);

	private final Configuration configuration;

	public FreemarkerProcessor(HttpServletRequest req) {
		ServletContext ctx = req.getSession().getServletContext();
		configuration = (Configuration) ctx
				.getAttribute(FreemarkerSetup.ATTRIBUTE_NAME);
	}

	public String renderTemplate(String templateName, Map<String, Object> map)
			throws TemplateProcessingException {
		Template template = getTemplate(templateName);
		return processTemplate(template, map);
	}

	/**
	 * Fetch this template from a file and parse it. If there are any problems,
	 * return "null".
	 */
	private Template getTemplate(String templateName)
			throws TemplateProcessingException {
		Template template = null;
		try {
			template = configuration.getTemplate(templateName);
		} catch (ParseException e) {
			throw new TemplateProcessingException(
					"Failed to parse the template at '" + templateName + "'", e);
		} catch (FileNotFoundException e) {
			throw new TemplateProcessingException("No template found for '"
					+ templateName + "'", e);
		} catch (IOException e) {
			throw new TemplateProcessingException(
					"Failed to read the template at '" + templateName + "'", e);
		}
		return template;
	}

	private String processTemplate(Template template, Map<String, Object> map)
			throws TemplateProcessingException {

		StringWriter writer = new StringWriter();
		try {
			template.process(map, writer);
			return writer.toString();
		} catch (TemplateException e) {
			throw new TemplateProcessingException(
					"TemplateException creating processing environment", e);
		} catch (IOException e) {
			throw new TemplateProcessingException(
					"IOException creating processing environment", e);
		}
	}

}
