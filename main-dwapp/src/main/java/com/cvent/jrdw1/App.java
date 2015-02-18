package com.cvent.jrdw1;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.couchbase.client.CouchbaseClient;
import com.cvent.logic.workq.MessageWorker;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<DwConfig> {

    public static void main(String[] args) throws Exception {

        /*
        // (Subset) of nodes in the cluster to establish a connection
        List<URI> hosts = Arrays.asList(new URI("http://127.0.0.1:8091/pools"));

        // Name of the Bucket to connect to
        String bucket = "default";

        // Password of the bucket (empty) string if none
        String password = "";

        // Connect to the Cluster
        CouchbaseClient client = new CouchbaseClient(hosts, bucket, password);

        // Store a Document
        client.set("my-first-document", "Hello Couchbase").get();

        // Retreive the Document and print it
        System.out.println(client.get("my-first-document"));

        // Shutting down properly
        client.shutdown();
        */
        
        /*
        MessageCreator mqInteraction = new MessageCreator();
        mqInteraction.performSend();
        mqInteraction.performReceive();
        */
        
        /*
        MessageWorker messageWorker = new MessageWorker();
        messageWorker.performReceive();
        */

        try {
            new App().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DwConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DwConfig configuration, Environment environment) {

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
                configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        final DwResource resource = new DwResource(configuration.getTemplate(),
                configuration.getDefaultName());

        environment.jersey().register(resource);
    }

}
