/* $This file is distributed under the terms of the license in /doc/license.txt$ */

package edu.cornell.mannlib.oce.freemarker;

/**
 * Indicates a problem with the Freemarker processing.
 */
public class TemplateProcessingException extends RuntimeException {
	public TemplateProcessingException(String message) {
		super(message);
	}

	public TemplateProcessingException(String message, Throwable cause) {
		super(message, cause);
	}
}
