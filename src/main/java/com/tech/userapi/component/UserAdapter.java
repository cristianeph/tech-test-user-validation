package com.tech.userapi.component;

import com.tech.userapi.controller.request.UserRequest;
import com.tech.userapi.repository.models.Phone;
import com.tech.userapi.repository.models.User;
import com.tech.userapi.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public User userRequestToModel(UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User adapted = User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(encoder.encode(userRequest.getPassword()))
                .token(jwt)
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
