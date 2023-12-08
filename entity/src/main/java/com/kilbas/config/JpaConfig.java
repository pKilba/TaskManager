package com.kilbas.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EntityScan("com.kilbas.model")
@EnableJpaAuditing

public class JpaConfig {

}
