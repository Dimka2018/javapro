package com.dimka.javapro.repository;

import com.dimka.javapro.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, String> {
}
