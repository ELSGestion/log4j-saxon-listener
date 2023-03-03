package eu.els.sie.xml.saxon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.saxon.lib.StandardErrorListener;

public class Log4jStandardErrorListener extends StandardErrorListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(Log4jStandardErrorListener.class);

	public Log4jStandardErrorListener() {
		super();
		super.setLogger(new Log4jLogger(LOGGER, Log4jLoggerTypeEnum.error));
	}

}
