package com.cvent.topicreceive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 
 * @author jregan
 * 
 */
public class ReceiveLogsTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv)
            throws Exception {

        List<String> argList = Arrays.asList(argv);

        if (argList != null) {
            System.out.println("arg list = \n");
            for (String arg : argList) {
                System.out.println("arg = " + arg);
            }
        }

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        com.rabbitmq.client.Connection connection = factory.newConnection();
        com.rabbitmq.client.Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        if (argList.size() < 2) {
            System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
            System.exit(1);
        }

        String queueName = argList.get(0);
        List<String> bindingsList = new ArrayList<String>();
        bindingsList.addAll(argList);
        bindingsList.remove(0);

        DeclareOk queueDeclareOk = channel.queueDeclare(queueName, true, false, true, null);

        if (queueDeclareOk != null) {
            for (String bindingKey : argList) {
                channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            }
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(queueName, true, consumer);

        while (true) {

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();

            System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");
        }
    }
}
