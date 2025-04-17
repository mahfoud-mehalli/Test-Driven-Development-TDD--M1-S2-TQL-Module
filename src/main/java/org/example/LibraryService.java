package org.example;

public class LibraryService {
    private final Library library;

    public LibraryService(Library library) {
        this.library = library;
    }

    public Book borrowBook(String isbn) {
        Book book = library.findBook(isbn);
        if (book != null) {
            library.removeBook(isbn);
        }
        return book;
    }

    public void addBook(Book book) {
        library.addBook(book);
    }
}