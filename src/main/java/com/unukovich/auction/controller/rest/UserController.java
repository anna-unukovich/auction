package com.unukovich.auction.controller.rest;

import com.unukovich.SessionBean;
import com.unukovich.SpringFactory;
import com.unukovich.auction.model.Balance;
import com.unukovich.auction.model.Stat;
import com.unukovich.auction.model.User;
import com.unukovich.auction.service.BalanceService;
import com.unukovich.auction.service.StatService;
import com.unukovich.auction.service.UserService;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("users")
public class UserController {

    public UserController() {
    }

    @GET
    @Path("login")
    @Produces("text/html")
    public Response login(@QueryParam("paramEmail") String login) {
        boolean isUserExist = false;
        java.net.URI location = null;

        UserService userService = (UserService) SpringFactory.getspringApplicationContext().getBean("userService");
        isUserExist = userService.CheckUser(login);

        if (isUserExist == true) {
            try {
                SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
                User user = userService.getUserByLogin(login);
                sessionBean.setCurrentUser(user);
                location = new java.net.URI("../main-menu.jsp");
            } catch (URISyntaxException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        try {
                location = new java.net.URI("../login-error.jsp");
            } catch (URISyntaxException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }

        return Response.temporaryRedirect(location).build();
    }
    
    @GET
    @Path("create")
    @Produces("text/html")
    public Response create(@QueryParam("paramLogin") String login, @QueryParam("paramName") String name,  @QueryParam("paramPassword") String password, @QueryParam("paramRole") String role) {
        java.net.URI location = null;        
        
        try {
            User user = new User();
            user.setId(0);
            user.setLogin(login);
            user.setName(name);
            user.setPassword(password);           
            Date date = new Date();
            user.setRegistrationDate(date);            
            user.setAdmin(role);
            
            UserService userService = (UserService) SpringFactory.getspringApplicationContext().getBean("userService");
            userService.createUser(user);
            
            System.out.println("---- Create new user from Restful. User id: " + user.getId());
            
            SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
            User currentUser = sessionBean.getCurrentUser();
            
            BalanceService balanceService = (BalanceService) SpringFactory.getspringApplicationContext().getBean("balanceService");
            Balance balance = new Balance();
            balance.setId(0);
            balance.setUserId(user.getId());
            balance.setRegistrationDate(date);
            balance.setBalance(1000);
            
            balanceService.createBalance(balance);
            System.out.println("---- Create new balance from Restful. Balance id: " + balance.getId());
            
            StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
            Stat stat = new Stat();
            
            stat.setId(0);
            stat.setDate(new Date());
            String descriptionString = "";
            descriptionString = "User: " + currentUser.getName() + " create user: " + user.getName();
            stat.setDescription(descriptionString);
            statService.createStatr(stat);
            System.out.println("---- Create new stat from Restful. Stat id: " + stat.getId());
            
            stat = new Stat();
            stat.setId(0);
            stat.setDate(new Date());
            descriptionString = "";
            descriptionString = "User: " + currentUser.getName() + " create balance. Id: " + balance.getId() + ". Balance: " + balance.getBalance();
            stat.setDescription(descriptionString);
            statService.createStatr(stat);
            System.out.println("---- Create new stat from Restful. Stat id: " + stat.getId());           
                      
            location = new java.net.URI("../user-menu.jsp");

        } catch (URISyntaxException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.temporaryRedirect(location).build();
    }
}
