package com.kmeans.cluster.database;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DbAccess
 */
public class DbAccess {
    String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    final String DBMS = "jdbc:mysql";
    final Integer PORT = 3306;

    String server = "localhost";
    String database = "MapDB";
    String userId = "MapUser";
    String password = "map";
    Connection conn;

    public DbAccess() {
    }

    public DbAccess(String server, String database, String userId, String password) {
        this.server = server;
        this.database = database;
        this.userId = userId;
        this.password = password;
    }

    public void initConnection() throws DatabaseConnectionException {
        try {
            Class.forName(DRIVER_CLASS_NAME).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new DatabaseConnectionException("[!] Driver non trovato: " + e.getMessage());
        } catch (InstantiationException e) {
            throw new DatabaseConnectionException("[!] Errore durante l'istanziazione: " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new DatabaseConnectionException("[!] Impossibile accedere al driver: " + e.getMessage());
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        String connectionString = this.DBMS + "://" + this.server + ":" + this.PORT + "/" + this.database
                + "?user=" + this.userId + "&password=" + this.password + "&serverTimezone=UTC";
        System.out.println("Connection's String: " + connectionString);

        try {
            this.conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            throw new DatabaseConnectionException(
                    "[!] SQLException: " + e.getMessage() + "\n"
                            + "[!] SQLState: " + e.getSQLState() + "\n"
                            + "[!] VendorError: " + e.getErrorCode());
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void closeConnection() throws DatabaseConnectionException {
        try {
            this.conn.close();
        } catch (SQLException e) {
            throw new DatabaseConnectionException(
                    "[!] Errore durante la chiusura della connessione: " + e.getMessage());
        }
    }
}