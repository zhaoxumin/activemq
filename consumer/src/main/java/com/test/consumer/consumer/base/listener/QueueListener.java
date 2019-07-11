package com.test.consumer.consumer.base.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @ClassName QueueListener
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/7/11 17:42
 * @Version 1.0
 **/
@Component
public class QueueListener {
    @JmsListener(destination = "producer", containerFactory = "jmsListenerContainerQueue")
    @SendTo("consumer")
    public String receive(String text){
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);
        return "consumer-a received : " + text;
    }
}
