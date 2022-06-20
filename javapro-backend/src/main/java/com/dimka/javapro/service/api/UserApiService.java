package com.dimka.javapro.service.api;

import com.dimka.javapro.exception.UserNotFoundException;
import com.dimka.javapro.model.User;
import com.dimka.javapro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserApiService {

    private final UserService userService;

    public User login(User user) {
        return userService.get(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new UserNotFoundException("User not found " + user.getUsername()));
    }
}
