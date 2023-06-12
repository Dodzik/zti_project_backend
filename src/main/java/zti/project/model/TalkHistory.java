package zti.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "talk_history")
@Data
public class TalkHistory implements Serializable {
    @Id
    private Integer talkId;
    private Integer userId;
    private String caller;
    private String addressee;
    private Integer time;
    private Timestamp date;

}
