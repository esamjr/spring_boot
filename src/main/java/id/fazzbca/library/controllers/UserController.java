package id.fazzbca.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.library.payloads.req.UserRequest;
import id.fazzbca.library.payloads.res.ResponseHandler;
import id.fazzbca.library.services.user.UserService;
import id.fazzbca.library.validators.UserValidation;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        if (!UserValidation.validateUserRequest(request)) {
            return ResponseHandler.responseMessage(HttpStatus.BAD_REQUEST.value(), "Invalid user data", false);
        }

        ResponseEntity<?> responseEntity = userService.addUserService(request);
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUserByEmailOrUsername(@RequestBody UserRequest request) {
        return userService.getUserByEmailOrUsernameService(request);
    }
}
