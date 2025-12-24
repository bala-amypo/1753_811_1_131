package com.example.demo.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.AppUser;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AppUserServiceImpl(AppUserRepository userRepository,
                              PasswordEncoder passwordEncoder,
                              JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AppUser register(String email, String password, String role) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new BadRequestException("Email must be unique");
        }

        AppUser user = AppUser.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(role)
                .active(true)
                .build();

        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(String email, String password) {

        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        String token = jwtTokenProvider.createToken(user);

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
