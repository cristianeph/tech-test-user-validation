package com.tech.userapi.component;

import com.tech.userapi.controller.request.UserRequest;
import com.tech.userapi.controller.response.UserResponse;
import com.tech.userapi.repository.models.Phone;
import com.tech.userapi.repository.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter {
    private final PasswordEncoder encoder;

    public UserResponse userToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .isActive(user.getIsActive())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .build();
    }

    public User userRequestToModel(UserRequest userRequest) {
        User adapted = User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(encoder.encode(userRequest.getPassword()))
                .token(null)
                .lastLogin(new Date())
                .createdDate(new Date())
                .modifiedDate(new Date())
                .isActive(Boolean.TRUE)
                .build();
        List<Phone> adaptedPhones = userRequest.getPhones().stream()
                .map(phone -> Phone.builder()
                        .phone(phone.getPhone())
                        .cityCode(phone.getCityCode())
                        .countryCode(phone.getCountryCode())
                        .user(adapted)
                        .build())
                .collect(Collectors.toList());
        adapted.setPhones(adaptedPhones);
        return adapted;
    }
}
