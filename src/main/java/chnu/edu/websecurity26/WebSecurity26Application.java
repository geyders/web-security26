package chnu.edu.websecurity26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
public class WebSecurity26Application {

    public static void main(String[] args) {
        SpringApplication.run(WebSecurity26Application.class, args);
    }

}