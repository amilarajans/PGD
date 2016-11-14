package com.origins.pgd.conf;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by Amila-Kumara on 1/4/2016.
 */
@Configuration
@PropertySources({@PropertySource(value = {"classpath:origins.properties"}, ignoreResourceNotFound = true), @PropertySource(value = {"file:origins.properties"}, ignoreResourceNotFound = true)})
@ComponentScan(basePackages = {"com.origins.pgd.license", "com.origins.pgd.service", "com.origins.pgd.security"})
@Import({SecurityConfiguration.class, AsyncConfiguration.class, JacksonConfiguration.class,DatabaseConfiguration.class})//LicenseConfiguration.class,
public class ApplicationConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
