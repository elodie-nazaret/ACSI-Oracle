package com.epsi.entities;

import javax.persistence.*;

@Entity
@Table(name = "ADMIN")
@PrimaryKeyJoinColumn(name="id")
public class Admin extends People {

    /**
     * @param id Integer
     * @param login String
     * @param password String
     */
    public Admin(Integer id, String login, String password) {
        super(id, login, password);
    }

    public Admin() {
        super();
    }
}
