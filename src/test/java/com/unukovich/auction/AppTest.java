package com.unukovich.auction;

import com.unukovich.SpringFactory;
import com.unukovich.auction.model.Auction;
import com.unukovich.auction.model.Balance;
import com.unukovich.auction.model.Bet;
import com.unukovich.auction.model.Stat;
import com.unukovich.auction.model.User;
import com.unukovich.auction.service.AuctionService;
import com.unukovich.auction.service.BalanceService;
import com.unukovich.auction.service.BetService;
import com.unukovich.auction.service.StatService;
import com.unukovich.auction.service.UserService;
import java.util.Date;
import static org.junit.Assert.fail;

import org.junit.Test;

public class AppTest {

    @Test
    public void testUsers() {
        System.out.println("Working Directory = "
                + System.getProperty("user.dir"));

        // User service test!
        System.out.println("User service test.");

        UserService userService = (UserService) SpringFactory.getspringApplicationContext().getBean("userService");

        User user = new User();
        user.setId(0);
        user.setLogin("anna");
        String userName = "anna";
        user.setName(userName);
        user.setPassword("unukovich");
        user.setRegistrationDate(new Date());

        userService.createUser(user);
        int userId = user.getId();
        System.out.println("create user done. User id: " + userId);

        user = userService.readUser(userId);
        if (!user.getName().equals(userName)) {
            fail("User name from create and read operations are not equals!!!");
        } else {
            System.out.println("Read user done!");
        }

        userName = "Anna Unukovich";
        user.setName(userName);
        userService.updateUser(user);
        userId = user.getId();
        user = userService.readUser(userId);
        if (!user.getName().equals(userName)) {
            fail("User name from update and read operations are not equals!!!");
        } else {
            System.out.println("Update user done!");
        }

        String userLogin = user.getLogin();
        User tempUser = userService.getUserByLogin(userLogin);
        System.out.println("Test userService.getUserByLogin: " + tempUser.getName());

        userService.deleteUser(user);
        System.out.println("Delete user done!");

        // Test belence --------------------------------------------------------
        
        BalanceService balanceService = (BalanceService) SpringFactory.getspringApplicationContext().getBean("balanceService");

        Balance balance = new Balance();
        balance.setId(0);
        balance.setUserId(0);
        balance.setBalance(1000);
        balance.setRegistrationDate(new Date());

        balanceService.createBalance(balance);
        int balanceID = balance.getId();
        System.out.println("create balance done. Balance id: " + userId);
        
        balanceService.deleteBalance(balance);
        System.out.println("Delete balance done!");
        
        
        // Test belence --------------------------------------------------------
        
        AuctionService auctionService = (AuctionService) SpringFactory.getspringApplicationContext().getBean("auctionService");

        Auction auction = new Auction();
        auction.setId(0);
        auction.setName("Auction 0");
        auction.setDescription("Auction description 0");
        auction.setCreatorId(0);
        auction.setStartPrice(100);
        auction.setCurrentPrice(100);
        auction.setCreatedDate(new Date());
        auction.setIsFinal(0);
        auction.setWinDate(new Date());
        auction.setWinDate(null);          
        
              

        auctionService.createAuction(auction);
        int auctionId = auction.getId();
        System.out.println("create auction done. Auction id: " + auctionId);
        
        
        // Test bet  -----------------------------------------------------------
        
        BetService betService = (BetService) SpringFactory.getspringApplicationContext().getBean("betService");

        Bet bet = new Bet();
        bet.setId(userId);
        bet.setUser_id(0);
        bet.setAuction_id(0);
        Integer betPrice = 150;
        bet.setPrice(betPrice);
        
        auction = auctionService.readAuction(auctionId);
        auction.setCurrentPrice(betPrice);
        
        bet.setDate(new Date());        
              

        betService.createBet(bet);
        int betId = bet.getId();
        System.out.println("create bet done. Bet id: " + betId);
        
        betService.deleteBet(bet);
        System.out.println("Delete bet done!");
        
        auctionService.deleteAuction(auction);
        System.out.println("Delete auction done!"); 
        
        
        // Test stat  ----------------------------------------------------------
        
        StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");

        Stat stat = new Stat();
        stat.setId(userId);
        stat.setDescription("Test stat description.");
        stat.setDate(new Date());
              

        statService.createStatr(stat);
        int statId = stat.getId();
        System.out.println("create stat done. Stat id: " + statId);
        
        statService.deleteStat(stat);
        System.out.println("Delete stat done!"); 
    }

}
