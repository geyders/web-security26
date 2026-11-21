package chnu.edu.websecurity26.user;

/*
  @author   george
  @project   book-social-network
  @class  UserDetailsServiceImpl
  @version  1.0.0
  @since 05.10.24 - 15.15
*/


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        repository.save(User.builder()
                .id("1")
                .firstName("John")
                .lastName("Smith")
                .email("john@mail.com")
                .password(passwordEncoder.encode("admin"))
                .enabled(true)
                .accountLocked(false)
                .roles(List.of(Role.ADMIN))
                .build());

        repository.save(User.builder()
                .id("2")
                .firstName("Jane")
                .lastName("Doe")
                .email("jane@mail.com")
                .password(passwordEncoder.encode("user"))
                .enabled(true)
                .accountLocked(false)
                .roles(List.of(Role.USER))
                .build());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return repository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}