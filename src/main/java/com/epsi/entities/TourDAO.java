package com.epsi.entities;

import com.epsi.managers.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TourDAO {
    /**
     * Ajoute un tour dans la base de données
     *
     * @param tour tour à ajouter
     */
    public void addTour(Tour tour) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(tour);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    /**
     * Supprime un tour de la base de données
     *
     * @param tour tour à supprimer
     */
    public void deleteTour(Tour tour) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(tour);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    /**
     * Met à jour un tour dans la base de données
     *
     * @param tour tour à mettre à jour
     */
    public void updateTour(Tour tour) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(tour);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    /**
     * Récupère la totalité des tours de la base de données
     */
    public List<Tour> getAllTours() {
        List<Tour> tours = new ArrayList<Tour>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tours = session.createQuery("from Tour").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tours;
    }

    /**
     * Récupère un tour en base de données selon son id
     *
     * @param id id du tour à récupérer
     * @return Tour
     */
    public Tour getTourById(int id) {
        Tour tour = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Tour where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            tour = (Tour) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tour;
    }
}
