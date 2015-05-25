package com.epsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ACSIConnection {

    private static String url       = "jdbc:oracle:thin:@//localhost:1521:xe";
    private static String urlRemote = "jdbc:oracle:thin:@//192.168.1.23:1521/xe";
    private static String user      = "acsi_oracle";
    private static String passwd    = "acsi_oracle";
    private static Connection connect;

    public static Connection getInstance() {
        if (connect == null) {
            try {
                connect = DriverManager.getConnection(urlRemote, user, passwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
