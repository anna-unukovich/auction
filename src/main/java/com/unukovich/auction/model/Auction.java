package com.unukovich.auction.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity 
@Table (name="auctions")
public class Auction {
    
    public static final String DEFAULT_NAME = "default name";
    public static final String DEFAULT_DESCRIPTION = "default description";
    
    private int id;
    private String name;
    private String description;
    private int creatorId;
    private int startPrice;
    private int currentPrice;
    private Date createdDate;
    private int isFinal;
    private int winnerId;
    private Date winDate;
    
    public Auction(){
        id = 0;
        name = DEFAULT_NAME;
        description = DEFAULT_DESCRIPTION;
        creatorId = 0;
        startPrice = 0;
        currentPrice = 0;
        createdDate = new Date();
        isFinal = 0;
        winnerId = 0;
        winDate = new Date();
    }
    
    public Auction(String name, String description, int creatorId, int startPrice){
        id = 0;
        this.name = name;
        this.description = DEFAULT_DESCRIPTION;
        this.creatorId = creatorId;
        this.startPrice = startPrice;
        this.currentPrice = this.startPrice;
        createdDate = new Date();
        isFinal = 0;
        winnerId = 0;
        winDate = new Date();    
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

    @Column (name="name")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Column (name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column (name="creator_id")
    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    @Column (name="start_price")
    public int getStartPrice() {
        return startPrice;
    }


    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    @Column (name="current_price")
    public int getCurrentPrice() {
        return currentPrice;
    }

    
    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Column (name="created_date")
    public Date getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column (name="is_final")    
    public int getIsFinal() {
        return isFinal;
    }


    public void setIsFinal(int isFinal) {
        this.isFinal = isFinal;
    }    
    
    @Column (name="winner_id") 
    public int getWinnerId() {
        return winnerId;
    }
  
    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    @Column (name="win_date")
    public Date getWinDate() {
        return winDate;
    }


    public void setWinDate(Date winDate) {
        this.winDate = winDate;
    }
    
}
