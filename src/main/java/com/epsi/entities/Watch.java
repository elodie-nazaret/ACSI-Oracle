package com.epsi.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "WATCH")
public class Watch {

    @Id
    @GeneratedValue
    private Integer id;
    private Date    beginDate;
    private Date    endDate;

    @ManyToOne
    private Tour    tour;

    @ManyToOne
    private Article article;

    /**
     * @param id Integer
     * @param beginDate Date
     * @param endDate Date
     * @param tour Tour
     * @param article Article
     */
    public Watch(Integer id, Date beginDate, Date endDate, Tour tour, Article article) {
        this.id         = id;
        this.beginDate  = beginDate;
        this.endDate    = endDate;
        this.tour       = tour;
        this.article    = article;
    }

    public Watch() {

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
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate Date
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return Date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate Date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return Article
     */
    public Article getArticle() {
        return article;
    }

    /**
     * @param article Article
     */
    public void setArticle(Article article) {
        this.article = article;
    }

    /**
     * @return Tour
     */
    public Tour getTour() {
        return tour;
    }

    /**
     * @param tour Tour
     */
    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
