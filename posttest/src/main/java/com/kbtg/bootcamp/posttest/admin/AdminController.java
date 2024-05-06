package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lotteryticket.LotteryService;
import com.kbtg.bootcamp.posttest.lotteryticket.LotteryTicket;
import com.kbtg.bootcamp.posttest.lotteryticket.LotteryTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private LotteryService lotteryService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmin() {
        List<Admin> admins = adminService.getAllAdmin();
        return ResponseEntity.ok(admins);
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> createAdmin( @RequestBody Admin admin) {
        Admin savedAdmin = adminService.RegisterAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }


    @PostMapping("/lotteries")
    public ResponseEntity<LotteryTicketResponse> addLotteryTicket(@RequestBody LotteryTicket ticket) {
        LotteryTicket savedTicket = lotteryService.addLotteryTicket(ticket);
        LotteryTicketResponse response = new LotteryTicketResponse(savedTicket.getTicket_number());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
