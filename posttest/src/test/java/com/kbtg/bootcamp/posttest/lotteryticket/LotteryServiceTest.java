package com.kbtg.bootcamp.posttest.lotteryticket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LotteryServiceTest {

    @Mock
    private LotteryTicketRepository lotteryTicketRepository;

    @InjectMocks
    private LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllLotteryTicket() {
        LotteryTicket ticket1 = new LotteryTicket();
        LotteryTicket ticket2 = new LotteryTicket();
        when(lotteryTicketRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));

        List<LotteryTicket> tickets = lotteryService.getAllLotteryTicket();

        assertEquals(2, tickets.size());
    }

    @Test
    void addLotteryTicket() {
        LotteryTicket newTicket = new LotteryTicket();
        when(lotteryTicketRepository.save(any(LotteryTicket.class))).thenReturn(newTicket);

        LotteryTicket savedTicket = lotteryService.addLotteryTicket(newTicket);

        assertEquals(newTicket, savedTicket);
    }

    @Test
    void getLotteryTicketById() {
        Long lotteryId = 1L;
        Optional<LotteryTicket> ticket = Optional.of(new LotteryTicket());
        when(lotteryTicketRepository.findById(lotteryId)).thenReturn(ticket);

        Optional<LotteryTicket> foundTicket = lotteryService.getLotteryTicketById(lotteryId);

        assertTrue(foundTicket.isPresent());
        assertEquals(ticket, foundTicket);
    }

    @Test
    void getLotteryTicketByIdNotFound() {
        Long lotteryId = 1L;
        when(lotteryTicketRepository.findById(lotteryId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            lotteryService.getLotteryTicketById(lotteryId).orElseThrow(() -> new NoSuchElementException("Lottery ticket not found with id" + lotteryId));
        });

        String expectedMessage = "Lottery ticket not found with id" + lotteryId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}