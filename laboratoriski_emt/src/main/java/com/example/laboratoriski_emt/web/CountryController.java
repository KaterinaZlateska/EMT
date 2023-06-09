package com.example.laboratoriski_emt.web;

import com.example.laboratoriski_emt.model.Country;
import com.example.laboratoriski_emt.model.dto.CountryDto;
import com.example.laboratoriski_emt.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = {"/api/countries"})
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return this.countryService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto countryDto) {
        return this.countryService.save(countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> save(@PathVariable Long id,
                                        @RequestBody CountryDto countryDto) {
        return this.countryService.update(id, countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping(value = {"/delete/{id}"})
    public ResponseEntity delete(@PathVariable Long id) {
        this.countryService.deleteById(id);
        if (this.countryService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }


}
