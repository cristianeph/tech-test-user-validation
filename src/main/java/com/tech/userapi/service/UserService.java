package com.tech.userapi.service;

import com.tech.userapi.controller.request.UserRequest;
import com.tech.userapi.repository.models.User;

public interface UserService {
    User validate(UserRequest userRequest);
}
