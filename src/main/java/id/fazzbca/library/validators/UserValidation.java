package id.fazzbca.library.validators;

import id.fazzbca.library.payloads.req.UserRequest;

public class UserValidation {
    public static boolean validateUserRequest(UserRequest userRequest) {
        return userRequest != null &&
               userRequest.getEmail() != null && !userRequest.getEmail().isEmpty() &&
               userRequest.getPassword() != null && !userRequest.getPassword().isEmpty() &&
               userRequest.getRole() > 0;
    }
}


