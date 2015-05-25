package com.epsi.data;

import com.epsi.ACSIConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDaoImpl implements UtilisateurDao {

    public void insert(Utilisateur utilisateur) {

        String sql = "INSERT INTO UTILISATEUR (LOGIN_UTLISATEUR, PASSWORD_UTILISATEUR) VALUES (?, ?)";

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ps.setString(1, utilisateur.getLogin());
            ps.setString(2, utilisateur.getPassword());
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utilisateur findById(int id) {
        
        String sql = "SELECT * FROM UTILISATEUR WHERE ID_UTILISATEUR = ?";

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ps.setInt(1, id);
            Utilisateur utilisateur = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                utilisateur = new Utilisateur(
                    rs.getInt("ID_UTILISATEUR"),
                    rs.getString("LOGIN_UTILISATEUR"),
                    rs.getString("PASSWORD_UTILISATEUR")
                );
            }
            rs.close();
            ps.close();

            return utilisateur;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Utilisateur> findAll() {

        String sql = "SELECT * FROM UTILISATEUR";
        List<Utilisateur> users = new ArrayList<>();

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("ID_UTILISATEUR"),
                        rs.getString("LOGIN_UTILISATEUR"),
                        rs.getString("PASSWORD_UTILISATEUR")
                );

                users.add(utilisateur);
            }

            rs.close();
            ps.close();

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Utilisateur utilisateur) {

        String sql = "UPDATE UTILISATEUR SET LOGIN_UTILISATEUR = ?, PASSWORD_UTILISATEUR = ? WHERE ID_UTILISATEUR = ?";

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ps.setString(1, utilisateur.getLogin());
            ps.setString(2, utilisateur.getPassword());
            ps.setInt(3, utilisateur.getId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Utilisateur utilisateur) {

        String sql = "DELETE FROM UTILISATEUR WHERE ID_UTILISATEUR = ?";

        try {
            PreparedStatement ps = ACSIConnection.getInstance().prepareStatement(sql);
            ps.setInt(1, utilisateur.getId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
