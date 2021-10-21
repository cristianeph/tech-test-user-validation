package com.tech.userapi.service;

import com.tech.userapi.component.UserAdapter;
import com.tech.userapi.controller.request.UserRequest;
import com.tech.userapi.controller.response.UserResponse;
import com.tech.userapi.exception.EmailExistsException;
import com.tech.userapi.repository.UserRepository;
import com.tech.userapi.repository.models.User;
import com.tech.userapi.util.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public UserResponse validate(UserRequest userRequest) {
        log.debug("receiving {}", userRequest);
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailExistsException();
        }
        User adapted = userAdapter.userRequestToModel(userRequest);
        User saved = userRepository.save(adapted);
        User accepted = this.generateToken(saved, userRequest);
        log.debug("accepting {}", accepted.getToken());
        return userAdapter.userToResponse(accepted);
    }

    @Override
    public User generateToken(User user, UserRequest userRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), userRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        user.setToken(jwtUtils.generateJwtToken(authentication));
        User saved = userRepository.save(user);
        return saved;
    }
}
