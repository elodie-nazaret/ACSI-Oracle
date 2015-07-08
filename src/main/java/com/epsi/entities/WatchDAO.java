package com.epsi.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.epsi.managers.HibernateUtil;

public class WatchDAO {

    public void addWatch(Watch watch) {
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(watch);
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

    public void deleteWatch(Watch watch) {
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(watch);
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

    public void updateWatch(Watch watch) {
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(watch);
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

    public List<Watch> getAllWatchs() {
        List<Watch>  watchs       = new ArrayList<Watch>();
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            watchs = session.createQuery("from Watch").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return watchs;
    }

    public Watch getWatchById(int id) {
        Watch        watch        = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Watch where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            watch = (Watch) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return watch;
    }

    public List<Object[]> getAverageWatchTimeForArticle(Article article) {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT to_char(w.beginDate,'YYYY/MM') as month, avg(w.endDate - w.beginDate + 1) * 24 * 60 * 60 as time FROM Watch w WHERE w.article = :article GROUP BY to_char(w.beginDate,'YYYY/MM') ORDER BY to_char(w.beginDate,'YYYY/MM')";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("article", article);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }
}
