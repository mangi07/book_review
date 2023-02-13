package com.ben.reviews.book_library;

import com.ben.reviews.book_search.OpenLibraryService;
import com.ben.reviews.book_search.json_model.Book;
import com.ben.reviews.models.author.Author;
import com.ben.reviews.models.book.BookRepository;
import com.ben.reviews.models.user.User;
import com.ben.reviews.models.user.UserRepository;
import com.ben.reviews.models.user_book.UserBook;
import com.ben.reviews.models.user_book.UserBookRepository;
import com.ben.reviews.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserBookRepository userBookRepository;
    private final OpenLibraryService openLibraryService;
    private final JwtService jwtService;



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
                .publication_date(book.getPublish_date())
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
                bookRepository.save(bookEntity);
            } catch (Exception e) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "{\"error\":\"Unable to find book from Open Book API with given ISBN number.\"}"
                );
            }
        }
        return bookRepository.findByIsbn(isbn).get();
    }

    private com.ben.reviews.models.book.Book getBook (String isbn) {
        com.ben.reviews.models.book.Book book;
        if (bookRepository.findByIsbn(isbn).isPresent()) {
            book = bookRepository.findByIsbn(isbn).get();
        } else {
            book = addBook(isbn);
        }
        return book;
    }

    public LibraryResponse searchByIsbn(String isbn) throws ResponseStatusException {
        com.ben.reviews.models.book.Book book = this.getBook(isbn);

        return LibraryResponse
                .builder()
                .book(book)
                .build();
    }

    public LibraryResponse addBookToUserByIsbn(String isbn, String jwt) {
        UserBook userBook;
        Optional<UserBook> userBookOptional = this.findUserBook(jwt, isbn);

        if (userBookOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User has already added this book."
            );
        }

        com.ben.reviews.models.book.Book book = this.getBook(isbn);
        String username = jwtService.extractUsername(jwt);
        User user = userRepository.findByName(username).get();
        userBook = UserBook.builder()
                .bookId(book.getId())
                .userId(user.getId())
                .build();
        userBookRepository.save(userBook);

        return LibraryResponse
                .builder()
                .book(book)
                .build();
    }

    public ReviewResponse reviewBook(String isbn, String jwt, String review, Integer rating) {
        UserBook userBook;
        Optional<UserBook> userBookOptional = this.findUserBook(jwt, isbn);

        if (userBookOptional.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User has not yet added this book."
            );
        }

        userBook = userBookOptional.get();
        userBook.setReview(review);
        userBook.setRating(rating);
        userBookRepository.save(userBook);

        com.ben.reviews.models.book.Book book = bookRepository.findByIsbn(isbn).get();
        return ReviewResponse
                .builder()
                .book(book)
                .review(review)
                .rating(rating)
                .build();
    }

    private Optional<UserBook> findUserBook(String jwt, String isbn) {
        com.ben.reviews.models.book.Book book = this.getBook(isbn);
        String username = jwtService.extractUsername(jwt);
        User user = userRepository.findByName(username).get();

        Integer userId = user.getId();
        Integer bookId = book.getId();
        Optional<UserBook> userBook = userBookRepository.findByUserIdAndBookId(userId, bookId);
        return userBook;
    }

    public ReviewsResponse listReviews(String isbn, String jwt) {
        com.ben.reviews.models.book.Book book = this.getBook(isbn);
        Integer bookId = book.getId();
        List<UserBook> userBookList = userBookRepository.findByReviewIsNotNullAndBookIdEquals(bookId);
        // TODO: figure out inner join to get usernames of the reviews

        return ReviewsResponse.builder().bookReviews(userBookList).build();
    }
}
