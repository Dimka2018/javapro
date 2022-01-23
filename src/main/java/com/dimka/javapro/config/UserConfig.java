package com.dimka.javapro.config;

import com.dimka.javapro.model.Permission;
import com.dimka.javapro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class UserConfig {

    private final UserService userService;

    //@PostConstruct
    public void createUser() {
        userService.createUser("admin", "admin", Arrays.stream(Permission.values()).collect(Collectors.toSet()));
    }
}
