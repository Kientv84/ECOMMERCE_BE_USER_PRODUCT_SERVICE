package com.ecommerce.kientv84.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() { //Audit giống như công tắt bật --> chỉ ra cho class có anotation EntityListeners trả về "ai đang đăng nhập".
        return new AuditorAwareImpl();
    }
}
