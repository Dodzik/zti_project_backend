package zti.project.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zti.project.Request.CreateCallRequest;
import zti.project.Request.IdRequest;
import zti.project.model.Contact;
import zti.project.model.TalkHistory;
import zti.project.model.User;

import java.util.List;

@Slf4j
@Service
public class TalkHistoryService {
    @Autowired
    TalkHistoryRepository talkHistoryRepository;
    @Autowired
    UserContactService userContactService;
    @Autowired
    ContactService contactService;
    @Autowired
    UserRepository userRepository;

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

    public List<TalkHistory> getCallsWithNames(Integer userId){

        List<TalkHistory> userCalls = talkHistoryRepository.getAllByUserId(userId);
        List<Contact> userContacts = contactService.getAllContactsForUserId(userId);
        User user = userRepository.findByUserId(userId);

        for (TalkHistory talkHistory: userCalls) {
            for (Contact contact: userContacts) {
                if (talkHistory.getCaller().equals(contact.getContactNumber())) {
                    talkHistory.setCaller(contact.getContactName());
                }
                if (talkHistory.getAddressee().equals(contact.getContactNumber())) {
                    talkHistory.setAddressee(contact.getContactName());
                }
                if (talkHistory.getCaller().equals(user.getUserTel())) {
                    talkHistory.setCaller("YOU");
                }
                if (talkHistory.getAddressee().equals(user.getUserTel())) {
                    talkHistory.setAddressee("YOU");
                }
            }
        }

        return userCalls;
    }
}
