package com.unukovich.auction.service.implementation;

import com.unukovich.HibernateFactory;
import com.unukovich.auction.model.User;
import com.unukovich.auction.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    public UserServiceImplementation() {

    }

    @Override
    public void createUser(User user) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User readUser(int userId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user;
        user = (User) session.get(User.class, userId);
        session.close();

        return user;
    }

    @Override
    public void updateUser(User user) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteUser(User user) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public List getAllUsers(){
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List usersList = session.createCriteria(User.class).list();
        session.close();

        return usersList;
    }
    
    @Override
    public User getUserByLogin(String login){
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<User> usersList = (ArrayList<User>) session.createCriteria(User.class).list();
        session.close();
        
        User resultUser = null;
        for (User user : usersList) {
            if (user.getLogin().equals(login)) {
                resultUser = user;
            }
        }
        
        return resultUser;
    }

    @Override
    public boolean CheckUser(String login){
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<User> usersList = (ArrayList<User>) session.createCriteria(User.class).list();
        session.close();
        
        boolean resultFlag = false;
        for (User user : usersList) {
            if (user.getLogin().equals(login)) {
                resultFlag = true;
            }
        }
        
        return resultFlag;
    }
    
}
