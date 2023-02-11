package com.ben.reviews.book_library;

import com.ben.reviews.models.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository repository;


    public LibraryResponse addBook(LibraryRequest request) throws ResponseStatusException {
        Integer bookId = request.getBookId();
        if ( repository.findById(bookId).isEmpty() ) {
        }
        /*var user = User.builder()
                .name(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);*/

        return LibraryResponse.builder()
                //.token(jwtToken)
                .token("filler")
                .build();
    }

    public LibraryResponse searchByIsbn(LibraryRequest request) throws ResponseStatusException {
        /*String passwordFromRequest = request.getPassword();
        var userName = request.getUsername();
        // check name
        if ( ! userRepository.existsByName(userName)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "User does not exists."
            );
        }
        // assuming user will be found...
        var user = userRepository.findByName(userName).get();
        String passwordFromDbEncrypted = user.getPassword();
        String passwordFromRequestEncrypted = passwordEncoder.encode(passwordFromRequest);
        // check password
        if ( ! passwordEncoder.matches(passwordFromRequest, passwordFromDbEncrypted) ) {
            // here, the passwords do not match, so...
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Wrong password."
            );
        }
        var jwtToken = jwtService.generateToken(user);*/
        return LibraryResponse
                .builder()
                .build();
    }
}
