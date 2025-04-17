package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Nested
    class BookAdditionAndRemovalTests {
        @Test
        void shouldAddBookSuccessfully() {
            // Arrange
            Book book = new Book("The Hobbit", "J.R.R. Tolkien", "12345");

            // Act
            library.addBook(book);

            // Assert
            assertEquals(1, library.listBooks().size(), "Library should contain one book after adding");
            assertTrue(library.listBooks().contains(book), "Library should contain the added book");
        }

        @Test
        void shouldRemoveBookSuccessfully() {
            // Arrange
            Book book = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
            library.addBook(book);

            // Act
            library.removeBook("12345");

            // Assert
            assertEquals(0, library.listBooks().size(), "Library should be empty after removing the book");
            assertFalse(library.listBooks().contains(book), "Library should not contain the removed book");
        }
    }

    @Nested
    class BookSearchTests {
        @Test
        void shouldFindExistingBook() {
            // Arrange
            Book book = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
            library.addBook(book);

            // Act
            Book foundBook = library.findBook("12345");

            // Assert
            assertNotNull(foundBook, "Found book should not be null");
            assertEquals(book, foundBook, "Found book should match the added book");
        }

        @Test
        void shouldReturnNullForNonExistingBook() {
            // Act
            Book foundBook = library.findBook("99999");

            // Assert
            assertNull(foundBook, "Should return null when searching for a non-existing book");
        }
    }
}