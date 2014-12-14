package com.unukovich.auction.service.implementation;

import com.unukovich.HibernateFactory;
import com.unukovich.auction.model.Bet;
import com.unukovich.auction.service.BetService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class BetServiceImplementation implements BetService{

    @Override
    public void createBet(Bet bet) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(bet);
        session.getTransaction().commit();
        session.close();}

    @Override
    public Bet readBet(int betId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Bet bet;
        bet = (Bet) session.get(Bet.class, betId);
        session.close();

        return bet;}

    @Override
    public void updateBet(Bet bet) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(bet);
        session.getTransaction().commit();
        session.close();}

    @Override
    public void deleteBet(Bet bet) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(bet);
        session.getTransaction().commit();
        session.close();}

    @Override
    public List getAllBets() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List betsList = session.createCriteria(Bet.class).list();
        session.close();

        return betsList;
    }
    
}
