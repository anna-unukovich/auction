package com.unukovich.auction.service.implementation;

import com.unukovich.HibernateFactory;
import com.unukovich.auction.model.Auction;
import com.unukovich.auction.service.AuctionService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AuctionServiceImplementation implements AuctionService{

    @Override
    public void createAuction(Auction auction) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(auction);
        session.getTransaction().commit();
        session.close();    
    }

    @Override
    public Auction readAuction(int auctionId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Auction auction;
        auction = (Auction) session.get(Auction.class, auctionId);
        session.close();

        return auction;
    }

    @Override
    public void updateAuction(Auction auction) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(auction);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAuction(Auction auction) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(auction);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllAuctions() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List auctionsList = session.createCriteria(Auction.class).list();
        session.close();

        return auctionsList;
    }
    
}
