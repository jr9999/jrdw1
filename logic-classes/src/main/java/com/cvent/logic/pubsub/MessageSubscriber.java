package com.cvent.logic.pubsub;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 
 * @author jregan
 * 
 */
public class MessageSubscriber {

    /**
     * fake task to simulate execution time
     * @param task
     * @throws InterruptedException
     */
    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.')
                Thread.sleep(1000);
        }
    }

    /**
     * 
     */
    public void performReceive(String exchangeName) {

        try {

            /*
             
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
    
            Connection connection = null;
            connection = factory.newConnection();

            Channel channel = connection.createChannel();
            
            channel.exchangeDeclare(exchangeName, "fanout");
            
            String queueName = channel.queueDeclare().getQueue();
            
            System.out.println("queue name = " + queueName);
            
            channel.queueBind(queueName, exchangeName, "");
            
            //qos does not matter if all subscribers must get it
            //channel.basicQos(prefetchCount);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            QueueingConsumer consumer = new QueueingConsumer(channel);                       

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());

                System.out.println(" [x] Received '" + message + "'");
                doWork(message);
                System.out.println(" [x] Done");

                //cannot ack on subscribe model
                //channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
            */
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(exchangeName, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchangeName, "");

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, true, consumer);

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
