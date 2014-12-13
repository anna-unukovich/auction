package com.unukovich.auction.service;

import com.unukovich.auction.model.Balance;
import java.util.List;


public interface BalanceService {
    
    void createBalance(Balance balance);
    Balance readBalance(int balanceId);
    void updateBalance(Balance balance);
    void deleteBalance(Balance balance);
    
    List getAllBalances();
    
}
