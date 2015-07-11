package com.epsi.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN")
@PrimaryKeyJoinColumn(name="ID_PEOPLE")
public class Admin extends People {

    public Admin() {
        super();
    }
}
