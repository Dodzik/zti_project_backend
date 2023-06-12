package zti.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

    @Id
    private Integer userId;
    private String userInfo;
    private int userInternetUsage;
    private String userTel;
    private String password;

}
