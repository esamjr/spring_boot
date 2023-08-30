package id.fazzbca.library.payloads.req;

import lombok.Data;
@Data
public class UserRequest {
    private String email;
    private String password;
    private String username;
    private int role;
}
