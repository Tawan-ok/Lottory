package com.kbtg.bootcamp.posttest.lotteryticket;

import com.kbtg.bootcamp.posttest.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LotteryTicketRepository extends JpaRepository<LotteryTicket, Long> {
}
