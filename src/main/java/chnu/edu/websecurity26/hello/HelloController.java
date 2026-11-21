package chnu.edu.websecurity26.hello;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin() {
        return "Hello Admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String user() {
        return "Hello User";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String all() {
        return "Hello All";
    }
}