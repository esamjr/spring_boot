package id.fazzbca.library.services.user;

import org.springframework.http.ResponseEntity;

import id.fazzbca.library.payloads.req.UserRequest;

public interface UserService {

    ResponseEntity<?> addUserService(UserRequest request);

    ResponseEntity<?> getUserByEmailOrUsernameService(UserRequest request);
}
