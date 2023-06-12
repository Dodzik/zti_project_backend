package zti.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zti.project.Request.CreateCallRequest;
import zti.project.Request.IdRequest;
import zti.project.model.TalkHistory;

import java.util.List;

@Service
public class TalkHistoryService {
    @Autowired
    TalkHistoryRepository talkHistoryRepository;

    public List<TalkHistory> getAllReceivedCallsForUser(Integer userId, String addressee) {
        return talkHistoryRepository.getAllByUserIdAndAddressee(userId, addressee);
    }

    public List<TalkHistory> getAllOutgoingCallsForUser(Integer userId, String caller) {
        return talkHistoryRepository.getAllByUserIdAndCaller(userId, caller);
    }

    public List<TalkHistory> getAllCallsForUser(Integer userId) {
        return talkHistoryRepository.getAllByUserId(userId);
    }
    public ResponseEntity<String> addCallToUser(Integer userId, CreateCallRequest createCallRequest) {
        TalkHistory talkHistoryToCreate = new TalkHistory();
        talkHistoryToCreate.setTalkId(getNewId());
        talkHistoryToCreate.setUserId(userId);
        talkHistoryToCreate.setCaller(createCallRequest.getCaller());
        talkHistoryToCreate.setAddressee(createCallRequest.getAddressee());
        talkHistoryToCreate.setTime(createCallRequest.getTime());
        talkHistoryToCreate.setDate(createCallRequest.getDate());
        talkHistoryRepository.save(talkHistoryToCreate);
        return ResponseEntity.ok("Call added successfully");
    }
    public void deleteCallFromHistory(IdRequest idRequest) {
        talkHistoryRepository.deleteById(Integer.parseInt(idRequest.getId()));
    }

    public int getNewId(){
        return talkHistoryRepository.findAll().stream().mapToInt(TalkHistory::getTalkId).max().getAsInt()+1;
    }
}
