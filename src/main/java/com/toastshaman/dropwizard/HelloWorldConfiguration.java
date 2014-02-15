package com.toastshaman.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import net.gini.dropwizard.gelf.config.GelfConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

public class HelloWorldConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty
    private String defaultName = "Stranger";

    private GelfConfiguration gelf;

    public GelfConfiguration getGelf() {
        return gelf;
    }

    public String getDefaultName() {
        return defaultName;
    }
}
