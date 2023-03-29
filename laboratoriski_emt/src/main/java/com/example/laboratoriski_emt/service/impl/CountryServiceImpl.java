package com.example.laboratoriski_emt.service.impl;

import com.example.laboratoriski_emt.repository.CountryRepository;
import com.example.laboratoriski_emt.service.CountryService;
import com.example.laboratoriski_emt.model.Country;
import com.example.laboratoriski_emt.model.dto.CountryDto;
import com.example.laboratoriski_emt.model.exceptions.CountryNotFoundException;
import com.example.laboratoriski_emt.model.exceptions.InvalidArgumentsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        if (countryDto.getName() == null || countryDto.getContinent() == null || countryDto.getName().isEmpty() || countryDto.getContinent().isEmpty())
            throw new InvalidArgumentsException();
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, CountryDto countryDto) {
        Country country = this.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
