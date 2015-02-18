package com.cvent.jrdw1;

import java.io.IOException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class MqInteraction {

    private final static String QUEUE_NAME = "hello";

    public MqInteraction() {

    }

    /**
     * 
     */
    public void performSend() {
        
        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel;

            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     */
    public void performReceive() {
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        Connection connection = null;
        
        try {
            
            connection = factory.newConnection();
            
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, consumer);

            while (true) {
              QueueingConsumer.Delivery delivery = consumer.nextDelivery();
              String message = new String(delivery.getBody());
              System.out.println(" [x] Received '" + message + "'");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ShutdownSignalException e) {
            e.printStackTrace();
        } catch (ConsumerCancelledException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
