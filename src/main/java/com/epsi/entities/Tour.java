package com.epsi.entities;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "TOUR")
public class Tour {

    @Id
    @GeneratedValue
    private Integer id;
    private Date beginDate;
    private Date endDate;

    @ManyToOne
    private Visitor visitor;

    @OneToMany(mappedBy="tour",cascade=CascadeType.ALL)
    private ArrayList<Watch> watches = new ArrayList<Watch>();

    /**
     * @param id Integer
     * @param endDate Date
     * @param beginDate Date
     */
    public Tour(Integer id, Date endDate, Date beginDate) {
        this.id         = id;
        this.endDate    = endDate;
        this.beginDate  = beginDate;
    }

    public Tour() {

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
     * @return Visitor
     */
    public Visitor getVisitor() {
        return visitor;
    }

    /**
     * @param visitor Visitor
     */
    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
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
