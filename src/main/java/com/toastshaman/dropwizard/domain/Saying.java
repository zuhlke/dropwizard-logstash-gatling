package com.toastshaman.dropwizard.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

public class Saying {

    private String content;

    public Saying(String content) {
        this.content = content;
    }

    void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public static class Builder {

        private String content;

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Saying build() {
            validate();

            Saying saying = new Saying(content);

            return saying;
        }

        private void validate() {
            Preconditions.checkArgument(StringUtils.isNotBlank(content), "content can not be null or empty");
        }

    }
}
