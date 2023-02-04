package com.ben.reviews.auth;

import com.ben.reviews.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
       return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping("/userinfo")
    public ResponseEntity<AuthenticationResponse> testLoggedInUser(
            @RequestBody AuthenticationRequest request
    ) {
        // TODO: return user info here, instead
        return ResponseEntity.ok(service.login(request));
    }

}
