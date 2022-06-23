package com.dimka.javapro.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserApiService {

    public boolean hasPermission(String permission) {
        List<String> authorities = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return authorities.contains(permission);
    }

    public boolean isAuthenticated() {
        return !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
    }

}
