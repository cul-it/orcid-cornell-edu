/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.startup;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Read the settings file. Adjust the logs as required. Initialize the
 * OrcidClientContext.
 */
public class StartupListener implements ServletContextListener {
	private static final Log log = LogFactory.getLog(StartupListener.class);

	public static final String ATTRIBUTE_NAME = StartupListener.class.getName()
			+ "/message";

	private static final String PROPERTY_NAME = "oce-properties";

	private ServletContext ctx;
	private Map<String, String> settings;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ctx = sce.getServletContext();
		try {
			readSettings();
			new LogFileAdjuster(settings).setupLogging();
			new OrcidClientSetup(settings).initialize();
			new FreemarkerSetup(ctx).initialize();
		} catch (Exception e) {
			log.error("Problem during startup", e);
			ctx.setAttribute(ATTRIBUTE_NAME, e);
		}
	}

	/**
	 * The System Property must point to a settings file, and we must be able to
	 * read it, and it must be a valid properties file.
	 */
	private void readSettings() throws IOException {
		String path = System.getProperty(PROPERTY_NAME);
		if (path == null) {
			throw new IllegalStateException("No System property for '"
					+ PROPERTY_NAME + "'");
		}

		File file = new File(path);
		if (!file.exists()) {
			throw new IllegalStateException("Settings file '" + path
					+ "' does not exist.");
		}
		if (!file.isFile()) {
			throw new IllegalStateException("Settings file '" + path
					+ "' is not a proper file.");
		}
		if (!file.canRead()) {
			throw new IllegalStateException("Cannot read settings file '"
					+ path + "'.");
		}

		Properties props = new Properties();
		props.load(new FileReader(file));

		Map<String, String> map = new HashMap<>();
		for (String name : props.stringPropertyNames()) {
			map.put(name, props.getProperty(name));
		}
		settings = Collections.unmodifiableMap(map);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Nothing to tear down
	}

}
