package zti.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zti.project.model.UserContact;

import java.util.List;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Integer> {

    List<UserContact> getAllByUserId(Integer userId);
    List<UserContact> getUserContactByUserIdAndContactId(Integer userId, Integer contactId);
}
