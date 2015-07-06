package com.epsi.managers;

import com.epsi.entities.People;

public class Connection {
    private static Connection ourInstance = new Connection();

    public static Connection getInstance() {
        return ourInstance;
    }


    private People connectedPeople;

    private Connection() {
    }

    public People getConnectedPeople() {
        return connectedPeople;
    }

    public void setConnectedPeople(People connectedPeople) {
        this.connectedPeople = connectedPeople;
    }
}
