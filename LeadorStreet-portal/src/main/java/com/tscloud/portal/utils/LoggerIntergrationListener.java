package com.tscloud.portal.utils;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContextEvent;

/**
 * 扩展Spring的Log4jConfigListener，在容器启动的时候，桥接JDK14的输出到slf4j-logger
 */
public class LoggerIntergrationListener extends Log4jConfigListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        installJulToSlf4jBridge();
        event.getServletContext().log("Install Jdk-util-logger to slf4j success.");
        super.contextInitialized(event);
    }

    private void installJulToSlf4jBridge() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
}
