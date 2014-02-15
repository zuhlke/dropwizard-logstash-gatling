package com.toastshaman.dropwizard.resources;

import com.sun.jersey.api.core.HttpContext;
import com.toastshaman.dropwizard.domain.Saying;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    @Inject
    @Named("defaultName")
    private String defaultName;

    final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    @GET
    @Path("/world")
    public Saying sayHello(@Context HttpContext context) {
        logger.info("User-Agent: " + context.getRequest().getHeaderValue("User-Agent"));
        return new Saying.Builder().withContent("Hello, " + defaultName).build();
    }
}
