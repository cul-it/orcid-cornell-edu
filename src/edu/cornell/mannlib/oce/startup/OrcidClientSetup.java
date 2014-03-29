/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.startup;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.cornell.mannlib.orcidclient.OrcidClientException;
import edu.cornell.mannlib.orcidclient.context.OrcidClientContext;
import edu.cornell.mannlib.orcidclient.context.OrcidClientContext.Setting;

/**
 * Use the relevant settings to initialize the OrcidClientContext.
 */
public class OrcidClientSetup {
	private static final Log log = LogFactory.getLog(OrcidClientSetup.class);

	private final Map<String, String> settings;
	private Map<Setting, String> orcidSettings;

	OrcidClientSetup(Map<String, String> settings) {
		this.settings = settings;
	}

	public void initialize() throws OrcidClientException {
		extractOrcidSettings();
		initializeOrcidContext();
	}

	private void extractOrcidSettings() {
		Map<Setting, String> settingsMap = new HashMap<>();
		for (String name : settings.keySet()) {
			try {
				Setting key = Setting.valueOf(name);
				settingsMap.put(key, settings.get(name));
			} catch (Exception e) {
				// No problem.
				// Just encountered a setting that was not an OrcidSetting.
			}
		}
		this.orcidSettings = settingsMap;
	}

	private void initializeOrcidContext() throws OrcidClientException {
		OrcidClientContext.initialize(orcidSettings);
		log.info("Context is: " + OrcidClientContext.getInstance());
	}

}
