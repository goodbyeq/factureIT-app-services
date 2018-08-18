package com.beatus.factureIT.app.services.config;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Jersey-specific configuration class that extends and wraps the JSR-311 
 * {@link Application} class. This is the entry point for the JSR-311
 * configuration.
 * 
 * @author Abhinav Akey
 */
@Configuration
public class RestConfiguration extends ResourceConfig {

    private static final String APPLICATION_NAME = "factureIT";

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public RestConfiguration() {

        // Provider-specific properties
        property(ServerProperties.APPLICATION_NAME, APPLICATION_NAME); 
        property(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);

    }
}