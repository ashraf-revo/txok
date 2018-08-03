package org.revo.txok.Config;

import org.revo.txok.Repository.UserRepository;
import org.revo.txok.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class Security {

    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange().anyExchange().authenticated().and()
                .formLogin().and().logout()
                .and().csrf().csrfTokenRepository(new CookieServerCsrfTokenRepository());
        return http.build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository userRepository) {
        return s -> userRepository.findByUsername(s).cast(UserDetails.class);

    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuditorAware<String> aware(UserService userService) {
        return userService::currentUser;
    }
}
