package com.kbtg.bootcamp.posttest.userticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member/api/users_tickets")
public class UserTickerController {
    @Autowired
    private UserTicketService userTicketService;

    @GetMapping
    public List<UserTicket> getAllUserTicket() {
        return userTicketService.getAllUserTicket();
    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public ResponseEntity<?> buyLotteryTicket(@PathVariable Long userId, @PathVariable Long ticketId) {
        try {
            UserTicket userTicket = userTicketService.buyLotteryTicket(userId, ticketId);
            return ResponseEntity.ok().body(Map.of("id", userTicket.getUser_ticket_id()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<?> sellBackTicket(@PathVariable Long userId, @PathVariable Long ticketId) {
        try {
            String ticketNumber = userTicketService.sellBackTicket(userId, ticketId);
            return ResponseEntity.ok().body(Map.of("ticket", ticketNumber));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
