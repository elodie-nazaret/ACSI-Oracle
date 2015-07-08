package com.epsi.managers;

import com.epsi.entities.People;
import com.epsi.entities.Tour;
import com.epsi.entities.TourDAO;
import com.epsi.entities.Visitor;

import java.util.Date;

public class Connection {

    private static Connection ourInstance = new Connection();

    public static Connection getInstance() {
        return ourInstance;
    }


    private People connectedPeople;
    private Tour   tour;

    private Connection() {
    }

    public People getConnectedPeople() {
        return connectedPeople;
    }

    public void setConnectedPeople(People connectedPeople) {
        this.connectedPeople = connectedPeople;

        if (this.connectedPeople instanceof Visitor) {
            this.initTour();
        }
    }

    private void initTour() {
        this.tour = new Tour();
        this.tour.setBeginDate(new Date());
        this.tour.setVisitor((Visitor) this.connectedPeople);
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void endTour() {
        this.tour.setEndDate(new Date());

        TourDAO tourDAO = new TourDAO();
        tourDAO.addTour(this.tour);
    }
}
