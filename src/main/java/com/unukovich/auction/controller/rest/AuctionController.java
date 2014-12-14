package com.unukovich.auction.controller.rest;

import com.unukovich.SessionBean;
import com.unukovich.SpringFactory;
import com.unukovich.auction.model.Auction;
import com.unukovich.auction.model.Stat;
import com.unukovich.auction.model.User;
import com.unukovich.auction.service.AuctionService;
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

@Path("auctions")
public class AuctionController {

    public AuctionController() {
    }

    @GET
    @Path("create")
    @Produces("text/html")
    public Response create(@QueryParam("paramName") String name, @QueryParam("paramDescription") String description, @QueryParam("paramStartPrice") Integer startPrice) {
        java.net.URI location = null;

        SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
        User currentUser = sessionBean.getCurrentUser();

        AuctionService auctionService = (AuctionService) SpringFactory.getspringApplicationContext().getBean("auctionService");
        Auction auction = new Auction();

        auction.setId(0);
        auction.setCreatorId(currentUser.getId());
        auction.setName(name);
        auction.setDescription(description);
        auction.setStartPrice(startPrice);
        auction.setCurrentPrice(startPrice);
        auction.setCreatedDate(new Date());
        auction.setIsFinal(0);
        auction.setWinnerId(0);
        auction.setWinDate(null);

        auctionService.createAuction(auction);
        System.out.println("---- Create auction from Restful with ID: " + auction.getId());

        StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
        Stat stat = new Stat();

        stat.setId(0);
        stat.setDate(new Date());
        String descriptionString = "";
        descriptionString = "User: " + currentUser.getName() + " create auction: " + auction.getName();
        stat.setDescription(descriptionString);
        statService.createStatr(stat);
        System.out.println("---- Create new stat from Restful. Stat id: " + stat.getId());

        try {
            location = new java.net.URI("../myauctions-menu.jsp");
        } catch (URISyntaxException ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.temporaryRedirect(location).build();
    }

}
