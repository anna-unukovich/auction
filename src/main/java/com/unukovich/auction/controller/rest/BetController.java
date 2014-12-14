package com.unukovich.auction.controller.rest;

import com.unukovich.SessionBean;
import com.unukovich.SpringFactory;
import com.unukovich.auction.model.Auction;
import com.unukovich.auction.model.Bet;
import com.unukovich.auction.model.Stat;
import com.unukovich.auction.model.User;
import com.unukovich.auction.service.AuctionService;
import com.unukovich.auction.service.BetService;
import com.unukovich.auction.service.StatService;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("bets")
public class BetController {

    public BetController() {
    }

    @GET
    @Path("create")
    @Produces("text/html")
    public Response create(@QueryParam("paramPrice") int price, @QueryParam("auctionId") int auctionId) {
        java.net.URI location = null;

        SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
        User currentUser = sessionBean.getCurrentUser();

        BetService betService = (BetService) SpringFactory.getspringApplicationContext().getBean("betService");
        Bet bet = new Bet();
        bet.setId(0);
        bet.setDate(new Date());
        bet.setAuction_id(auctionId);
        bet.setPrice(price);
        bet.setUser_id(currentUser.getId());

        betService.createBet(bet);
        System.out.println("---- Create bet from Restful with ID: " + bet.getId());

        AuctionService auctionService = (AuctionService) SpringFactory.getspringApplicationContext().getBean("auctionService");
        Auction auction = auctionService.readAuction(auctionId);
        auction.setCurrentPrice(price);
        auctionService.updateAuction(auction);
        System.out.println("---- Update auction from Restful with ID: " + auction.getId());

        StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
        Stat stat = new Stat();

        stat.setId(0);
        stat.setDate(new Date());
        String descriptionString = "";
        descriptionString = "User: " + currentUser.getName() + " create bet id: " + bet.getId() + ". Price: " + bet.getPrice();
        stat.setDescription(descriptionString);
        statService.createStatr(stat);
        System.out.println("---- Create new stat from Restful. Stat id: " + stat.getId());

        try {
            location = new java.net.URI("../bet-menu.jsp");
        } catch (URISyntaxException ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.temporaryRedirect(location).build();
    }

}
