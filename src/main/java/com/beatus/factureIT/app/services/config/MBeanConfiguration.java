/*
 * Unauthorized reproduction, transmission, or distribution of
 * this software is a violation of applicable laws.
 */
package com.beatus.factureIT.app.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.MBeanServerFactoryBean;

/**
 * JMX MBean Server configuration.
 * 
 * @author Abhinav Akey
 * @since 1.8
 */
@Configuration
public class MBeanConfiguration {

    @Bean
    public MBeanServerFactoryBean mBeanServerFactoryBean() {
        MBeanServerFactoryBean mBeanServerFactoryBean 
                = new MBeanServerFactoryBean();

        mBeanServerFactoryBean
                .setLocateExistingServerIfPossible(
                        true);

        return mBeanServerFactoryBean;
    }
    
}
