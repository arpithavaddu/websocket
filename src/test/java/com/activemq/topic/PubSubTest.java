package com.activemq.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by arpitha.vaddu on 03/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PubSubTest {

    @Autowired
    private Publisher publisher;

    @Test
    public void testPubSub()
    {
        publisher.sendMessage("Hello! this is the first message on this topic");
    }
}
