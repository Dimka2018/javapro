package com.dimka.javapro.controller;

import com.dimka.javapro.model.User;
import com.dimka.javapro.service.api.UserApiService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserApiService userApiService;

    @PostMapping("/users/login")
    public User login(@RequestBody User user, HttpServletRequest request) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        User authorizedUser = userApiService.login(user);
        request.getSession().setAttribute("user", authorizedUser);
        return authorizedUser;
    }

    @GetMapping("/users/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
