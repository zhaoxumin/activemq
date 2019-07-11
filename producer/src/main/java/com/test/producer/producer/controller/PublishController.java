package com.test.producer.producer.controller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

/**
 * @ClassName PublishController
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/7/11 17:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @RequestMapping("/queue")
    public String queue(){

        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.12.71:61616");
        try (Connection con = connectionFactory.createConnection()) {
            con.start();
            Session session=con.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            Destination destination=session.createQueue("producer") ;
            MessageProducer messageProducer=session.createProducer(destination);
            TextMessage message=session.createTextMessage("这是一个沙雕的消息");
            messageProducer.send(message);
            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 10 ; i++){
            jms.convertAndSend(queue, "queue"+i);
        }

        return "queue 发送成功";
    }

    @JmsListener(destination = "producer")
    public void consumerMsg(String msg){
        System.out.println(msg);
    }

    @RequestMapping("/topic")
    public String topic(){

        for (int i = 0; i < 10 ; i++){
            jms.convertAndSend(topic, "topic"+i);
        }

        return "topic 发送成功";
    }
}
