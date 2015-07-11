package com.epsi.entities;

import com.epsi.managers.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    /**
     * Ajoute un article dans la base de données
     *
     * @param article article à ajouter
     */
    public void addArticle(Article article) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(article);
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
     * Supprime un article de la base de données
     *
     * @param article article à supprimer
     */
    public void deleteArticle(Article article) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(article);
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
     * Met à jour un article dans la base de données
     *
     * @param article article à mettre à jour
     */
    public void updateArticle(Article article) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(article);
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
     * Récupère la totalité des articles de la base de données
     */
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<Article>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            articles = session.createQuery("from Article").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return articles;
    }

    /**
     * Récupère la liste des articles visibles dans la base de données
     */
    public List<Article> getAllArticlesVisible() {
        List<Article> articles = new ArrayList<Article>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            articles = session.createQuery("from Article WHERE isVisible = true ").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return articles;
    }

    /**
     * Récupère un article en base de données selon son id
     *
     * @param id id du article à récupérer
     * @return Article
     */
    public Article getArticleById(int id) {
        Article article = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Article where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            article = (Article) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return article;
    }
}
