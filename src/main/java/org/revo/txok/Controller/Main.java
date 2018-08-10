package org.revo.txok.Controller;

import org.revo.txok.Domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
    @RequestMapping("/auth/user")
    public User user(@AuthenticationPrincipal User user) {
        return user;
    }
}
