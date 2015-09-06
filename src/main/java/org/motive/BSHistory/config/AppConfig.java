package org.motive.BSHistory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MvcConfig.class, JPAConfig.class})
public class AppConfig {

}
