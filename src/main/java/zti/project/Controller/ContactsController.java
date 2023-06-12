package zti.project.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.project.Request.CreateContactRequest;
import zti.project.Request.DeleteContactRequest;
import zti.project.model.Contact;
import zti.project.repository.ContactService;
import zti.project.repository.UserRepository;
import zti.project.repository.UserService;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@Slf4j
public class ContactsController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUserContacts/{userId}")
    public List<Contact> getAllUserContacts(@PathVariable String userId) {
        return contactService.getAllContactsForUserId(Integer.parseInt(userId));
    }
    @GetMapping("/getAllUserContacts/{userId}/sortedByNameASC")
    public List<Contact> getAllUserContactsSortedByContactName(@PathVariable String userId) {
        return contactService.getAllContactsForUserIdOrderByContactNameAsc(Integer.parseInt(userId));
    }
    @GetMapping("/getAllUserContacts/{userId}/sortedByNameDESC")
    public List<Contact> getAllUserContactsSortedByContactNameDesc(@PathVariable String userId) {
        return contactService.getAllContactsForUserIdOrderByContactNameDesc(Integer.parseInt(userId));
    }
    @GetMapping("/getAllUserContacts/{userId}/sortedByNumberASC")
    public List<Contact> getAllUserContactsSortedByContactNumber(@PathVariable String userId) {
        return contactService.getAllContactsForUserIdOrderByContactNumberAsc(Integer.parseInt(userId));
    }
    @GetMapping("/getAllUserContacts/{userId}/sortedByNumberDESC")
    public List<Contact> getAllUserContactsSortedByContactNumberDesc(@PathVariable String userId) {
        return contactService.getAllContactsForUserIdOrderByContactNumberDesc(Integer.parseInt(userId));
    }
    @PostMapping("/add/contact/{userId}")
    public ResponseEntity<String> addContactForUser(@PathVariable String userId, @RequestBody CreateContactRequest createContactRequest) {
        return contactService.addContactToUser(Integer.parseInt(userId),createContactRequest);
    }
    @DeleteMapping("/delete/contact")
    public ResponseEntity<String> addContactForUser(@RequestBody DeleteContactRequest deleteContactRequest) {
        contactService.removeContactForUser(deleteContactRequest);
        return ResponseEntity.status(200).body("Success");
    }
    @GetMapping("/getAllUserContacts/{userId}/filteredByName/{contactName}")
    public List<Contact> getAllUserContactsFilteredByName(@PathVariable String userId, @PathVariable String contactName) {
        return contactService.getAllFilteredContactsForUserId(Integer.parseInt(userId),contactName);
    }

}
