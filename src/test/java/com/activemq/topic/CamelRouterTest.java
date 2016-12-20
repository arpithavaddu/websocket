//package com.activemq.topic;//package co.uk.sainsburys.order.consumer;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.camel.Exchange;
//import org.apache.camel.Predicate;
//import org.apache.camel.Processor;
//import org.apache.camel.ProducerTemplate;
//import org.apache.camel.builder.NotifyBuilder;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.component.jms.JmsComponent;
//import org.apache.camel.component.mock.MockEndpoint;
//import org.apache.camel.impl.JndiRegistry;
//import org.apache.camel.spi.BrowsableEndpoint;
//import org.apache.camel.spring.spi.SpringTransactionPolicy;
//import org.apache.camel.test.junit4.CamelTestSupport;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.jms.connection.JmsTransactionManager;
//
//import javax.jms.ConnectionFactory;
//import java.util.List;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by arpitha.vaddu on 23/11/2016.
// */
////@RunWith(SpringRunner.class)
////@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//public class CamelRouterTest extends CamelTestSupport {
//
//    private ProducerTemplate producerTemplate;
//
//
//    @Override
//    protected Properties useOverridePropertiesWithPropertiesComponent() {
//        Properties extra = new Properties();
//        extra.put("inbound.orders.queue", "jms:queue:OrdersTest");
//        extra.put("outbound.orders.topic", "jms:topic:CounterTopic");
//        extra.put("inbound.counter.queue", "jms:queue:CounterQueue");
//        return extra;
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        super.tearDown();
//    }
//
//    @Override
//    protected JndiRegistry createRegistry() throws Exception {
//        JndiRegistry reg = super.createRegistry();
//
//        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
//        jmsTransactionManager.setRollbackOnCommitFailure(true);
//
//        SpringTransactionPolicy txPolicy = new SpringTransactionPolicy();
//        txPolicy.setTransactionManager(jmsTransactionManager);
//        txPolicy.setPropagationBehaviorName("PROPAGATION_REQUIRED");
//        reg.bind("requiredTransactionPolicy", txPolicy);
//
//        return reg;
//    }
//
//    @Override
//    public boolean isUseAdviceWith() {
//        return true;
//    }
//
//
////    @Test
////    public void testAdvised() throws Exception {
////
////        // advice the first route using the inlined route builder
////        context.getRouteDefinition("OrdersToDBToTopic").adviceWith(context, new RouteBuilder() {
////            @Override
////            public void configure() throws Exception {
////                // intercept sending to mock:foo and do something else
////                interceptSendToEndpoint("{{inbound.orders.queue}}")
////                        .skipSendToOriginalEndpoint()
////                        .to("mock:catchOrderMessages");
////            }
////        });
////
////        Mockito.doThrow(DatabaseException.class).when(customerOrderIngestorService).ingestOrder(any(String.class));
////        getMockEndpoint("mock:catchOrderMessages").expectedMessageCount(1);
////        //getMockEndpoint("mock:advised").expectedMessageCount(1);
////        //getMockEndpoint("mock:result").expectedMessageCount(1);
////        context.start();
////
////        template.sendBody("{{inbound.orders.queue}}", new OrderReader().retrieveOrderJson());
////        context.stop();
////
////        assertMockEndpointsSatisfied();
////    }
//
//    @Test
//    public void testTransaction() throws Exception {
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//        context.addComponent("jms", JmsComponent.jmsComponentTransacted(connectionFactory));
//        Predicate predicate = new Predicate() {
//            @Override
//            public boolean matches(Exchange exchange) {
//
//                return exchange.getOut().getHeader("saved").equals(Boolean.TRUE);
//            }
//        };
//        context.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from("{{inbound.orders.queue}}")
//                        .transacted("requiredTransactionPolicy")
//                        .process(new Processor() {
//                            @Override
//                            public void process(Exchange exchange) throws Exception {
//                                System.out.println("Expected failure");
//                                exchange.getOut().setHeader("saved",Boolean.TRUE);
//                            }
//                        })
//                        .filter(predicate)
//                        .to("mock:result");
//            }
//        });
//        context.start();
//
//        MockEndpoint result = context.getEndpoint("mock:result", MockEndpoint.class);
//        result.expectedMessageCount(1);
//
//        NotifyBuilder notifyBuilder = new NotifyBuilder(context).whenDone(1).create();
//        context.createProducerTemplate().sendBody("{{inbound.orders.queue}}", "new order arrived");
//
//        boolean matches = notifyBuilder.matches(5, TimeUnit.SECONDS);
//        assertTrue(matches);
//
//        Thread.sleep(1000);
//        assertMockEndpointsSatisfied();
//
////        BrowsableEndpoint in = context.getEndpoint("{{inbound.orders.queue}}", BrowsableEndpoint.class);
////        List<Exchange> list = in.getExchanges();
////        assertEquals(1, list.size());
////        String body = list.get(0).getIn().getBody(String.class);
////        assertEquals("Test", body);
//
//        context.stop();
//    }
//}
