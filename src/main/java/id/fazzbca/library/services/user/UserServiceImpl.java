package id.fazzbca.library.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.library.models.User;
import id.fazzbca.library.payloads.req.UserRequest;
import id.fazzbca.library.payloads.res.ResponseHandler;
import id.fazzbca.library.repositories.UserRepository;
import id.fazzbca.library.validators.UserValidation;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> addUserService(UserRequest request) {
        if (!UserValidation.validateUserRequest(request)) {
            return ResponseHandler.responseMessage(HttpStatus.BAD_REQUEST.value(), "Invalid user data", false);
        }

        User user = new User(request.getEmail(), request.getPassword());
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());

        userRepository.save(user);

        return ResponseHandler.responseData(HttpStatus.CREATED.value(), "User successfully added!", null);
    }

    @Override
    public ResponseEntity<?> getUserByEmailOrUsernameService(UserRequest request) {
        User user = userRepository.findByEmailOrUsername(request.getEmail());
        if (user == null) {
            return ResponseHandler.responseMessage(HttpStatus.NOT_FOUND.value(), "User not found", false);
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseHandler.responseMessage(HttpStatus.UNAUTHORIZED.value(), "Invalid password", false);
        }

        return ResponseHandler.responseData(HttpStatus.OK.value(), "Login successful", user);
    }
}

