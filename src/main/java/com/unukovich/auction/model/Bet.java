package com.unukovich.auction.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity 
@Table (name="bets")
public class Bet {
    
    private int id;
    private int user_id;
    private int auction_id;
    private int price;
    private Date date;
    
    public Bet(){
        id = 0;
        user_id = 0;
        auction_id = 0;
        price = 0;
        date = new Date();
    }
    
    public Bet (int user_id, int auction_id, int price){
        id = 0;
        this.user_id = user_id;
        this.auction_id = auction_id;
        this.price = price;
        date = new Date();
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column (name="user_id")
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Column (name="auction_id")
    public int getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(int auction_id) {
        this.auction_id = auction_id;
    }

    @Column (name="price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column (name="date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
       
}
