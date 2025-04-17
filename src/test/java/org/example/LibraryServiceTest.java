package org.example;

// Importing necessary JUnit 5 and Mockito classes for testing and mocking
import org.junit.jupiter.api.BeforeEach; // For setting up test preconditions before each test
import org.junit.jupiter.api.Test; // Marks a method as a test method
import org.junit.jupiter.api.extension.ExtendWith; // Allows extending JUnit with custom extensions
import org.mockito.InjectMocks; // Automatically injects mocks into the test subject
import org.mockito.Mock; // Creates a mock object for a class or interface
import org.mockito.junit.jupiter.MockitoExtension; // Enables Mockito integration with JUnit 5
import static org.junit.jupiter.api.Assertions.*; // Static import for JUnit assertions like assertEquals, assertNull, etc.
import static org.mockito.Mockito.*; // Static import for Mockito methods like when, verify, etc.

// This annotation enables Mockito's features in JUnit 5, such as @Mock and @InjectMocks
@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    // @Mock creates a mock instance of the Library class
    // This means we can control its behavior (e.g., what it returns when methods are called) without using the real implementation
    @Mock
    private Library library;

    // @InjectMocks creates an instance of LibraryService and automatically injects the mocked Library into it
    // This simulates the dependency injection that LibraryService expects (via its constructor)
    @InjectMocks
    private LibraryService libraryService;

    // A Book object that we'll use in our tests
    // Declared as a class field so it can be reused across test methods
    private Book book;

    // @BeforeEach ensures this method runs before each test
    // It sets up a fresh Book object for each test to ensure consistent test data
    @BeforeEach
    void setUp() {
        // Create a new Book instance with title "The Hobbit", author "J.R.R. Tolkien", and ISBN "12345"
        book = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
    }

    // This test verifies that borrowing a book works correctly when the book exists in the library
    @Test
    void shouldBorrowBookSuccessfullyWhenBookExists() {
        // Arrange: Set up the mock behavior
        // When the mock library's findBook method is called with ISBN "12345", it should return our book object
        when(library.findBook("12345")).thenReturn(book);

        // Act: Call the method we want to test
        // Call borrowBook on the libraryService, which should use the mocked library
        Book borrowedBook = libraryService.borrowBook("12345");

        // Assert: Verify the results
        // Check that the borrowed book is not null (meaning the book was found)
        assertNotNull(borrowedBook, "Borrowed book should not be null");
        // Check that the borrowed book matches the book we set up (using equals(), which compares ISBNs)
        assertEquals(book, borrowedBook, "Borrowed book should match the expected book");
        // Verify that findBook was called on the mock library with the correct ISBN
        verify(library).findBook("12345");
        // Verify that removeBook was called on the mock library with the correct ISBN
        // (since borrowing a book should remove it from the library)
        verify(library).removeBook("12345");
    }

    // This test verifies that borrowing a non-existing book returns null and doesn't attempt to remove anything
    @Test
    void shouldReturnNullWhenBorrowingNonExistingBook() {
        // Arrange: Set up the mock behavior
        // When the mock library's findBook method is called with ISBN "99999", it should return null (book not found)
        when(library.findBook("99999")).thenReturn(null);

        // Act: Call the method we want to test
        // Call borrowBook with an ISBN that doesn't exist
        Book borrowedBook = libraryService.borrowBook("99999");

        // Assert: Verify the results
        // Check that the result is null since the book doesn't exist
        assertNull(borrowedBook, "Should return null when book does not exist");
        // Verify that findBook was called on the mock library with the correct ISBN
        verify(library).findBook("99999");
        // Verify that removeBook was never called, since the book wasn't found
        verify(library, never()).removeBook("99999");
    }

    // This test verifies that adding a book through the service correctly delegates to the library
    @Test
    void shouldAddBookSuccessfully() {
        // Act: Call the method we want to test
        // Call addBook on the libraryService, which should call addBook on the mocked library
        libraryService.addBook(book);

        // Assert: Verify the interaction with the mock
        // Verify that the mock library's addBook method was called with the correct book
        verify(library).addBook(book);
    }
}