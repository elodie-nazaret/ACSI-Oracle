package com.epsi.data;

import com.epsi.ACSIConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    private UtilisateurDaoImpl user = new UtilisateurDaoImpl();

    public void insert(Admin admin) {

        //retourner l'id inséré
        user.insert(admin);
        String sql = "INSERT INTO UTILISATEUR (LOGIN_UTLISATEUR, PASSWORD_UTILISATEUR) VALUES (?, ?)";

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ps.setString(1, admin.getLogin());
            ps.setString(2, admin.getPassword());
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Admin findById(int id) {

        String sql = "SELECT * FROM UTILISATEUR WHERE ID_UTILISATEUR = ?";

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ps.setInt(1, id);
            Admin admin = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(
                        rs.getInt("ID_UTILISATEUR"),
                        rs.getString("LOGIN_UTILISATEUR"),
                        rs.getString("PASSWORD_UTILISATEUR")
                );
            }
            rs.close();
            ps.close();

            return admin;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Admin> findAll() {

        String sql = "SELECT * FROM UTILISATEUR";
        List<Admin> users = new ArrayList<>();

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("ID_UTILISATEUR"),
                        rs.getString("LOGIN_UTILISATEUR"),
                        rs.getString("PASSWORD_UTILISATEUR")
                );

                users.add(admin);
            }

            rs.close();
            ps.close();

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Admin admin) {

        String sql = "UPDATE UTILISATEUR SET LOGIN_UTILISATEUR = ?, PASSWORD_UTILISATEUR = ? WHERE ID_UTILISATEUR = ?";

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ps.setString(1, admin.getLogin());
            ps.setString(2, admin.getPassword());
            ps.setInt(3, admin.getId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Admin admin) {

        String sql = "DELETE FROM UTILISATEUR WHERE ID_UTILISATEUR = ?";

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ps.setInt(1, admin.getId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
