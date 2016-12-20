package com.activemq.topic;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by arpitha.vaddu on 03/11/2016.
 */
@Component
public class Subscriber implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            try {
                String msg = ((TextMessage) message).getText();
                System.out.println("Message consumed by Subscriber: " + msg);
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
}
