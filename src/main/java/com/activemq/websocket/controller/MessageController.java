package com.activemq.websocket.controller;

import com.activemq.websocket.NameMessage;
import com.activemq.websocket.handlers.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by arpitha.vaddu on 16/12/2016.
 */
@Controller
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private Publisher publisher;

    @MessageMapping("/names")
    @SendTo("/topic/messages")
    public String greeting(NameMessage name) {
        logger.info("name received"+name.getName());
        publisher.sendMessage("Hello !"+name.getName());
        return "Hello !"+name.getName();
    }

}
