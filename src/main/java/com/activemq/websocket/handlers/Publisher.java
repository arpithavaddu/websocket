package com.activemq.websocket.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by arpitha.vaddu on 03/11/2016.
 */
@Component
public class Publisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String message){
        jmsTemplate.convertAndSend(message);
    }
}
