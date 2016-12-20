package com.activemq.topic;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(CamelRouter.class);

    @Autowired
    private CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        logger.info("Before from : new");
        from("{{inbound.orders.queue}}")
               .to("bean:queueHandler");

    }
}
