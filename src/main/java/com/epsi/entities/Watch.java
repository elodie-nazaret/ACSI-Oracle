package com.epsi.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WATCH")
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_watch")
    @SequenceGenerator(name = "SEQ_watch", sequenceName = "SEQ_WATCH_ID", allocationSize = 1)
    private Integer id;

    @Column(name = "begin_date")
    private Date    beginDate;

    @Column(name = "end_date")
    private Date    endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tour")
    private Tour    tour;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_article")
    private Article article;

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
