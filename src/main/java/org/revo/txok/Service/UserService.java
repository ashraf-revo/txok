package org.revo.txok.Service;

import org.revo.txok.Domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<String> currentUser();

    Mono<Long> count();

    Flux<User> save(List<User> users);

}
