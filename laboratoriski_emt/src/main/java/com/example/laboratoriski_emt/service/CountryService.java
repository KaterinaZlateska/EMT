package com.example.laboratoriski_emt.service;

import com.example.laboratoriski_emt.model.Country;
import com.example.laboratoriski_emt.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(CountryDto countryDto);

    Optional<Country> update(Long id, CountryDto countryDto);

    void deleteById(Long id);

}
