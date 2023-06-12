package zti.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "contacts")
@Data
public class Contact implements Serializable {
    @Id
    private Integer contactId;

    private String contactName;
    private String contactNumber;
}
