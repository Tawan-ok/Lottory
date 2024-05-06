package com.kbtg.bootcamp.posttest.userticket;

import com.kbtg.bootcamp.posttest.lotteryticket.LotteryService;
import com.kbtg.bootcamp.posttest.lotteryticket.LotteryTicket;
import com.kbtg.bootcamp.posttest.lotteryticket.LotteryTicketRepository;
import com.kbtg.bootcamp.posttest.user.User;
import com.kbtg.bootcamp.posttest.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserTicketServiceTest {
    @Mock
    private UserTicketRepository userTicketRepository;

    @Mock
    private LotteryTicketRepository lotteryTicketRepository;

    @Mock
    private UserService userService;

    @Mock
    private LotteryService lotteryService;

    @InjectMocks
    private UserTicketService userTicketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUserTicket() {
        UserTicket ticket1 = new UserTicket();
        UserTicket ticket2 = new UserTicket();
        when(userTicketRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));

        var result = userTicketService.getAllUserTicket();

        assertEquals(2, result.size());
    }

    @Test
    void buyLotteryTicket() {
        Long userId = 1L;
        Long lotteryId = 1L;
        User user = new User();
        LotteryTicket lotteryTicket = new LotteryTicket();

        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        when(lotteryService.getLotteryTicketById(lotteryId)).thenReturn(Optional.of(lotteryTicket));
        when(userTicketRepository.save(any(UserTicket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserTicket newTicket = userTicketService.buyLotteryTicket(userId, lotteryId);

        assertNotNull(newTicket.getPurchase_date());
        assertEquals(userId, newTicket.getUser_id());
        assertEquals(lotteryId, newTicket.getLottery_id());
    }

    @Test
    void sellBackTicket() {
        Long userId = 1L;
        Long ticketId = 1L;
        UserTicket userTicket = new UserTicket();
        userTicket.setUser_id(userId);
        Long lotteryId = 1L;
        userTicket.setLottery_id(lotteryId);
        LotteryTicket lotteryTicket = new LotteryTicket();
        lotteryTicket.setTicket_number("123456");

        when(userTicketRepository.findById(ticketId)).thenReturn(Optional.of(userTicket));
        when(lotteryTicketRepository.findById(userTicket.getLottery_id())).thenReturn(Optional.of(lotteryTicket));

        String ticketNumber = userTicketService.sellBackTicket(userId, ticketId);

        assertEquals("123456", ticketNumber);
    }


    @Test
    void getAllTicketsByUser() {
        Long userId = 1L;

        UserTicket ticket1 = new UserTicket();
        ticket1.setLottery_id(100L);
        UserTicket ticket2 = new UserTicket();
        ticket2.setLottery_id(200L);
        List<UserTicket> userTickets = Arrays.asList(ticket1, ticket2);

        LotteryTicket lotteryTicket1 = new LotteryTicket();
        lotteryTicket1.setTicket_number("TicketNumber100");
        lotteryTicket1.setPrice(50.0);
        LotteryTicket lotteryTicket2 = new LotteryTicket();
        lotteryTicket2.setTicket_number("TicketNumber200");
        lotteryTicket2.setPrice(75.0);

        when(userTicketRepository.findByUserId(userId)).thenReturn(userTickets);
        when(lotteryTicketRepository.findById(100L)).thenReturn(Optional.of(lotteryTicket1));
        when(lotteryTicketRepository.findById(200L)).thenReturn(Optional.of(lotteryTicket2));

        UserLotteryResponse response = userTicketService.getAllTicketsByUser(userId);

        assertNotNull(response);
        assertEquals(2, response.getTickets().size());
        assertTrue(response.getTickets().contains("TicketNumber100"));
        assertTrue(response.getTickets().contains("TicketNumber200"));
        assertEquals(125.0, response.getCost());
        assertEquals(2, response.getCount());
    }

}