package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.userticket.UserLotteryResponse;
import com.kbtg.bootcamp.posttest.userticket.UserTicket;
import com.kbtg.bootcamp.posttest.userticket.UserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserTicketService userTicketService;

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PutMapping("/{userId}")
    public User editUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.editUser(userId, updatedUser);
    }

    @PutMapping("")
    public String greeting() {
        return "Member Resource";
    }

    @GetMapping("/{userId}/lotteries")
    public ResponseEntity<UserLotteryResponse> getUserLotteries(@PathVariable Long userId) {
        UserLotteryResponse response = userTicketService.getAllTicketsByUser(userId);
        return ResponseEntity.ok(response);
    }


}
