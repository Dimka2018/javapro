package com.dimka.javapro.service;

import com.dimka.javapro.model.Permission;
import com.dimka.javapro.model.User;
import com.dimka.javapro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> get(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public void createUser(String username, String password, Set<Permission> permissions) {
        User user = new User()
                .setUsername(username)
                .setPassword(password)
                .setPermissions(permissions);
        userRepository.save(user);

    }
}
