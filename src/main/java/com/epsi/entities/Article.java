package com.epsi.entities;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "ARTICLE")
public class Article {

    @Id
    @GeneratedValue
    private Integer id;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    private Blob image;
    private Boolean isVisible;
    private Float price;
    private String reference;
    private String designation;

    @OneToMany(mappedBy="article",cascade=CascadeType.ALL)
    private ArrayList<Watch> watches = new ArrayList<Watch>();

    /**
     * @param id Integer
     * @param createdAt Date
     * @param updatedAt Date
     * @param description String
     * @param image Blob
     * @param isVisible Boolean
     * @param price Float
     * @param reference String
     * @param designation String
     */
    public Article(Integer id, Date createdAt, Date updatedAt, String description, Blob image, Boolean isVisible, Float price, String reference, String designation) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
        this.image = image;
        this.isVisible = isVisible;
        this.price = price;
        this.reference = reference;
        this.designation = designation;
    }

    public Article() {

    }

    /**
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt Date
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt Date
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Blob
     */
    public Blob getImage() {
        return image;
    }

    /**
     * @param image Blob
     */
    public void setImage(Blob image) {
        this.image = image;
    }

    /**
     * @return Boolean
     */
    public Boolean isVisible() {
        return isVisible;
    }

    /**
     * @param isVisible Boolean
     */
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * @return Float
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price Float
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return String
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference String
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return String
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation String
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return ArrayList<Watch>
     */
    public ArrayList<Watch> getWatches() {
        return watches;
    }

    /**
     * @param watches ArrayList<Watch>
     */
    public void setWatchs(ArrayList<Watch> watches) {
        this.watches = watches;
    }

    /**
     * @param index int
     *
     * @return Watch
     */
    public Watch getWatch(int index) {
        return this.watches.get(index);
    }

    /**
     * @param watch Watch
     */
    public void addWatch(Watch watch) {
        this.watches.add(watch);
    }
}
