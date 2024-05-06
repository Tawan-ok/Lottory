package com.kbtg.bootcamp.posttest.lotteryticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "lottery")
public class LotteryTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("lottery_id")
    @Column(name = "lottery_id")
    private Long lottery_id;
    @JsonProperty("ticket")
    @Column(name = "ticket_number", nullable = false)
    private String ticketNumber;
    @JsonProperty("price")
    @Column(name = "price")
    private Double price;
    @JsonProperty("amount")
    @Column(name = "amount")
    private Integer amount;


    public Long getLottery_id() {
        return lottery_id;
    }

    public void setLottery_id(Long lottery_id) {
        this.lottery_id = lottery_id;
    }

    public String getTicket_number() {
        return ticketNumber;
    }

    public void setTicket_number(String ticket_number) {
        this.ticketNumber = ticket_number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
