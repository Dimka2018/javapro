package com.dimka.javapro.repository;

import com.dimka.javapro.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);
}
