package zti.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zti.project.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(Integer userId);
    Boolean existsByUserTelAndPassword(String userTel, String password);
    User findByUserTelAndPassword(String userTel, String password);

}
