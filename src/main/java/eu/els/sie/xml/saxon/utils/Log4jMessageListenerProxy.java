package eu.els.sie.xml.saxon.utils;

import javax.xml.transform.SourceLocator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.saxon.s9api.Axis;
import net.sf.saxon.s9api.MessageListener2;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmNodeKind;
import net.sf.saxon.s9api.XdmSequenceIterator;

public class Log4jMessageListenerProxy implements MessageListener2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Log4jMessageListenerProxy.class);

	private static final QName SOURCE_NAME = new QName("source");
	private static final QName LEVEL_NAME = new QName("level");
	private static final QName LOG_NAME = new QName("log");

	public void message(XdmNode content) {

		XdmSequenceIterator<?> it = content.axisIterator(Axis.CHILD);

		while (it.hasNext()) {
			XdmNode xdmMessage = (XdmNode) it.next();
			String textMessage = xdmMessage.getStringValue();
			XdmNodeKind kind = xdmMessage.getNodeKind();
			switch (kind) {
			case TEXT:
				LOGGER.info(textMessage);
				break;
			case ELEMENT:
				if (xdmMessage.getNodeName().equals(LOG_NAME)) {
					try {
						String level = xdmMessage.getAttributeValue(LEVEL_NAME);
						String source = xdmMessage.getAttributeValue(SOURCE_NAME);
						Logger logger = LoggerFactory.getLogger(source);

						MessageLevelEnum levelEnum = MessageLevelEnum.valueOf(level);
						switch (levelEnum) {
						case error:
							logger.error(textMessage);
							break;
						case fatal:
							logger.error(textMessage);
							break;
						case debug:
							logger.debug(textMessage);
							break;
						case trace:
							logger.trace(textMessage);
							break;
						case info:
							logger.info(textMessage);
							break;
						case warn:
							logger.warn(textMessage);
							break;
						default:
							break;
						}
					} catch (Exception e) {
						LOGGER.error(xdmMessage.toString(), e);
					}
				} else {
					LOGGER.info(xdmMessage.toString());
				}
				break;
			default:
				LOGGER.info(xdmMessage.toString());
				break;
			}
		}
	}

	@Override
	public void message(XdmNode content, QName errorCode, boolean terminate, SourceLocator locator) {
		message(content);
	}
}
