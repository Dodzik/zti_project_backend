package zti.project.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.project.Request.IdRequest;
import zti.project.Request.LoginRequest;
import zti.project.Request.RegisterRequest;
import zti.project.model.Contact;
import zti.project.model.User;
import zti.project.repository.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @GetMapping("/allusers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        return userService.validateCredentials(loginRequest);
    }
    @GetMapping("/getAllUserContacts/{userId}")
    public List<Contact> getAllUserContacts(@PathVariable String userId) {
        return contactService.getAllContactsForUserId(Integer.parseInt(userId));
    }
    @PostMapping("/getUserInfo")
    public List<User> getUserInfo(@RequestBody IdRequest idRequest) {
        log.info("test");
        log.info(userService.getOneUserInfo(idRequest).toString());
        return userService.getOneUserInfo(idRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> getAllUserContacts(@RequestBody RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }


}
