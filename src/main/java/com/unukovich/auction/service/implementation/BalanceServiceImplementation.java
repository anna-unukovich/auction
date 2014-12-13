package com.unukovich.auction.service.implementation;

import com.unukovich.HibernateFactory;
import com.unukovich.auction.model.Balance;
import com.unukovich.auction.model.User;
import com.unukovich.auction.service.BalanceService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImplementation implements BalanceService{
    
    public BalanceServiceImplementation() {

    }
    
    @Override
    public void createBalance(Balance balance) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(balance);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Balance readBalance(int balanceId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Balance balance;
        balance = (Balance) session.get(Balance.class, balanceId);
        session.close();

        return balance;
    }

    @Override
    public void updateBalance(Balance balance) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(balance);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteBalance(Balance balance) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(balance);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllBalances() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List balancesList = session.createCriteria(Balance.class).list();
        session.close();

        return balancesList;
    }
    
    
    
}
