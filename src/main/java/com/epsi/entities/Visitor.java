package com.epsi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "VISITOR")
@PrimaryKeyJoinColumn(name="ID_PEOPLE")
public class Visitor extends People {

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "subscribe_date")
    private Date subscribeDate;

    /**
     * @param id Integer
     * @param login String
     * @param password String
     * @param postalCode String
     * @param subscribeDate Date
     */
    public Visitor(Integer id, String login, String password, String postalCode, Date subscribeDate) {
        super(id, login, password);
        this.postalCode    = postalCode;
        this.subscribeDate = subscribeDate;
    }

    public Visitor() {
        super();
    }

    /**
     * @return String
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode String
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return Date
     */
    public Date getSubscribeDate() {
        return subscribeDate;
    }

    /**
     * @param subscribeDate Date
     */
    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }
}
