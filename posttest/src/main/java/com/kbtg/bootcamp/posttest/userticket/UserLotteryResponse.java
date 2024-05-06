package com.kbtg.bootcamp.posttest.userticket;

import java.util.List;

public class UserLotteryResponse {
    private List<String> tickets;
    private int count;
    private double cost;

    public UserLotteryResponse(List<String> tickets, int count, double cost) {
        this.tickets = tickets;
        this.count = count;
        this.cost = cost;
    }


    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
