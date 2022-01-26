package com.dimka.javapro.config;

import com.dimka.javapro.model.Permission;
import com.dimka.javapro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class UserConfig {

    private final UserService userService;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @PostConstruct
    public void createUser() {
        if (userService.get(adminUsername, adminPassword).isEmpty()) {
            userService.createUser(adminUsername, adminPassword, Arrays.stream(Permission.values()).collect(Collectors.toSet()));
        }
    }
}
