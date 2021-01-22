package com.danielarrais.algafood.core.conf;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConf {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
