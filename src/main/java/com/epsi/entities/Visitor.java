package com.epsi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "VISITOR")
@PrimaryKeyJoinColumn(name="ID_PEOPLE")
public class Visitor extends People {

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "subscribe_date")
    private Date subscribeDate;

    @OneToMany(mappedBy="visitor",cascade=CascadeType.ALL)
    private List<Tour> tours = new ArrayList<Tour>();

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
     * @return List<Tour>
     */
    public List<Tour> getTours() {
        return tours;
    }

    /**
     * @param tours List<Tour>
     */
    public void setTours(List<Tour> tours) {
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
