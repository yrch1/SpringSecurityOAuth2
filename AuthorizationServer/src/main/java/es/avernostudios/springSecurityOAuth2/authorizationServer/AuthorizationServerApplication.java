package es.avernostudios.springSecurityOAuth2.authorizationServer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.security.Principal;

@SpringBootApplication
@RestController
@EnableResourceServer
@Slf4j
public class AuthorizationServerApplication extends WebMvcConfigurerAdapter {

        public static void main(String[] args) {
            SpringApplication.run(AuthorizationServerApplication.class, args);
        }

        @Configuration
        @EnableAuthorizationServer
        protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
            @Autowired
            private AuthenticationManager authenticationManager;

            @Override
            public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
                endpoints.authenticationManager(authenticationManager);
            }

            @Override
            public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
                clients.inMemory().withClient("foo").secret("foosecret")
                        .authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes("openid");
            }
        }

        @RequestMapping("/user")
        public Principal user(Principal user) {
            return user;
        }

    }