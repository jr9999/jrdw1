package org.task.creator;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Hello world!
 * 
 */
public class MessageCreator {
    
    private final static String QUEUE_NAME = "hello";

    /**
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        
        performSend(args);
    }

    /**
     * 
     */
    public static void performSend(String[] args) {

        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel;

            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            
            //basic way
            //String message = "Hello World!";
            //channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            //System.out.println(" [x] Sent '" + message + "'");
            
            String message = getMessage(args);

            channel.basicPublish("", "hello", null, message.getBytes());
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
