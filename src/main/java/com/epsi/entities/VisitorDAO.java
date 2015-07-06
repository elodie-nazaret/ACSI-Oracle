package com.epsi.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.epsi.managers.HibernateUtil;

public class VisitorDAO {

    public void addVisitor(Visitor visitor) {
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();

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

    public void deleteVisitor(Visitor visitor) {
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
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

    public void updateVisitor(Visitor visitor) {
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
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

    public List<Visitor> getAllVisitors() {
        List<Visitor>  visitors       = new ArrayList<Visitor>();
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            visitors = session.createQuery("from Visitor").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return visitors;
    }

    public Visitor getVisitorById(int id) {
        Visitor        visitor        = null;
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String queryString = "from Visitor where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            visitor = (Visitor) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return visitor;
    }
}
