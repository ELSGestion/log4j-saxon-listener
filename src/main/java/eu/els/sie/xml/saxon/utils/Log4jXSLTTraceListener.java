package eu.els.sie.xml.saxon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.saxon.trace.XSLTTraceListener;

public class Log4jXSLTTraceListener extends XSLTTraceListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(Log4jXSLTTraceListener.class);
	
	public Log4jXSLTTraceListener() {
		super();
		super.setOutputDestination(new Log4jLogger(LOGGER, Log4jLoggerTypeEnum.trace));
	}

}
