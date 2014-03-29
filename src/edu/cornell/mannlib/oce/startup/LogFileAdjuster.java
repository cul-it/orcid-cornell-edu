/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.startup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

/**
 * TODO
 */
public class LogFileAdjuster {
	private static final Log log = LogFactory.getLog(LogFileAdjuster.class);

	private enum SpecialLog {
		SUCCESS("logfile.success", "SuccessAppender"),

		PROBLEM("logfile.problem", "ProblemAppender");

		final String settingsKey;
		final String appenderName;

		private SpecialLog(String settingsKey, String appenderName) {
			this.settingsKey = settingsKey;
			this.appenderName = appenderName;
		}
	}

	private final Map<String, String> settings;

	LogFileAdjuster(Map<String, String> settings) {
		this.settings = settings;
	}

	/**
	 * If settings are provide for the SUCCESS and PROBLEM log files,
	 */
	void setupLogging() {
		adjustLog(SpecialLog.SUCCESS);
		adjustLog(SpecialLog.PROBLEM);
	}

	/**
	 * If they have a setting for the log file, verify that it is valid, and
	 * adjust the logger to write to it.
	 */
	private void adjustLog(SpecialLog specialLog) {
		try {
			String logFile = getLogFileSetting(specialLog);
			validateLogFileSetting(specialLog, logFile);

			FileAppender fileAppender = locateAppender(specialLog);

			fileAppender.setFile(logFile);
			fileAppender.activateOptions();

			log.info("Redirected " + specialLog + " logger to '" + logFile
					+ "'");
			return;
		} catch (LogAdjustmentException e) {
			if (e.getCause() == null) {
				log.warn(e.getMessage());
			} else {
				log.warn(e.getMessage(), e.getCause());
			}
		}
	}

	/**
	 * Get the log file setting, or complain if it's not there.
	 */
	private String getLogFileSetting(SpecialLog specialLog)
			throws LogAdjustmentException {
		String setting = settings.get(specialLog.settingsKey);
		if (setting == null) {
			throw new LogAdjustmentException("No setting for the " + specialLog
					+ " log file, '" + specialLog.settingsKey + "'");
		}
		return setting;
	}

	/**
	 * Confirm that the log file will go into an existing directory, and that
	 * either it does exist, or we can create it. Should also be writeable.
	 */
	private void validateLogFileSetting(SpecialLog specialLog, String logFile)
			throws LogAdjustmentException {
		Path path = Paths.get(logFile);
		if (!Files.exists(path.getParent())) {
			throw new LogAdjustmentException("Cannot create " + specialLog
					+ " log file '" + logFile
					+ "', parent directory does not exist.");
		}

		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				throw new LogAdjustmentException("Failed to create "
						+ specialLog + " log file '" + path + "'", e);
			}
		}

		if (!Files.isWritable(path)) {
			throw new LogAdjustmentException("Cannot write to log file '"
					+ logFile + "' for " + specialLog + " log.");
		}
	}

	/**
	 * Locate the logger for this category and find the expected Appender. If
	 * it's not there, or is not a FileAppender, complain.
	 */
	private FileAppender locateAppender(SpecialLog specialLog)
			throws LogAdjustmentException {
		Logger logger = Logger.getLogger(specialLog.name());

		Appender appender = logger.getAppender(specialLog.appenderName);
		if (appender == null) {
			throw new LogAdjustmentException("Failed to redirect logger "
					+ "for category  " + specialLog
					+ ". Logger does not have an appender named '"
					+ specialLog.appenderName + "'.");
		}

		if (!(appender instanceof FileAppender)) {
			throw new LogAdjustmentException(
					"Can't redirect logger for category  " + specialLog
							+ ". Appender '" + specialLog.appenderName
							+ "' is not a FileAppender.");
		}

		FileAppender fileAppender = (FileAppender) appender;
		return fileAppender;
	}

	/**
	 * Indicates a problem with adjusting one of the special logs.
	 */
	private static class LogAdjustmentException extends Exception {
		LogAdjustmentException(String message) {
			super(message);
		}

		LogAdjustmentException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
