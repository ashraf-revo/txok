package org.revo.txok.Config;

import org.springframework.http.ResponseCookie;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
public class CookieFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Mono<CsrfToken> token = exchange.getAttributeOrDefault(CsrfToken.class.getName(), Mono.empty());
        return token.flatMap(it -> {
            exchange.getResponse().addCookie(ResponseCookie.from("X-CSRF-TOKEN", it.getToken()).build());
            return chain.filter(exchange);
        });

    }
}