package com.cvent.logic.workq;

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
public class MessageWorker {

    // non-durable
    private final static String QUEUE_NAME = "hello";

    private final static String DEFAULT_DURABLE_QUEUE_NAME = "durable_task_queue1";

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
    public void performReceive(boolean durable) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = null;

        try {

            connection = factory.newConnection();

            Channel channel = connection.createChannel();

            if (durable) {
                channel.queueDeclare(DEFAULT_DURABLE_QUEUE_NAME, false, false, false, null);
            } else {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            }
            
            //dispatch to next worker that is still not busy
            int prefetchCount = 1;
            channel.basicQos(prefetchCount);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            
            if (durable) {
                channel.basicConsume(DEFAULT_DURABLE_QUEUE_NAME, true, consumer);
            } else {
                channel.basicConsume(QUEUE_NAME, true, consumer);
            }

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());

                System.out.println(" [x] Received '" + message + "'");
                doWork(message);
                System.out.println(" [x] Done");

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

            /*
             * basic way
             * while (true) {
             * QueueingConsumer.Delivery delivery = consumer.nextDelivery();
             * String message = new String(delivery.getBody());
             * System.out.println(" [x] Received '" + message + "'");
             * }
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
