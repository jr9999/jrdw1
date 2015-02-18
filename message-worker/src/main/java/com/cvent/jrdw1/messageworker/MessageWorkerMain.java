package com.cvent.jrdw1.messageworker;

import com.cvent.logic.pubsub.MessageSubscriber;
import com.cvent.logic.workq.MessageWorker;

/**
 * 
 * @author jregan
 *
 */
public class MessageWorkerMain {
    
    private final static String EXCHANGE_NAME = "logs";

    /**
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("message-worker");
        
        /*
        MessageWorker messageWorker = new MessageWorker();

        //first argument - use durable queue or not?
        messageWorker.performReceive(true);
        */
        
        MessageSubscriber messageSubscriber = new MessageSubscriber();
        messageSubscriber.performReceive(EXCHANGE_NAME);
    }
   
}
