package com.example.laboratoriski_emt.service.impl;

import com.example.laboratoriski_emt.repository.AuthorRepository;
import com.example.laboratoriski_emt.service.AuthorService;
import com.example.laboratoriski_emt.service.CountryService;
import com.example.laboratoriski_emt.model.Author;
import com.example.laboratoriski_emt.model.Country;
import com.example.laboratoriski_emt.model.dto.AuthorDto;
import com.example.laboratoriski_emt.model.exceptions.AuthorNotFoundException;
import com.example.laboratoriski_emt.model.exceptions.CountryNotFoundException;
import com.example.laboratoriski_emt.model.exceptions.InvalidArgumentsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }


    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        if (authorDto.getName() == null || authorDto.getName().isEmpty() || authorDto.getSurname() == null || authorDto.getSurname().isEmpty())
            throw new InvalidArgumentsException();
        Country country = this.countryService.findById(authorDto.getCountry()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));
        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> update(Long id, AuthorDto authorDto) {
        Author author = this.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        Country country = this.countryService.findById(authorDto.getCountry()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
