package com.merchant.main.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.main.DTO.SignInRequest;
import com.merchant.main.DTO.SignUpRequest;
import com.merchant.main.Response.AuthResponse;
import com.merchant.main.Service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/admin/sign-up")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody SignUpRequest admin) throws Exception {
        return ResponseEntity.ok(authService.signUp(admin));
    }

    @PostMapping("/admin/sign-in")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody SignInRequest admin) {
        return ResponseEntity.ok(authService.signIn(admin));
    }
}
