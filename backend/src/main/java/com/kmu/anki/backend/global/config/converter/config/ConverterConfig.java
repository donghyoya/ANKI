package com.kmu.anki.backend.global.config.converter.config;

import com.kmu.anki.backend.global.config.converter.factory.StringToEnumConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConverterConfig implements WebMvcConfigurer {
    @Bean
    StringToEnumConverterFactory stringToEnumConverterFactory(){
        return new StringToEnumConverterFactory();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(stringToEnumConverterFactory());
    }
}
