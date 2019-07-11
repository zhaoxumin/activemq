package com.test.consumer.consumer.base.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicListener
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/7/11 17:43
 * @Version 1.0
 **/
@Component
public class TopicListener {
    @JmsListener(destination = "producer.topic", containerFactory = "jmsListenerContainerTopic")
    public void receive(String text){
        System.out.println("TopicListener: consumer-a 收到一条信息: " + text);
    }
}
