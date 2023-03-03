# log4j-saxon-listener-dist

### Purpose ###

Deal with xsl:message/error/trace XSLT with LOG4J.THis is based on [Saxon](http://www.saxonica.com/). By default, its uses the HE edition but it is possible to use EE or PE edition juste by importing the necessary packages via the projet POM. 

### Logging  ###

```

<xsl:message select="'hello word'"/>

```

gives this output

> 2022-08-09 09:18:29,575 [pool-2-thread-1] INFO  eu.els.sie.xml.saxon.utils.Log4jMessageListenerProxy  - hello word


```

<xsl:variable name="log" as="xs:element">
  <log level="error" source="...xsl.commons.link.xsl">XML error : cannot find the linkid [A1B2C3] target</log>
</xsl:variable>
<xsl:message select="$log"/>

```

gives this output

> 2022-08-09 09:18:29,575 [pool-2-thread-1] ERROR  ...xsl.commons.link.xsl  - XML error : cannot find the linkid [A1B2C3] target


