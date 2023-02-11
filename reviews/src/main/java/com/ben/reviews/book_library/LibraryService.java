package com.ben.reviews.book_library;

import com.ben.reviews.book_search.OpenLibraryService;
import com.ben.reviews.book_search.json_model.Book;
import com.ben.reviews.models.author.Author;
import com.ben.reviews.models.author.AuthorRepository;
import com.ben.reviews.models.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final OpenLibraryService openLibraryService;


    public com.ben.reviews.models.book.Book prepareBookEntity(Book book){
        Author author = Author.builder()
                .name(book.getAuthors().get(0).getName().toString())
                .build();
        String testTitle = book.getTitle();
        String testAuthor = book.getAuthors().get(0).getName();
        com.ben.reviews.models.book.Book bookEntity =
                com.ben.reviews.models.book.Book
                        .builder()
                .isbn(book.getIsbn())
                .title(testTitle)
                .author(testAuthor)
                .description(book.getNotes())
                .publication_year(book.getPublish_date())
                .cover_image(book.getCover().getMedium())
                .author_obj(author)
                .build();
        return bookEntity;
    }

    public com.ben.reviews.models.book.Book addBook(String isbn) throws ResponseStatusException {
        boolean isEmpty = bookRepository.findByIsbn(isbn).isEmpty();
        if ( isEmpty ) { // search book with api request
            try {
                Book book = openLibraryService.getBook(isbn);
                com.ben.reviews.models.book.Book bookEntity = prepareBookEntity(book);
                Author author = bookEntity.getAuthor_obj();
                bookRepository.save(bookEntity);
            } catch (Exception e) {
                throw new ResponseStatusException(
                        HttpStatus.EXPECTATION_FAILED,
                        "{\"error\":\"Unable to find book from Open Book API with given ISBN number.\"}"
                );
            }
        }
        return bookRepository.findByIsbn(isbn).get();
    }

    public LibraryResponse searchByIsbn(String isbn) throws ResponseStatusException {
        com.ben.reviews.models.book.Book book;
        Optional<com.ben.reviews.models.book.Book> bookOptional = bookRepository.findByIsbn(isbn);
        if (bookOptional.isPresent()) {
           book = bookOptional.get();
        } else {
            book = addBook(isbn);
        }
        return LibraryResponse
                .builder()
                .book(book)
                .build();
    }
}
