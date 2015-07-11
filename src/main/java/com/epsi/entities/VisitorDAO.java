package com.epsi.entities;

import com.epsi.managers.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class VisitorDAO {

    /**
     * Ajoute un visiteur dans la base de données
     *
     * @param visitor visitor à ajouter
     */
    public void addVisitor(Visitor visitor) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(visitor);
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
     * Supprime un visiteur de la base de données
     *
     * @param visitor visitor à supprimer
     */
    public void deleteVisitor(Visitor visitor) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(visitor);
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
     * Met à jour un visiteur dans la base de données
     *
     * @param visitor visitor à mettre à jour
     */
    public void updateVisitor(Visitor visitor) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(visitor);
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
     * Récupère la totalité des visiteurs de la base de données
     */
    public List<Visitor> getAllVisitors() {
        List<Visitor> visitors = new ArrayList<Visitor>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            visitors = session.createQuery("from Visitor").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return visitors;
    }

    /**
     * Récupère un visiteur en base de données selon son id
     *
     * @param id id du visiteur à récupérer
     * @return Visitor
     */
    public Visitor getVisitorById(int id) {
        Visitor visitor = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Visitor where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            visitor = (Visitor) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return visitor;
    }

    /**
     * Récupère le nombre de nouveaux inscrits sur le mois donné
     *
     * @param month date au format YYYY/MM
     * @return Object
     */
    public Object getCountNewSubscribersForMonth(String month) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT count(*) FROM Visitor v WHERE to_char(v.subscribeDate,'YYYY/MM') = :date";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("date", month);
        Object result = query.uniqueResult();

        session.close();
        return result;
    }

    /**
     * Récupère le nombre total de visiteurs
     *
     * @return Object
     */
    public Object getCountAllVisitors() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT count(*) FROM Visitor v";
        Query query = session.createQuery(sqlQuery);
        Object result = query.uniqueResult();

        session.close();
        return result;
    }
}
