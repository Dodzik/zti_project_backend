package zti.project.Request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginRequest {
    String number;
    String password;
}
