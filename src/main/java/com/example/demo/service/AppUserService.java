package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

public interface AppUserService {

    AuthResponse login(AuthRequest request);

    void register(AuthRequest request);
}
