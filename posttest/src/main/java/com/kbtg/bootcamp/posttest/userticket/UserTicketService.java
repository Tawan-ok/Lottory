package com.kbtg.bootcamp.posttest.userticket;

import com.kbtg.bootcamp.posttest.lotteryticket.LotteryService;
import com.kbtg.bootcamp.posttest.lotteryticket.LotteryTicket;
import com.kbtg.bootcamp.posttest.lotteryticket.LotteryTicketRepository;
import com.kbtg.bootcamp.posttest.user.User;
import com.kbtg.bootcamp.posttest.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserTicketService {
    @Autowired
    private UserTicketRepository userTicketRepository;
    @Autowired
    private LotteryTicketRepository lotteryTicketRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private LotteryService lotteryService;


    public List<UserTicket> getAllUserTicket() {
        return userTicketRepository.findAll();
    }

    public UserLotteryResponse getAllTicketsByUser(Long userId) {
        List<UserTicket> tickets = userTicketRepository.findByUserId(userId);
        final double[] totalCost = {0};
        List<String> ticketNumbers = new ArrayList<>();

        for (UserTicket ticket : tickets) {
            Optional<LotteryTicket> lotteryTicket = lotteryTicketRepository.findById(ticket.getLottery_id());
            lotteryTicket.ifPresent(lottery -> {
                ticketNumbers.add(lottery.getTicket_number());
                totalCost[0] += lottery.getPrice();
            });
        }

        return new UserLotteryResponse(ticketNumbers, ticketNumbers.size(), totalCost[0]);
    }

    public UserTicket buyLotteryTicket(Long userId, Long lotteryId) {

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        LotteryTicket lotteyTicket = lotteryService.getLotteryTicketById(lotteryId)
                .orElseThrow(() -> new RuntimeException("Lottery ticket not found with id: " + lotteryId));

        UserTicket userTicket = new UserTicket();
        userTicket.setUser_id(userId);
        userTicket.setLottery_id(lotteryId);
        userTicket.setPurchase_date(new Date());
        return userTicketRepository.save(userTicket);
    }

    public String sellBackTicket(Long userId, Long ticketId) {
        UserTicket userTicket = userTicketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Lottery ticket not found with id: " + ticketId));
        if (!userTicket.getUser_id().equals(userId)) {
            throw new RuntimeException("Lottery ticket does not belong to the user with id: " + userId);
        }

        LotteryTicket lotteryTicket = lotteryTicketRepository.findById(userTicket.getLottery_id())
                .orElseThrow(() -> new RuntimeException("Lottery ticket not found with id: " + userTicket.getLottery_id()));
        userTicketRepository.delete(userTicket);
        return lotteryTicket.getTicket_number();
    }

}
