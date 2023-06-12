package zti.project.Request;

import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class CreateCallRequest {
    String caller;
    String addressee;
    Integer time;
    Timestamp date;
}
