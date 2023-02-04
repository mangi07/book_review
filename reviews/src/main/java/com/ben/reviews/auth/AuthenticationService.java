package com.ben.reviews.auth;

import com.ben.reviews.security.JwtService;
import com.ben.reviews.user.UserRepository;
import com.ben.reviews.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        String name = request.getName();
        if (userRepository.existsByName(name)) {
            //return new RegistrationConfirmation(name, "Error: Username already taken.");
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "User already exists."
            );
        }
        var user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByName(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
