package com.cvent.jrdw1;

import com.cvent.jrdw1.Saying;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import com.cvent.jrdw1.transport.TestSchema;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/dwr")
@Produces(MediaType.APPLICATION_JSON)
public class DwResource {
	
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    /**
     * 
     * @param template
     * @param defaultName
     */
    public DwResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
    
}
