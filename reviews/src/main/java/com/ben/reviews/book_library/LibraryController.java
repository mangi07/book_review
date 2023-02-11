package com.ben.reviews.book_library;

import com.ben.reviews.security.register.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin
public class LibraryController {

    private final LibraryService service;
    @GetMapping("/auth/testLibraryAdd")
    public ResponseEntity<LibraryResponse> register(
            @RequestBody LibraryRequest request
    ) {
       return ResponseEntity.ok(service.addBook(request));
    }

    /*
    @PostMapping("/auth/login")
    public ResponseEntity<LibraryResponse> login(
            @RequestBody LibraryRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping("/userinfo")
    public ResponseEntity<LibraryResponse> testLoggedInUser(
            @RequestBody LibraryRequest request
    ) {
        // TODO: return user info here, instead
        return ResponseEntity.ok(service.login(request));
    }
     */

}
