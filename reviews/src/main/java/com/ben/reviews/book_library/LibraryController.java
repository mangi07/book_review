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
    public ResponseEntity<LibraryResponse> testLibraryAdd(
            @PathVariable("isbn") String isbnParam
    ) {
       return ResponseEntity.ok(service.searchByIsbn(isbnParam));
    }


}
