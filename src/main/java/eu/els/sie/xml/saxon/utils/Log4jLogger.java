package eu.els.sie.xml.saxon.utils;

import net.sf.saxon.lib.StandardLogger;

public class Log4jLogger extends StandardLogger {

	private org.slf4j.Logger logger;
	private Log4jLoggerTypeEnum type;

	public Log4jLogger(org.slf4j.Logger logger, Log4jLoggerTypeEnum type) {
		this.logger = logger;
		this.type = type;
	}

	@Override
	public void println(String message, int severity) {
		switch (type) {
		case error:
			if (severity == INFO) {
				logger.info(message);
			} else if (severity == WARNING) {
				logger.warn(message);
			} else if (severity == ERROR) {
				logger.error(message);
			} else if (severity == DISASTER) {
				logger.error(message);
			}
			break;
		case trace:
			logger.trace(message);
			break;
		default:
			break;
		}
	}

}
