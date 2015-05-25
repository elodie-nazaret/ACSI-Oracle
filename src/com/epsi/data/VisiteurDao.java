package com.epsi.data;

import java.util.List;

public interface VisiteurDao {

    public List<Visiteur> getAllVisiteur();
    public Visiteur getVisiteur(int id);
    public void updateVisiteur(Visiteur visiteur);
    public void deleteVisiteur(Visiteur visiteur);
}
