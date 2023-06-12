package zti.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zti.project.Request.IdRequest;
import zti.project.Request.LoginRequest;
import zti.project.Request.RegisterRequest;
import zti.project.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<User> validateCredentials(LoginRequest loginRequest){
        if (userRepository.existsByUserTelAndPassword(loginRequest.getNumber(), loginRequest.getPassword())) {
            return ResponseEntity.status(200).body(userRepository.findByUserTelAndPassword(loginRequest.getNumber(), loginRequest.getPassword()));
        }
        return ResponseEntity.badRequest().build();
    }
    public ResponseEntity<String> registerUser(RegisterRequest registerRequest) {
        User toRegister = new User();
        toRegister.setUserId(getNewId());
        toRegister.setUserTel(registerRequest.getNumber());
        toRegister.setUserInfo(registerRequest.getUserInfo());
        toRegister.setPassword(registerRequest.getPassword());
        toRegister.setUserInternetUsage(0);
        userRepository.save(toRegister);
        return ResponseEntity.ok("Registered successfully");
    }

    public List<User> getOneUserInfo(IdRequest idRequest){
//        return userRepository.getReferenceById(Integer.parseInt(idRequest.getId()));
        return List.of(userRepository.findByUserId(Integer.parseInt(idRequest.getId())));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int getNewId(){
        return userRepository.findAll().stream().mapToInt(User::getUserId).max().getAsInt()+1;
    }
}
