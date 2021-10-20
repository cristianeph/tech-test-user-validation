package com.tech.userapi.component;

import com.tech.userapi.controller.request.UserRequest;
import com.tech.userapi.repository.models.Phone;
import com.tech.userapi.repository.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter {
    public User userRequestToModel(UserRequest userRequest) {
        User adapted = User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
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
