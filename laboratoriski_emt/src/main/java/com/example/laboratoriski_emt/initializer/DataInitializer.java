package com.example.laboratoriski_emt.initializer;

import com.example.laboratoriski_emt.model.enumetarions.Category;
import com.example.laboratoriski_emt.service.AuthorService;
import com.example.laboratoriski_emt.service.BookService;
import com.example.laboratoriski_emt.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.example.laboratoriski_emt.model.Author;
import com.example.laboratoriski_emt.model.Country;
import com.example.laboratoriski_emt.model.dto.AuthorDto;
import com.example.laboratoriski_emt.model.dto.BookDto;
import com.example.laboratoriski_emt.model.dto.CountryDto;

import java.util.List;

@Component
public class DataInitializer {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }


    @PostConstruct
    void init(){

        this.countryService.save(new CountryDto("Italy", "Europe"));
        this.countryService.save(new CountryDto("Brazil", "South America"));
        this.countryService.save(new CountryDto("Macedonia", "Europe"));
        this.countryService.save(new CountryDto("Germany", "Europe"));

        List<Country> countryList = this.countryService.findAll();



        this.authorService.save(new AuthorDto("Katerina","Zlateska",countryList.get(0).getId()));
        this.authorService.save(new AuthorDto("Loren","Oliver",countryList.get(1).getId()));
        this.authorService.save(new AuthorDto("Beka","Ficpatrik",countryList.get(2).getId()));
        this.authorService.save(new AuthorDto("Bojana","Anastasova",countryList.get(3).getId()));

        List<Author> authors = this.authorService.findAll();



        this.bookService.save(new BookDto("Story", Category.DRAMA,authors.get(0).getId(),300));
        this.bookService.save(new BookDto("Pandemonium",Category.THRILLER,authors.get(1).getId(),260));
        this.bookService.save(new BookDto("Hush, hush",Category.FANTASY,authors.get(2).getId(),100));
        this.bookService.save(new BookDto("Lovely",Category.FANTASY,authors.get(3).getId(),200));
    }

    }


