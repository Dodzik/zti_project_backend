package zti.project.Request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateContactRequest {
    String name;
    String number;
}
