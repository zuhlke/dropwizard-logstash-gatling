package com.toastshaman.dropwizard;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.toastshaman.dropwizard.resources.HelloWorldResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import net.gini.dropwizard.gelf.bundles.GelfLoggingBundle;
import net.gini.dropwizard.gelf.config.GelfConfiguration;
import net.gini.dropwizard.gelf.filters.GelfLoggingFilter;

public class HelloWorldService extends Service<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldService().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.setName("hello-world");

        GuiceBundle<HelloWorldConfiguration> guiceBundle = GuiceBundle.<HelloWorldConfiguration>newBuilder()
                .addModule(new HelloWorldModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(HelloWorldConfiguration.class)
                .build();

        GelfLoggingBundle<HelloWorldConfiguration> gelfLoggingBundle = new GelfLoggingBundle<HelloWorldConfiguration>() {
            @Override
            public GelfConfiguration getConfiguration(HelloWorldConfiguration configuration) {
                return configuration.getGelf();
            }
        };

        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(gelfLoggingBundle);
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        environment.addFilter(new GelfLoggingFilter(), "/api/*");
        environment.addResource(HelloWorldResource.class);
    }
}
