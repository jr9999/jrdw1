package com.cvent.logic;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;


public class MessageWorker {
    
    private final static String QUEUE_NAME = "hello";
    
    // fake task to simulate execution time
    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
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
                doWork(message);
                System.out.println(" [x] Done");
            }

            /*basic way
            while (true) {
              QueueingConsumer.Delivery delivery = consumer.nextDelivery();
              String message = new String(delivery.getBody());
              System.out.println(" [x] Received '" + message + "'");
            }
            */
            
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
