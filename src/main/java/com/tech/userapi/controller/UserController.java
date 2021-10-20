package com.tech.userapi.controller;

import com.tech.userapi.controller.request.UserRequest;
import com.tech.userapi.repository.models.User;
import com.tech.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping(value = "")
    ResponseEntity<User> validateUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<User>(userService.validate(userRequest), HttpStatus.CREATED);
    }
}
