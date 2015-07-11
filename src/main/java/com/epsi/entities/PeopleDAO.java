package com.epsi.entities;

import com.epsi.managers.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PeopleDAO {

    /**
     * Ajoute une personne dans la base de données
     *
     * @param people personne à ajouter
     */
    public void addPeople(People people) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(people);
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
     * Supprime une personne de la base de données
     *
     * @param people personne à supprimer
     */
    public void deletePeople(People people) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(people);
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
     * Met à jour une personne dans la base de données
     *
     * @param people personne à mettre à jour
     */
    public void updatePeople(People people) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(people);
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
     * Récupère la totalité des personnes de la base de données
     */
    public List<People> getAllPeoples() {
        List<People> peoples = new ArrayList<People>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            peoples = session.createQuery("from People").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return peoples;
    }

    /**
     * Récupère une personne en base de données selon son id
     *
     * @param id id de la personne à récupérer
     * @return People
     */
    public People getPeopleById(int id) {
        People people = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from People where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            people = (People) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return people;
    }

    /**
     * Récupère une personne selon son login et son password
     *
     * @param login login de la personne
     * @param password mot de passe de la personne
     * @return People
     */
    public People checkLoginAndPassword(String login, String password) {
        People        people        = null;
        Session     session     = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from People where login = :login and password = :password";
            Query query = session.createQuery(queryString);
            query.setString("login", login);
            query.setString("password", password);
            people = (People) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return people;
    }
}
