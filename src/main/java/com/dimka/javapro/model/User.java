package com.dimka.javapro.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Accessors(chain = true)
@Data
@Document("USER")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private Set<Permission> permissions = new HashSet<>();

}
