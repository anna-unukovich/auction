package com.unukovich.auction.service;

import com.unukovich.auction.model.Bet;
import java.util.List;

public interface BetService {
    
    void createBet(Bet bet);
    Bet readBet(int betId);
    void updateBet(Bet bet);
    void deleteBet(Bet bet);
    
    List getAllBets();
    
}
