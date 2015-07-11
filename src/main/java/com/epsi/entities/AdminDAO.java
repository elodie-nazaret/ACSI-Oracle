package com.epsi.entities;

import com.epsi.managers.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    /**
     * Ajoute un admin dans la base de données
     *
     * @param admin admin à ajouter
     */
    public void addAdmin(Admin admin) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(admin);
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
     * Supprime un admin de la base de données
     *
     * @param admin admin à supprimer
     */
    public void deleteAdmin(Admin admin) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(admin);
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
     * Met à jour un admin dans la base de données
     *
     * @param admin admin à mettre à jour
     */
    public void updateAdmin(Admin admin) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(admin);
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
     * Récupère la totalité des admins de la base de données
     */
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<Admin>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            admins = session.createQuery("from Admin").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return admins;
    }

    /**
     * Récupère un admin en base de données selon son id
     *
     * @param id id de l'admin à récupérer
     * @return Admin
     */
    public Admin getAdminById(int id) {
        Admin admin = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String queryString = "from Admin where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            admin = (Admin) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return admin;
    }
}
