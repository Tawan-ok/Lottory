package com.kbtg.bootcamp.posttest.lotteryticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lotteries")
public class LotteryController {
    @Autowired
    private LotteryService lotteryService;

    @GetMapping("")
    public ResponseEntity<Map<String, List<String>>> getAllLotteryTickets() {
        List<LotteryTicket> tickets = lotteryService.getAllLotteryTicket();
        List<String> ticketNumbers = tickets.stream()
                .map(LotteryTicket::getTicket_number)
                .collect(Collectors.toList());
        Map<String, List<String>> response = new HashMap<>();
        response.put("tickets", ticketNumbers);
        return ResponseEntity.ok(response);
    }
}
