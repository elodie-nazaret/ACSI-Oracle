package com.epsi.data;

import java.util.List;

public interface UtilisateurDao {

    public Utilisateur findById(int id);
    public List<Utilisateur> findAll();
    public void insert(Utilisateur utilisateur);
    public void update(Utilisateur utilisateur);
    public void delete(Utilisateur utilisateur);
}
