package com.example.laboratoriski_emt.service.impl;

import com.example.laboratoriski_emt.model.exceptions.NoMoreBookCopiesException;
import com.example.laboratoriski_emt.repository.BookRepository;
import com.example.laboratoriski_emt.service.AuthorService;
import com.example.laboratoriski_emt.service.BookService;
import com.example.laboratoriski_emt.model.Author;
import com.example.laboratoriski_emt.model.Book;
import com.example.laboratoriski_emt.model.dto.BookDto;
import com.example.laboratoriski_emt.model.exceptions.AuthorNotFoundException;
import com.example.laboratoriski_emt.model.exceptions.BookNotFoundException;
import com.example.laboratoriski_emt.model.exceptions.InvalidArgumentsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        if (bookDto.getName() == null || bookDto.getName().isEmpty())
            throw new InvalidArgumentsException();
        Author author = this.authorService.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Author author = this.authorService.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);

    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book.getAvailableCopies() > 0)
        {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            return Optional.of(this.bookRepository.save(book));
        }
        throw new NoMoreBookCopiesException(id);
    }
    }

