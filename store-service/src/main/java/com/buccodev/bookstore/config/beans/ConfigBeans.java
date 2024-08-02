package com.buccodev.bookstore.config.beans;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class ConfigBeans {

    @Bean
    public Faker faker(){

        return new Faker(new Locale("pt-BR"));
    }

}
