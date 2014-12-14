package com.unukovich.auction.service;

import com.unukovich.auction.model.Stat;
import java.util.List;


public interface StatService {
    
    void createStatr(Stat stat);
    Stat readStat(int statId);
    void updateStat(Stat stat);
    void deleteStat(Stat stat);
    
    List getAllStats();
    
}
