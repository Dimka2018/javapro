package com.dimka.javapro.controller;

import com.dimka.javapro.service.api.UserApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserApiService userApiService;

    @GetMapping("/user/permissions")
    public List<String> getPermissions() {
        return userApiService.getPermissions();
    }

    @GetMapping("/user/authentication")
    public boolean isAuthenticated() {
        return userApiService.isAuthenticated();
    }

}
