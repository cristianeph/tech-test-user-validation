package com.tech.userapi.service;

import com.tech.userapi.controller.request.UserRequest;
import com.tech.userapi.controller.response.UserResponse;
import com.tech.userapi.repository.models.User;

public interface UserService {
    UserResponse validate(UserRequest userRequest);
    User generateToken(User user, UserRequest userRequest);
}
