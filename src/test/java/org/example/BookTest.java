package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    void shouldCreateBookCorrectly() {
        // Arrange
        String title = "The Hobbit";
        String author = "J.R.R. Tolkien";
        String isbn = "12345";

        // Act
        Book book = new Book(title, author, isbn);

        // Assert
        assertEquals(title, book.getTitle(), "Title should match the one provided in constructor");
        assertEquals(author, book.getAuthor(), "Author should match the one provided in constructor");
        assertEquals(isbn, book.getIsbn(), "ISBN should match the one provided in constructor");
    }

    @Test
    void shouldConsiderBooksEqualIfSameIsbn() {
        // Arrange
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
        Book book2 = new Book("The Hobbit 2", "J.R.R. Tolkien", "12345");
        
        // Act & Assert
        assertTrue(book1.equals(book2), "Books with the same ISBN should be equal");
        assertTrue(book2.equals(book1), "Books with the same ISBN should be equal (symmetric)");
        assertEquals(book1.hashCode(), book2.hashCode(), "Books with the same ISBN should have the same hash code");
    }

}