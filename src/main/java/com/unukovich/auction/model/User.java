package com.unukovich.auction.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity 
@Table (name="users")
public class User {
    
    public static final String DEFAULT_NAME = "default name";
    public static final String DEFAULT_LOGIN = "default login";
    public static final String DEFAULT_PASSWORD = "default password";
    public static final String DEFAULT_ADMIN = "user";
    
    private int id;
    private String name;
    private String login;
    private String password;
    private Date registrationDate;
    private String admin;
    
    public User(){
        id = 0;
        name = DEFAULT_NAME;
        login = DEFAULT_LOGIN;
        password = DEFAULT_PASSWORD;
        registrationDate = new Date();
        admin = DEFAULT_ADMIN;
    }
    
    public User(String name, String login, String password, String admin){
        id = 0;
        this.name = name;
        this.login = login;
        this.password = password;
        registrationDate = new Date();
        this.admin = admin;
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

    @Column(name="login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name="admin")
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
    
    
}