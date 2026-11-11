package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/fondatel?useSSL=false&serverTimezone=America/Mexico_City";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private Conexion() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}