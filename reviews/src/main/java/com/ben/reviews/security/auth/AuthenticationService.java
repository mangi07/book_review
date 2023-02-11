package com.ben.reviews.security.auth;

import com.ben.reviews.security.jwt.JwtService;
import com.ben.reviews.models.user.UserRepository;
import com.ben.reviews.models.user.User;
import com.ben.reviews.security.register.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    record RegistrationConfirmation(String username, String message){}

    public AuthenticationResponse register(RegisterRequest request) throws ResponseStatusException {
        String name = request.getUsername();
        if (userRepository.existsByName(name)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "{\"error\":\"User already exists.\"}"
            );
        }
        var user = User.builder()
                .name(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) throws ResponseStatusException {
        String passwordFromRequest = request.getPassword();
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
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
