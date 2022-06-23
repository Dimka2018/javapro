package com.dimka.javapro.controller;

import com.dimka.javapro.service.api.UserApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserApiService userApiService;

    @GetMapping("/user/permissions/{permission}")
    public boolean hasPermission(@PathVariable String permission) {
        return userApiService.hasPermission(permission);
    }

    @GetMapping("/user/authentication")
    public boolean isAuthenticated() {
        return userApiService.isAuthenticated();
    }

}
