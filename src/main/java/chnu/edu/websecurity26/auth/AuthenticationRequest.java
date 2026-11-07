package chnu.edu.websecurity26.auth;

/*
 @author Lenovo
 @project web-security26
 @class AuthenticationRequest
 @version 1.0.0
 @since 11/7/2026 - 14.20
*/

import lombok.Data;
import lombok.NonNull;
@Data
public class AuthenticationRequest {
    @NonNull
    private String login;
    @NonNull
    private String password;
}