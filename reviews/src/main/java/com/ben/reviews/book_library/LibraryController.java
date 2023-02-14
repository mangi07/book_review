package com.ben.reviews.book_library;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin
public class LibraryController {

    private final LibraryService service;

    @GetMapping("/search/{isbn}")
    public ResponseEntity<LibraryResponse> addBook(
            @PathVariable("isbn") String isbnParam
    ) {
       return ResponseEntity.ok(service.searchByIsbn(isbnParam));
    }

    @GetMapping("/add/{isbn}")
    public ResponseEntity<LibraryResponse> addBookToUser(
            @PathVariable("isbn") String isbnParam,
            @RequestHeader (name="Authorization") String jwtHeader
    ) {
        String jwt = jwtHeader.substring(7); // remove "Bearer " to get jwt
        return ResponseEntity.ok(service.addBookToUserByIsbn(isbnParam, jwt));
    }

    @PostMapping("/review/{isbn}")
    public ResponseEntity<ReviewResponse> reviewBook(
            @PathVariable("isbn") String isbnParam,
            @RequestHeader (name="Authorization") String jwtHeader,
            @RequestBody ReviewRequest request
    ) {
        String jwt = jwtHeader.substring(7); // remove "Bearer " to get jwt
        String review = request.getReview();
        Integer rating = request.getRating();
        return ResponseEntity.ok(service.reviewBook(isbnParam, jwt, review, rating));
    }

    @GetMapping("/list/reviews/{isbn}")
    public ResponseEntity<ReviewsResponse> listReviews(
            @PathVariable("isbn") String isbnParam,
            @RequestHeader (name="Authorization") String jwtHeader
    ) {
        String jwt = jwtHeader.substring(7); // remove "Bearer " to get jwt
        return ResponseEntity.ok(service.listReviews(isbnParam, jwt));
    }
    @GetMapping("/library/{username}")
    public ResponseEntity<LibraryBooksResponse> listReviews(
            @RequestHeader (name="Authorization") String jwtHeader
    ) {
        String jwt = jwtHeader.substring(7); // remove "Bearer " to get jwt
        return ResponseEntity.ok(service.listLibraryBooks(jwt));
    }

}
