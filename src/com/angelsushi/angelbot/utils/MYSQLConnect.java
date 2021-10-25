package com.angelsushi.angelbot.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MYSQLConnect {
    private static Connection conn;

    public static void connectToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver OK");
            //étape 2: créer l'objet de connexion
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/angelbot", "root", "");
            System.out.println("Connexion OK");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }
}
