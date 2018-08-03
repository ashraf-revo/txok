package org.revo.txok.Domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class BaseUser implements UserDetails {
    private String type = "000";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        if (type.charAt(0) == '1') {
            roles.add("ROLE_USER");
        }
        if (type.charAt(1) == '1') {
            roles.add("ROLE_MEDIA");
        }
        if (type.charAt(2) == '1') {
            roles.add("ROLE_ADMIN");
            roles.add("ROLE_ACTUATOR");
        }
        return roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
