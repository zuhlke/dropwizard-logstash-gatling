package com.toastshaman.dropwizard;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Named;

public class HelloWorldModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Named("defaultName")
    public String provideDefaultName(HelloWorldConfiguration configuration) {
        return configuration.getDefaultName();
    }
}
