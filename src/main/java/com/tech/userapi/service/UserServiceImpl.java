package com.tech.userapi.service;

import com.tech.userapi.controller.request.UserRequest;
import com.tech.userapi.repository.UserRepository;
import com.tech.userapi.repository.models.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User validate(UserRequest userRequest) {
        return null;
    }
}
