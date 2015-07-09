package com.epsi.entities;

import com.epsi.managers.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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
        List<Watch>  watches    = new ArrayList<Watch>();
        Transaction transaction = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            watches = session.createQuery("from Watch").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return watches;
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

        String sqlQuery = "SELECT to_char(w.beginDate,'YYYY/MM'), avg(w.endDate - w.beginDate) * 24 * 60 * 60 FROM Watch w WHERE w.article = :article GROUP BY to_char(w.beginDate,'YYYY/MM') ORDER BY to_char(w.beginDate,'YYYY/MM')";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("article", article);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getCountWatchTimeForArticle(Article article) {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT to_char(beginDate,'YYYY/MM'), count(*) FROM Watch WHERE article = :article GROUP BY to_char(beginDate,'YYYY/MM') ORDER BY to_char(beginDate,'YYYY/MM')";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("article", article);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getTopVisitorForArticle(Article article) {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.tour.visitor.login, count(*) as quantity FROM Watch w WHERE w.article = :article GROUP BY w.tour.visitor.login ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        query.setParameter("article", article);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getTopCpForArticle(Article article) {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.tour.visitor.postalCode as postalCode, count(*) as quantity FROM Watch w WHERE w.article = :article GROUP BY w.tour.visitor.postalCode ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        query.setParameter("article", article);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getAverageWatchTimeForAllArticles() {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, to_char(w.beginDate,'YYYY/MM') as month, avg(w.endDate - w.beginDate) * 24 * 60 * 60 as time FROM Watch w GROUP BY w.article.designation, to_char(w.beginDate,'YYYY/MM') ORDER BY time DESC";
        Query query = session.createQuery(sqlQuery);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getTopCpForAllArticles() {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.tour.visitor.postalCode as postalCode, count(*) as quantity FROM Watch w GROUP BY w.tour.visitor.postalCode ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getCountWatchTimeForAllArticles() {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, to_char(w.beginDate,'YYYY/MM') as month, count(*) as quantity FROM Watch w GROUP BY w.article.designation, to_char(w.beginDate,'YYYY/MM') ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getTopArticlesCountWatchTime() {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, count(*) as quantity FROM Watch w GROUP BY w.article.designation ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getTopArticlesAverageWatchTime() {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, avg(w.endDate - w.beginDate) * 24 * 60 * 60 as time FROM Watch w GROUP BY w.article.designation ORDER BY time DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getCountWatchTimeForAVisitor(Visitor visitor) {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, count(*) as quantity FROM Watch w WHERE w.tour.visitor = :visitor GROUP BY w.article.designation ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("visitor", visitor);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getAverageWatchTimeForAVisitor(Visitor visitor) {
        Session     session     = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, avg(w.endDate - w.beginDate) * 24 * 60 * 60 as time FROM Watch w WHERE w.tour.visitor = :visitor GROUP BY w.article.designation ORDER BY time DESC";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("visitor", visitor);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }
}
