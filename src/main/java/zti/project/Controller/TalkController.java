package zti.project.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.project.Request.CreateCallRequest;
import zti.project.Request.IdRequest;
import zti.project.model.TalkHistory;
import zti.project.repository.TalkHistoryService;

import java.util.List;

@RestController
@RequestMapping("/talk")
@Slf4j
public class TalkController {
    @Autowired
    private TalkHistoryService talkHistoryService;

    @GetMapping("/getAllCalls/{userId}")
    public List<TalkHistory> getAllUserCalls(@PathVariable String userId) {
        return talkHistoryService.getAllCallsForUser(Integer.parseInt(userId));
    }

    @PostMapping("/createCall/{userId}")
    public ResponseEntity<String> createCallForUser(@PathVariable String userId, @RequestBody CreateCallRequest createCallRequest) {
        return talkHistoryService.addCallToUser(Integer.parseInt(userId),createCallRequest);
    }
    @DeleteMapping("/deletecall")
    public ResponseEntity<String> createCallForUser(@RequestBody IdRequest idRequest) {
        talkHistoryService.deleteCallFromHistory(idRequest);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getAllCallsWithNames/{userId}")
    public List<TalkHistory> getAllUserCallsWithNames(@PathVariable String userId) {
        return talkHistoryService.getCallsWithNames(Integer.parseInt(userId));
    }

}
