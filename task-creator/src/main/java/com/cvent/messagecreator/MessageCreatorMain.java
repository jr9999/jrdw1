package com.cvent.messagecreator;

import com.cvent.logic.pubsub.MessagePublisher;
import com.cvent.logic.workq.MessageCreator;

/**
 * 
 * @author jregan
 *
 */
public class MessageCreatorMain {
    
    private final static String EXCHANGE_NAME = "logs";

    /**
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("message-");

        /*
        MessageCreator messageCreator = new MessageCreator();
        
        // second arg - durable queue or not?
        messageCreator.performSend(args, true);
        */
        
        MessagePublisher messagePublisher = new MessagePublisher();
        messagePublisher.performSend(args, EXCHANGE_NAME);
    }
}
