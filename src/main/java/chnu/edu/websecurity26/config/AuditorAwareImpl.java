package chnu.edu.websecurity26.config;


/*
  @author   george
  @project   web-security26
  @class  AuditAwareImpl
  @version  1.0.0
  @since 22/09/2026 - 12.49
*/


import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        //   return Optional.of(System.getProperty("user.name"));
        return Optional.of("Admin");
    }
}