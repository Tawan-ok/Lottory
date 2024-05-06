package com.kbtg.bootcamp.posttest.lotteryticket;

public class LotteryTicketResponse {
    private String ticket;

    public LotteryTicketResponse(String ticket) {
        this.ticket = ticket;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
