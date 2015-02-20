package com.cvent.logic.pubsub;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author jregan
 *
 */
public class MessagePublisher {

    /**
     * 
     */
    public void performSend(String[] args, String exchangeName, String exchangeType, boolean durability) {

        try {
            
            /*
                ConnectionFactory factory = new ConnectionFactory();
                factory.setHost("localhost");
                Connection connection = factory.newConnection();
                Channel channel;
    
                channel = connection.createChannel();         
                
                channel.exchangeDeclare(exchangeName, "fanout");
    
                String message = getMessage(args);
    
                channel.basicPublish(exchangeName, "", null, message.getBytes());
    
                System.out.println(" [x] Sent '" + message + "'");
    
                channel.close();
                connection.close();
    
            
            */
            
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
    
            // version without durability - channel.exchangeDeclare(exchangeName, exchangeType);
            channel.exchangeDeclare(exchangeName, exchangeType, durability);
            
            channel.confirmSelect();
    
            String message = getMessage(args);
    
            channel.basicPublish(exchangeName, "", null, message.getBytes());
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
