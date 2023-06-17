package com.AATransferAPI.AATransferAPI.TransfersModel;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class TransfersAuditingConfiguration {

    @Bean
    public AuditorAware<User> auditorProvider(){
        return new SecurityAuditorAware();
    }
}
