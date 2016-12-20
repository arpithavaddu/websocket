package com.activemq.topic.controller;

import com.activemq.topic.QueueHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by arpitha.vaddu on 16/12/2016.
 */
@Controller
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping("/names")
    @SendTo("/topic/messages")
    public String greeting(String name) {
        logger.info("name received"+name);
        return "Hello !"+name;
    }

}
