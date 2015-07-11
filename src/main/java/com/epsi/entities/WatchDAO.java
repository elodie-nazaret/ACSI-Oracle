package com.epsi.entities;

import com.epsi.managers.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class WatchDAO {

    /**
     * Ajoute un watch dans la base de données
     *
     * @param watch watch à ajouter
     */
    public void addWatch(Watch watch) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

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

    /**
     * Supprime un watch de la base de données
     *
     * @param watch watch à supprimer
     */
    public void deleteWatch(Watch watch) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    /**
     * Met à jour un watch dans la base de données
     *
     * @param watch watch à mettre à jour
     */
    public void updateWatch(Watch watch) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    /**
     * Récupère la totalité des watchs de la base de données
     */
    public List<Watch> getAllWatchs() {
        List<Watch> watchs = new ArrayList<Watch>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            watchs = session.createQuery("from Watch").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return watchs;
    }

    /**
     * Récupère un watch en base de données selon son id
     *
     * @param id id du watch à récupérer
     * @return Watch
     */
    public Watch getWatchById(int id) {
        Watch watch = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Watch where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            watch = (Watch) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return watch;
    }

    /**
     * Récupère le temps moyen de consultation d'un article donné
     *
     * @param article article dont il faut calculer le temps de consultation
     */
    public List<Object[]> getAverageWatchTimeForArticle(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT to_char(w.beginDate,'YYYY/MM'), avg(w.endDate - w.beginDate) * 24 * 60 * 60 FROM Watch w WHERE w.article = :article GROUP BY to_char(w.beginDate,'YYYY/MM') ORDER BY to_char(w.beginDate,'YYYY/MM')";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("article", article);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    /**
     * Récupère le nombre de fois qu'un article a été consulté
     *
     * @param article
     */
    public List<Object[]> getCountWatchTimeForArticle(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT to_char(beginDate,'YYYY/MM'), count(*) FROM Watch WHERE article = :article GROUP BY to_char(beginDate,'YYYY/MM') ORDER BY to_char(beginDate,'YYYY/MM')";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("article", article);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    public List<Object[]> getTopVisitorForArticle(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.tour.visitor.login, count(*) as quantity FROM Watch w WHERE w.article = :article GROUP BY w.tour.visitor.login ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        query.setParameter("article", article);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    /**
     * Récupère la liste des code postaux ayant le plus visité un article donné
     *
     * @param article
     */
    public List<Object[]> getTopCpForArticle(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.tour.visitor.postalCode as postalCode, count(*) as quantity FROM Watch w WHERE w.article = :article GROUP BY w.tour.visitor.postalCode ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        query.setParameter("article", article);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    /**
     * Récupère le temps moyen de consultation des articles en général
     */
    public List<Object[]> getAverageWatchTimeForAllArticles() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, to_char(w.beginDate,'YYYY/MM') as month, avg(w.endDate - w.beginDate) * 24 * 60 * 60 as time FROM Watch w GROUP BY w.article.designation, to_char(w.beginDate,'YYYY/MM') ORDER BY time DESC";
        Query query = session.createQuery(sqlQuery);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    /**
     * Récupère la liste des codes postaux ayant le plus consulté des articles en général
     */
    public List<Object[]> getTopCpForAllArticles() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.tour.visitor.postalCode as postalCode, count(*) as quantity FROM Watch w GROUP BY w.tour.visitor.postalCode ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    /**
     * Récupère le nombre de fois que les articles ont été vus de manière générale
     */
    public List<Object[]> getCountWatchTimeForAllArticles() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, to_char(w.beginDate,'YYYY/MM') as month, count(*) as quantity FROM Watch w GROUP BY w.article.designation, to_char(w.beginDate,'YYYY/MM') ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    /**
     * Récupère le top 5 des articles ayant été vus le plus de fois
     */

    public List<Object[]> getTopArticlesCountWatchTime() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, count(*) as quantity FROM Watch w GROUP BY w.article.designation ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    /**
     * Récupère le top 5 des temps moyens de consultation des articles
     */
    public List<Object[]> getTopArticlesAverageWatchTime() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, avg(w.endDate - w.beginDate) * 24 * 60 * 60 as time FROM Watch w GROUP BY w.article.designation ORDER BY time DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(5);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    /**
     * Récupère le nombre de consultation de chaque article pour un visiteur
     *
     * @param visitor
     */
    public List<Object[]> getCountWatchTimeForAVisitor(Visitor visitor) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, count(*) as quantity FROM Watch w WHERE w.tour.visitor = :visitor GROUP BY w.article.designation ORDER BY quantity DESC";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("visitor", visitor);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }

    /**
     * Récupère le temps moyen de consultation de chaque article pour un visiteur
     *
     * @param visitor
     */
    public List<Object[]> getAverageWatchTimeForAVisitor(Visitor visitor) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String sqlQuery = "SELECT w.article.designation, avg(w.endDate - w.beginDate) * 24 * 60 * 60 as time FROM Watch w WHERE w.tour.visitor = :visitor GROUP BY w.article.designation ORDER BY time DESC";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("visitor", visitor);
        List<Object[]> results = query.list();

        session.close();
        return results;
    }
}
