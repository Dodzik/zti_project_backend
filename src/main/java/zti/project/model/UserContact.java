package zti.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContact {
    @Id
    private Integer userContactId;
    private Integer userId;
    private Integer contactId;


}
