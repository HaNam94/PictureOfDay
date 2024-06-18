package org.example.picture_of_today.repository;

import org.example.picture_of_today.model.NasaPicture;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class NasaPictureRepositoryImpl implements NasaPictureRepository {
    public static SessionFactory sessionFactory;
    public static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<NasaPicture> findAll() {
        Session session = null;
        List<NasaPicture> nasaPictureList = null;
        try {
            session = sessionFactory.openSession();
            nasaPictureList = session.createQuery("FROM NasaPicture").getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return nasaPictureList;
    }

    @Override
    public void save(NasaPicture p) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.save(p);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public NasaPicture findById(int id) {
        Session session = null;
        NasaPicture p = null;
        try {
            session = sessionFactory.openSession();

            p = session.get(NasaPicture.class, id);

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return p;
    }

    @Override
    public void update(NasaPicture p) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(p);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void remove(int id) {
    }
}
