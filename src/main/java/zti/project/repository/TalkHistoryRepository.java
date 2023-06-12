package zti.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zti.project.model.TalkHistory;

import java.util.List;

@Repository
public interface TalkHistoryRepository extends JpaRepository<TalkHistory, Integer> {
    List<TalkHistory> getAllByUserIdAndCaller(Integer userId, String caller);
    List<TalkHistory> getAllByUserIdAndAddressee(Integer userId, String addressee);

    List<TalkHistory> getAllByUserId(Integer userId);

    void deleteAllByUserIdAndAddressee(Integer userId, String addressee);
    void deleteAllByUserIdAndCaller(Integer userId, String caller);


}
