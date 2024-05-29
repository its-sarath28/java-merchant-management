package com.merchant.main.Service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.merchant.main.DTO.SignInRequest;
import com.merchant.main.DTO.SignUpRequest;
import com.merchant.main.Exception.EntityAlreadyExistsException;
import com.merchant.main.Exception.NotFoundException;
import com.merchant.main.Model.Admin;
import com.merchant.main.Repository.AdminRepository;
import com.merchant.main.Response.AuthResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponse signUp(SignUpRequest admin) throws Exception {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            throw new EntityAlreadyExistsException("Email already Exists");
        }

        Admin createdAdmin = new Admin();

        createdAdmin.setFull_name(admin.getFull_name());
        createdAdmin.setEmail(admin.getEmail());
        createdAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        createdAdmin.setCreated_at(LocalDateTime.now());

        createdAdmin = adminRepository.save(createdAdmin);
        String token = jwtService.generateToken(createdAdmin);

        return new AuthResponse(token);
    }

    public AuthResponse signIn(SignInRequest admin) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid credentials");
        }

        Admin userToLogin = adminRepository.findByEmail(admin.getEmail())
                .orElseThrow(() -> new NotFoundException("general", "Invalid credentials"));

        String token = jwtService.generateToken(userToLogin);
        return new AuthResponse(token);
    }
}
