package org.revo.txok.Config;

import org.revo.txok.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Util {
    @Bean
    public CommandLineRunner runner(Env env, UserService userService) {
        return args -> {
            if (env.getUsers().size() > 0) userService.count().filter(it -> it == 0)
                    .flatMapMany(it -> userService.save(env.getUsers())).subscribe();
        };
    }

}
