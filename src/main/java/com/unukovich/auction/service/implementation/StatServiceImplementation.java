package com.unukovich.auction.service.implementation;

import com.unukovich.HibernateFactory;
import com.unukovich.auction.model.Stat;
import com.unukovich.auction.model.User;
import com.unukovich.auction.service.StatService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImplementation implements StatService {

    public StatServiceImplementation() {

    }

    @Override
    public void createStatr(Stat stat) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(stat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Stat readStat(int statId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Stat stat;
        stat = (Stat) session.get(User.class, statId);
        session.close();

        return stat;
    }

    @Override
    public void updateStat(Stat stat) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(stat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteStat(Stat stat) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(stat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllStats() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List statsList = session.createCriteria(Stat.class).list();
        session.close();

        return statsList;
    }

}
