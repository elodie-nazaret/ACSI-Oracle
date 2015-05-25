package com.epsi.data;

import java.util.List;

public interface VisiteDao {

    public List<Visite> getAllVisite();
    public Visite getVisite(int idVisite, int idUser);
    public void updateVisite(Visite visite);
    public void deleteVisite(Visite visite);
}
