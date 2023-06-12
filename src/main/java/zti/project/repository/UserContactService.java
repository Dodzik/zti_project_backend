package zti.project.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.project.Request.DeleteContactRequest;
import zti.project.model.UserContact;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserContactService {
    @Autowired
    private UserContactRepository userContactRepository;


    public List<Integer> getAllUserContactsIdsForUserId (Integer userId) {
        log.info(userContactRepository.getAllByUserId(userId)
                .stream().map(UserContact::getContactId).collect(Collectors.toList()).toString());
        return userContactRepository.getAllByUserId(userId)
                .stream().map(UserContact::getContactId).collect(Collectors.toList());

    }
    public void addUserContactRelation(Integer userId, Integer contactId) {
        userContactRepository.save(new UserContact(getNewId(),userId,contactId));
    }
    public void removeRelationWithContactForUser(DeleteContactRequest deleteContactRequest) {
        log.info(Integer.parseInt(deleteContactRequest.getUserId())+" " + Integer.parseInt(deleteContactRequest.getContactId()));
//        userContactRepository.deleteUserContactByUserIdAndContactId(Integer.parseInt(deleteContactRequest.getUserId()), Integer.parseInt(deleteContactRequest.getContactId()));
        userContactRepository.deleteAll(userContactRepository.getUserContactByUserIdAndContactId(Integer.parseInt(deleteContactRequest.getUserId()), Integer.parseInt(deleteContactRequest.getContactId())));
    }


    public int getNewId(){
        return userContactRepository.findAll().stream().mapToInt(UserContact::getUserContactId).max().getAsInt()+1;
    }
}
