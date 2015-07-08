package com.epsi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "ARTICLE")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_article")
    @SequenceGenerator(name = "SEQ_article", sequenceName = "SEQ_ARTICLE_ID", allocationSize = 1)
    private Integer id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "designation")
    private String designation;

    @Column(name = "price")
    private Float price;

    @Column(name = "isVisible")
    private Boolean isVisible;

    @Column(name = "creation_date")
    private Date createdAt;

    @Column(name = "modification_date")
    private Date updatedAt;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "description")
    private String description;

    /**
     * @param id Integer
     * @param createdAt Date
     * @param updatedAt Date
     * @param description String
     * @param image byte[]
     * @param isVisible Boolean
     * @param price Float
     * @param reference String
     * @param designation String
     */
    public Article(Integer id, Date createdAt, Date updatedAt, String description, byte[] image, Boolean isVisible, Float price, String reference, String designation) {
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
     * @return byte[]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image byte[]
     */
    public void setImage(byte[] image) {
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
}
