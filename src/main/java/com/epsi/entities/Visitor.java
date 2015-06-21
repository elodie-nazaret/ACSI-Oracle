package com.epsi.entities;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "VISITOR")
@PrimaryKeyJoinColumn(name="id")
public class Visitor extends People {

    private String postalCode;
    private Date subscribeDate;

    @OneToMany(mappedBy="visitor",cascade=CascadeType.ALL)
    private ArrayList<Tour> tours = new ArrayList<Tour>();

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

    /**
     * @return ArrayList<Tour>
     */
    public ArrayList<Tour> getTours() {
        return tours;
    }

    /**
     * @param tours ArrayList<Tour>
     */
    public void setTours(ArrayList<Tour> tours) {
        this.tours = tours;
    }

    /**
     * @param index int
     *
     * @return Tour
     */
    public Tour getTour(int index) {
        return this.tours.get(index);
    }

    /**
     * @param tour Tour
     */
    public void addTour(Tour tour) {
        this.tours.add(tour);
    }
}
