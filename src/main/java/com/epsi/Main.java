package com.epsi;

import com.epsi.managers.Mediator;

import java.lang.String;

public class Main {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();

        mediator.start();
    }
}
