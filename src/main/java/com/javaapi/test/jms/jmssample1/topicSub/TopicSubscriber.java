package com.javaapi.test.jms.jmssample1.topicSub;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicSubscriber {  
    public static void main(String[] args) throws JMSException {  
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");  
        Connection connection = factory.createConnection();  
        connection.start();  
          
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
        Topic topic = session.createTopic("myTopic.messages");  
  
        MessageConsumer consumer = session.createConsumer(topic);  
        consumer.setMessageListener(new MessageListener() {  
            public void onMessage(Message message) {  
                TextMessage tm = (TextMessage) message;  
                try {  
                    System.out.println("Received message: " + tm.getText());  
                } catch (JMSException e) {  
                    e.printStackTrace();  
                }  
            }  
        });  
        try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      session.close();  
      connection.stop();  
      connection.close();  
    }  
}  