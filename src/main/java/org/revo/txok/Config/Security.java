package org.revo.txok.Config;

import org.revo.txok.Repository.UserRepository;
import org.revo.txok.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class Security {

    @Autowired
    private CookieServerCsrfTokenRepository csrfTokenRepository;


    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http) {


        http

                .exceptionHandling()
                .authenticationEntryPoint((exchange, e) -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return

                            csrfTokenRepository.loadToken(exchange)

                            .switchIfEmpty(csrfTokenRepository.generateToken(exchange))
                            .flatMap(it -> {
                                exchange.getResponse().addCookie(ResponseCookie.from("XSRF-TOKEN", it.getToken()).path("/").build());
                                return Mono.empty();
                            });
                })
                .and()
                .authorizeExchange()

                .pathMatchers("/api/teacher", "/api/teacher/*/**").hasRole("TEACHER")
                .pathMatchers("/api/student", "/api/student/*/**").hasRole("STUDENT")
                .pathMatchers("/api/user", "/api/user/*/**").hasRole("ADMIN")
                .pathMatchers("/auth/user").authenticated()
                .anyExchange().permitAll()

                .and().csrf().csrfTokenRepository(csrfTokenRepository)
                .and()
                .formLogin()
                .loginPage("/login")
                .authenticationFailureHandler((exchange, e) -> {
                    exchange.getExchange().getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getExchange().getResponse().setComplete();
                })
                .authenticationSuccessHandler((webFilterExchange, authentication) -> {
                    webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                })
                .and()
                .logout()
                .logoutUrl("/logout")

                .logoutHandler((exchange, authentication) -> {
                    exchange.getExchange().getResponse().setStatusCode(HttpStatus.OK);
                    return exchange.getExchange().getResponse().setComplete();
                });
        return http.build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository userRepository) {
        return s -> userRepository.findByUsername(s)
                .cast(UserDetails.class);
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
