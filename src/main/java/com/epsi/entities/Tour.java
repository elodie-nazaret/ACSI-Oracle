package com.epsi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "TOUR")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_tour")
    @SequenceGenerator(name = "SEQ_tour", sequenceName = "SEQ_TOUR_ID", allocationSize = 1)
    private Integer id;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_people")
    private Visitor visitor;


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
}
