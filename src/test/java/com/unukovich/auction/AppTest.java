package com.unukovich.auction;

import com.unukovich.SpringFactory;
import com.unukovich.auction.model.User;
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

        

    }

}
