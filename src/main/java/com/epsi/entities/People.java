package com.epsi.entities;

import javax.persistence.*;

@Entity
@Table(name = "PEOPLE")
@Inheritance(strategy=InheritanceType.JOINED)
public class People {

    @Id
    @GeneratedValue
    private Integer id;
    private String login;
    private String password;

    /**
     * @param id Integer
     * @param login String
     * @param password String
     */
    public People(Integer id, String login, String password) {
        this.id       = id;
        this.login    = login;
        this.password = password;
    }

    public People() {
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
     * @return String
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login String
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
