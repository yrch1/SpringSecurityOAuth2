package es.avernostudios.springSecurityOAuth2.resourceServer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@SpringBootApplication
@RestController
@EnableResourceServer
@Slf4j
public class ResourceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

    @RequestMapping("/")
    public String securedCall() {
        return "success (id: " + UUID.randomUUID().toString().toUpperCase() + ")";
    }
}
