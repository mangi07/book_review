package com.ben.reviews.book_search;

import com.ben.reviews.book_library.LibraryRequest;
import com.ben.reviews.book_library.LibraryService;
import com.ben.reviews.models.book.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest
public class OpenBookServiceTests {

    @Autowired
    private BookRepository repository;
    @Autowired
    private LibraryService service;
    @Test
    void assertTestTest() {
        int isbn = 123;
        LibraryRequest request = new LibraryRequest(123);
        service.searchByIsbn(request);
        assertTrue(true);
    }
}
