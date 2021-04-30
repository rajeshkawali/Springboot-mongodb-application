package com.rajeshkawali.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "userprofile")
public class UserProfileConfig {

    private List<String> identityTypes = new ArrayList<>();

    public List<String> getIdentityTypes() {
        return identityTypes;
    }

}