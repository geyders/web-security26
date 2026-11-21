package chnu.edu.websecurity26.config;


/*
  @author   george
  @project   web-security26
  @class  AuditionConfiguration
  @version  1.0.0
  @since 22/09/2026 - 12.50
*/


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@Configuration
public class AuditionConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }


}