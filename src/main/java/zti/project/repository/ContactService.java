package zti.project.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zti.project.Request.CreateContactRequest;
import zti.project.Request.DeleteContactRequest;
import zti.project.Request.RegisterRequest;
import zti.project.model.Contact;
import zti.project.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserContactService userContactService;

    public List<Contact> getAllContactsForUserId(Integer userId) {
        List<Contact> result = new ArrayList<>();
        for (Integer id : userContactService.getAllUserContactsIdsForUserId(userId)) {
            log.info(id.toString());
            result.add(contactRepository.findById(id).get());
        }
        return result;
    }

    public List<Contact> getAllFilteredContactsForUserId(Integer userId, String contactName) {
        List<Contact> toFilter = getAllContactsForUserId(userId);
        return toFilter.stream().filter(contact -> contact.getContactName().contains(contactName)).collect(Collectors.toList());
    }


    public List<Contact> getAllContactsForUserIdOrderByContactNameAsc(Integer userId) {
        return getAllContactsForUserId(userId).stream().sorted(Comparator.comparing(Contact::getContactName)).collect(Collectors.toList());
    }
    public List<Contact> getAllContactsForUserIdOrderByContactNameDesc(Integer userId) {
        List<Contact> result = getAllContactsForUserId(userId).stream().sorted(Comparator.comparing(Contact::getContactName)).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }
    public List<Contact> getAllContactsForUserIdOrderByContactNumberAsc(Integer userId) {
        return getAllContactsForUserId(userId).stream().sorted(Comparator.comparing(Contact::getContactNumber)).collect(Collectors.toList());
    }
    public List<Contact> getAllContactsForUserIdOrderByContactNumberDesc(Integer userId) {
        List<Contact> result = getAllContactsForUserId(userId).stream().sorted(Comparator.comparing(Contact::getContactNumber)).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }
    public ResponseEntity<String> addContactToUser(Integer userId, CreateContactRequest createContactRequest) {
            Contact contactToSave = new Contact();
            contactToSave.setContactId(getNewId());
            contactToSave.setContactName(createContactRequest.getName());
            contactToSave.setContactNumber(createContactRequest.getNumber());
            contactRepository.save(contactToSave);
            userContactService.addUserContactRelation(userId,contactToSave.getContactId());
        return ResponseEntity.ok("Contact added successfully");
    }
    public void removeContactForUser(DeleteContactRequest deleteContactRequest) {
        userContactService.removeRelationWithContactForUser(deleteContactRequest);
        contactRepository.deleteById(Integer.parseInt(deleteContactRequest.getContactId()));
    }
    public int getNewId(){
        return contactRepository.findAll().stream().mapToInt(Contact::getContactId).max().getAsInt()+1;
    }
}
