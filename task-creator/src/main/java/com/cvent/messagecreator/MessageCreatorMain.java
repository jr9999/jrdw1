package com.cvent.messagecreator;

import com.cvent.logic.pubsub.MessagePublisher;
import com.cvent.logic.workq.MessageCreator;

/**
 * 
 * @author jregan
 *
 */
public class MessageCreatorMain {
    
    //private final static String EXCHANGE_NAME = "logs";
    private final static String EXCHANGE_NAME = "ha:attendeedelta:local:ATTENDEE_DELTA_INDEX_EXCHANGE";
    
    //private final static String EXCHANGE_TYPE = "fanout";
    private final static String EXCHANGE_TYPE = "direct";
    
    private final static boolean EXCHANGE_DURABILITY = true;

    /**
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("message-creator-main");

        /*
        MessageCreator messageCreator = new MessageCreator();
        
        // second arg - durable queue or not?
        messageCreator.performSend(args, true);
        */
        
        MessagePublisher messagePublisher = new MessagePublisher();
        messagePublisher.performSend(args, EXCHANGE_NAME, EXCHANGE_TYPE, EXCHANGE_DURABILITY);
    }
}
