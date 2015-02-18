package com.cvent.logic.workq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * 
 * @author jregan
 *
 */
public class MessageCreator {

    private final static String QUEUE_NAME = "hello";

    private final static String DEFAULT_DURABLE_QUEUE_NAME = "durable_task_queue1";

    /**
     * 
     */
    public void performSend(String[] args, boolean durable) {

        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel;

            channel = connection.createChannel();         

            if (durable) {
                channel.queueDeclare(DEFAULT_DURABLE_QUEUE_NAME, false, false, false, null);
            } else {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            }

            // basic way
            // String message = "Hello World!";
            // channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            // System.out.println(" [x] Sent '" + message + "'");

            String message = getMessage(args);

            if (durable) {
                channel.basicPublish("", DEFAULT_DURABLE_QUEUE_NAME, 
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes());
            } else {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }

            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0)
            return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
