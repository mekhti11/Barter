package com.example.barter.service.impl;

import com.example.barter.dto.request.LoginRequest;
import com.example.barter.dto.request.RegisterRequest;
import com.example.barter.dto.response.AuthResponse;
import com.example.barter.dto.response.UserResponse;
import com.example.barter.entity.User;
import com.example.barter.exception.UserAlreadyExistsException;
import com.example.barter.exception.UserNotFoundException;
import com.example.barter.repository.UserRepository;
import com.example.barter.security.JwtTokenProvider;
import com.example.barter.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    
    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        log.info("Registering new user: {}", request.getEmail());
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered: " + request.getEmail());
        }
        
        User user = createUserFromRequest(request);
        User savedUser = userRepository.save(user);
        
        String accessToken = jwtTokenProvider.generateToken(savedUser.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(savedUser.getEmail());
        
        log.info("User registered successfully: {}", savedUser.getEmail());
        
        return new AuthResponse(
            accessToken,
            refreshToken,
            jwtTokenProvider.getExpirationTime(),
            UserResponse.from(savedUser)
        );
    }
    
    @Override
    public AuthResponse login(LoginRequest request) {
        log.info("Authenticating user: {}", request.getEmail());
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UserNotFoundException("User not found: " + request.getEmail()));
        
        String accessToken = jwtTokenProvider.generateToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
        
        log.info("User authenticated successfully: {}", user.getEmail());
        
        return new AuthResponse(
            accessToken,
            refreshToken,
            jwtTokenProvider.getExpirationTime(),
            UserResponse.from(user)
        );
    }
    
    @Override
    public AuthResponse refreshToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }
        
        String email = jwtTokenProvider.getEmailFromToken(refreshToken);
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found: " + email));
        
        String newAccessToken = jwtTokenProvider.generateToken(user.getEmail());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
        
        return new AuthResponse(
            newAccessToken,
            newRefreshToken,
            jwtTokenProvider.getExpirationTime(),
            UserResponse.from(user)
        );
    }
    
    private User createUserFromRequest(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBio(request.getBio());
        user.setLocation(request.getLocation());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setStatus(User.UserStatus.ACTIVE);
        user.setIsEmailVerified(false);
        return user;
    }
}