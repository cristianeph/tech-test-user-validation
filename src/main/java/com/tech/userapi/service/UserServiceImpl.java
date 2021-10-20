package com.tech.userapi.service;

import com.tech.userapi.component.UserAdapter;
import com.tech.userapi.controller.request.UserRequest;
import com.tech.userapi.repository.UserRepository;
import com.tech.userapi.repository.models.User;
import com.tech.userapi.util.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Override
    public User validate(UserRequest userRequest) {
        log.debug("receiving {}", userRequest);
        User adapted = userAdapter.userRequestToModel(userRequest);
        User saved = userRepository.save(adapted);
        log.debug("saving {}", saved.getToken());
        return saved;
    }
}
