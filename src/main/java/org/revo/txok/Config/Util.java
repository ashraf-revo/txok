package org.revo.txok.Config;

import org.revo.txok.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Util {
    @Bean
    public CommandLineRunner runner(Env env, UserRepository userRepository) {
        return args -> {

            if (env.getUsers().size() > 0) userRepository.count().filter(it -> it == 0)
                    .flatMapMany(it -> userRepository.saveAll(env.getUsers())).subscribe(System.out::println);

        };
    }
}
