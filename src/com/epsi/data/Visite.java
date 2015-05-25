package com.epsi.data;

import java.util.Date;

public class Visite {

    private Integer id;
    private Integer idUser;
    private Date startVisite;
    private Date endVisite;

    public Visite(Integer id, Integer idUser, Date startVisite, Date endVisite) {
        this.id = id;
        this.idUser = idUser;
        this.startVisite = startVisite;
        this.endVisite = endVisite;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Date getStartVisite() {
        return startVisite;
    }

    public void setStartVisite(Date startVisite) {
        this.startVisite = startVisite;
    }

    public Date getEndVisite() {
        return endVisite;
    }

    public void setEndVisite(Date endVisite) {
        this.endVisite = endVisite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
