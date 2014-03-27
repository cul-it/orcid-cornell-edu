/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.startup;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO
 */
public class StartupListener implements ServletContextListener {
	private static final Log log = LogFactory.getLog(StartupListener.class);

	private ServletContext ctx;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ctx = sce.getServletContext();
		readSettings();
		setupLogging();
		initializeOrcidClient();
	}

	/**
	 * 
	 */
	private void readSettings() {
		// TODO Auto-generated method stub
		log.warn("StartupListener.readSettings() not implemented.");
	}

	/**
	 * 
	 */
	private void setupLogging() {
		// TODO Auto-generated method stub
		log.warn("StartupListener.setupLogging() not implemented.");
	}

	/**
	 * 
	 */
	private void initializeOrcidClient() {
		// TODO Auto-generated method stub
		log.warn("StartupListener.initializeOrcidClient() not implemented.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Nothing to tear down
	}

}
