package com.example.laboratoriski_emt.model.exceptions;

public class NoMoreBookCopiesException extends RuntimeException {
    public NoMoreBookCopiesException(Long id) {
        super(String.format("There are no more available copies for the book with id %d", id));
    }
}

