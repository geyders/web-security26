package chnu.edu.websecurity26.auth;


/*
  @author   george
  @project   security25
  @class  AuthenticationRequest
  @version  1.0.0
  @since 15.04.25 - 20.27
*/

import lombok.Data;
import lombok.NonNull;
@Data
public class AuthenticationRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}