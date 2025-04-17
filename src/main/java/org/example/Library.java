package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public Book findBook(String isbn) {
        return books.stream()
                    .filter(book -> book.getIsbn().equals(isbn))
                    .findFirst()
                    .orElse(null);
    }

    public List<Book> listBooks() {
        return new ArrayList<>(books);
    }

}