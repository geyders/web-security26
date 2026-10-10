package chnu.edu.websecurity26.config;

/*
 @author Lenovo
 @project web-security26
 @class AuditorAwareImpl
 @version 1.0.0
 @since 10/10/2026 - 21.09
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