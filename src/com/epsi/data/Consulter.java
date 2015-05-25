package com.epsi.data;

import java.util.Date;

public class Consulter {

    private Integer idArticle;
    private Integer idVisite;
    private Integer idUser;
    private Date startConsulter;
    private Date endConsulter;

    public Consulter(Integer idArticle, Integer idVisite, Integer idUser, Date startConsulter, Date endConsulter) {
        this.idArticle = idArticle;
        this.idVisite = idVisite;
        this.idUser = idUser;
        this.startConsulter = startConsulter;
        this.endConsulter = endConsulter;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getIdVisite() {
        return idVisite;
    }

    public void setIdVisite(Integer idVisite) {
        this.idVisite = idVisite;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Date getStartConsulter() {
        return startConsulter;
    }

    public void setStartConsulter(Date startConsulter) {
        this.startConsulter = startConsulter;
    }

    public Date getEndConsulter() {
        return endConsulter;
    }

    public void setEndConsulter(Date endConsulter) {
        this.endConsulter = endConsulter;
    }
}
