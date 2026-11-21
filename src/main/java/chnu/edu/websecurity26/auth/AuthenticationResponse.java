package chnu.edu.websecurity26.auth;


import lombok.Builder;
import lombok.Data;

/*
  @author   george
  @project   security25
  @class  AuthenticationResponse
  @version  1.0.0
  @since 15.04.25 - 20.32
*/
@Data
@Builder
public class AuthenticationResponse {
    private String token;
}