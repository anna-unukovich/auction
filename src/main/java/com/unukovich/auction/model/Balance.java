package com.unukovich.auction.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity 
@Table (name="balances")
public class Balance {
    
    private int id;
    private int userId;
    private int balance;
    private Date registrationDate;
    
    public Balance(){
        id = 0;
        userId = 0;
        balance = 0;
        registrationDate = new Date();
    }
    
    public Balance(int userId, int balance, Date registrationDate){
        id = 0;
        this.userId = userId;
        this.balance = balance;
        this.registrationDate = registrationDate;
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
    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column (name="balance")
    public int getBalance() {
        return balance;
    }


    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Column (name="registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }


    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}