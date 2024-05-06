package com.kbtg.bootcamp.posttest.lotteryticket;

import com.kbtg.bootcamp.posttest.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LotteryService {
    @Autowired
    private LotteryTicketRepository lotteryTicketRepository;

    public List<LotteryTicket> getAllLotteryTicket() {
        return lotteryTicketRepository.findAll();
    }

    public LotteryTicket addLotteryTicket(LotteryTicket ticket) {
        return lotteryTicketRepository.save(ticket);
    }

    public Optional<LotteryTicket> getLotteryTicketById(Long lotteryId) {
        return Optional.ofNullable(lotteryTicketRepository.findById(lotteryId).orElseThrow(() -> new NoSuchElementException("Lottery ticket not found with id" + lotteryId)));
    }
}
