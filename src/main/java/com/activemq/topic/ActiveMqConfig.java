package com.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * Created by arpitha.vaddu on 03/11/2016.
 */
@Configuration
public class ActiveMqConfig {

    @Value("${topic.name}")
    private String topic;


    @Bean
    public ActiveMQTopic activeMQTopic()
    {
        return new ActiveMQTopic(topic);
    }

    @Bean
    public JmsTemplate jmsTemplate(final ConnectionFactory connectionFactory)
    {
       JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setDefaultDestination(activeMQTopic());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

    @Bean
    public JmsTransactionManager jmsTransactionManager(final ConnectionFactory connectionFactory){
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(connectionFactory);
        return jmsTransactionManager;
    }

    @Bean
    public JmsComponent jmsComponent(final ConnectionFactory connectionFactory,final JmsTransactionManager jmsTransactionManager){
        JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory,jmsTransactionManager);
        jmsComponent.setMaxConcurrentConsumers(2);
        return jmsComponent;
    }

//    @Bean
//    public Subscriber subscriber()
//    {
//        return new Subscriber();
//    }
//    @Bean
//    public DefaultMessageListenerContainer defaultMessageListenerContainer(final ConnectionFactory connectionFactory)
//    {
//        DefaultMessageListenerContainer defaultMessageListenerContainer= new DefaultMessageListenerContainer();
//        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
//        defaultMessageListenerContainer.setPubSubDomain(true);
//        defaultMessageListenerContainer.setDestinationName(topic);
//        defaultMessageListenerContainer.setMessageListener(subscriber());
//        return defaultMessageListenerContainer;
//    }
}
