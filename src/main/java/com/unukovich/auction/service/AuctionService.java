package com.unukovich.auction.service;

import com.unukovich.auction.model.Auction;
import java.util.List;


public interface AuctionService {
    void createAuction(Auction auction);
    Auction readAuction(int auctionId);
    void updateAuction(Auction auction);
    void deleteAuction(Auction auction);
    
    List getAllAuctions();    
}
