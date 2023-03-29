package com.example.laboratoriski_emt.service;

import com.example.laboratoriski_emt.model.Author;
import com.example.laboratoriski_emt.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> update(Long id, AuthorDto authorDto);

    void deleteById(Long id);

}
