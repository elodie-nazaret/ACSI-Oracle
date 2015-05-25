package com.epsi.data;

import java.util.Date;

public class Visiteur extends Utilisateur {

    private String cp;
    private Date dateInscription;

    public Visiteur(Integer id, String login, String password, String cp, Date dateInscription) {
        super(id, login, password);
        this.cp              = cp;
        this.dateInscription = dateInscription;
    }
}
