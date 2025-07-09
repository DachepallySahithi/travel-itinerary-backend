package com.travel.itinerary.service;

import com.travel.itinerary.dto.AuthResponse;
import com.travel.itinerary.dto.LoginRequest;
import com.travel.itinerary.dto.SignUpRequest;
import com.travel.itinerary.dto.UserProfileRequest;
import com.travel.itinerary.model.entity.User;
import com.travel.itinerary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtHelper jwtHelper;

    public AuthResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username already Exists");
        }
        User user = User.builder().username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .phoneNumber(signUpRequest.getPhoneNumber()).build();
        User savedUser = userRepository.save(user);
        String token = jwtHelper.generateToken(savedUser.getUsername());
        return new AuthResponse(
                token,
                "Bearer",
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getRole()
        );
    }

    public AuthResponse Login(LoginRequest loginRequest) throws BadRequestException {
        User user = userRepository.findByUsername(loginRequest.getUserName()).orElse(null);
        if (user == null)
            throw new BadRequestException("User with username does not exist");
        String token = jwtHelper.generateToken(user.getUsername());
        return new AuthResponse(
                token,
                "Bearer",
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole()
        );
    }

    public User getUserByUserName(UserProfileRequest userProfileRequest) {
        return userRepository.findByUsername(userProfileRequest.getUserName()).orElse(null);
    }

    public Boolean existByUserName(String userName) {
        return userRepository.existsByUsername(userName);
    }

    public Boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
