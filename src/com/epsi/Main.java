package com.epsi;

import java.lang.String;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try {
            PreparedStatement prepare = ACSIConnection.getInstance().prepareStatement("SELECT * FROM admin");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
