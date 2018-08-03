package org.revo.txok;

import org.revo.txok.Config.Env;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.UUID;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@SpringBootApplication
@EnableConfigurationProperties(Env.class)
@EnableMongoAuditing
public class TxokApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxokApplication.class, args);
    }


    @Bean
    public RouterFunction<ServerResponse> function() {
        return route(GET("/api/userId"), serverRequest -> ok().body(fromObject(UUID.randomUUID().toString())));
    }
}
