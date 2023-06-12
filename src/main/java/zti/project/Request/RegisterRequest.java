package zti.project.Request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterRequest {
    private String userInfo;
    private String number;
    private String password;
}
