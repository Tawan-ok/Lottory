package com.kbtg.bootcamp.posttest.userticket;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_ticket")
public class UserTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("user_ticket_id")
    @Column(name = "user_ticket_id")
    private Long id;

    @JsonProperty("user_id")
    @Column(name = "user_id")
    private Long userId;

    @JsonProperty("lottery_id")
    @Column(name = "lottery_id")
    private Long lotteryId;

    @JsonProperty("purchase_date")
    @Column(name = "purchase_date")
    private Date purchaseDate;


    public Long getUser_ticket_id() {
        return id;
    }

    public void setUser_ticket_id(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return userId;
    }

    public void setUser_id(Long userId) {
        this.userId = userId;
    }

    public Long getLottery_id() {
        return lotteryId;
    }

    public void setLottery_id(Long lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Date getPurchase_date() {
        return purchaseDate;
    }

    public void setPurchase_date(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


}
