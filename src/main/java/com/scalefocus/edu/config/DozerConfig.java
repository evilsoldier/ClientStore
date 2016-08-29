package com.scalefocus.edu.config;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scalefocus.edu.service.map.ClientStoreMappingBuilder;


@Configuration
public class DozerConfig {
    private static final BeanMappingBuilder BUILDER = new ClientStoreMappingBuilder();
    private static final DozerBeanMapper MAPPER = new DozerBeanMapper();

    static {
        MAPPER.addMapping(BUILDER);
    }

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        return MAPPER;
    }
}

