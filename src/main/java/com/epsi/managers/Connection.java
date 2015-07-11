package com.epsi.managers;

import com.epsi.entities.People;
import com.epsi.entities.Tour;
import com.epsi.entities.TourDAO;
import com.epsi.entities.Visitor;

import java.util.Date;

public class Connection {

    /**
     * Gestion du singleton
     */
    private static Connection ourInstance = new Connection();

    public static Connection getInstance() {
        return ourInstance;
    }

    private Connection() {
    }


    private People connectedPeople;
    private Tour tour;

    /**
     * Retourne la personne connectée
     * @return People
     */
    public People getConnectedPeople() {
        return connectedPeople;
    }

    /**
     * Enregistre la personne connectée et initialise la session si c'est un utilisateur
     * @param connectedPeople
     */
    public void setConnectedPeople(People connectedPeople) {
        this.connectedPeople = connectedPeople;

        if (this.connectedPeople instanceof Visitor) {
            this.initTour();
        }
    }

    /**
     * Initialisation de la session
     */
    private void initTour() {
        this.tour = new Tour();
        this.tour.setBeginDate(new Date());
        this.tour.setVisitor((Visitor) this.connectedPeople);
    }

    /**
     * Enregistre la session
     */
    public void endTour() {
        this.tour.setEndDate(new Date());

        TourDAO tourDAO = new TourDAO();
        tourDAO.addTour(this.tour);
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
