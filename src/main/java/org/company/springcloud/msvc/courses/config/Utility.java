package org.company.springcloud.msvc.courses.config;

import org.company.springcloud.msvc.courses.utils.RequestValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utility {

    @Bean
    public RequestValidationService validationService(){
        return new RequestValidationService();
    }

}
