package com.epsi.data;

import java.util.Date;
import java.util.List;

public interface ConsulterDao {

    public List<Consulter> getAllConsulter();
    public Consulter getConsulter(int idArticle, int idUser, int idVisite, Date startConsulter);
    public void updateConsulter(Consulter consulter);
    public void deleteConsulter(Consulter consulter);
}
