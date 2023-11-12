package com.kmeans.cluster.database;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe che permette l'accesso al database, esponendo anche la classe
 * {@link DatabaseConnectionException} per la gestione degli errori
 * 
 * @see DatabaseConnectionException
 */
public class DbAccess {
    /** Driver per la connessione al database */
    String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    /** Tipologia di DBMS da usare */
    final String DBMS = "jdbc:mysql";

    /** Porta esposta per la connesione al database */
    final Integer PORT = 3306;

    /** Indirizzo del server */
    String server = "localhost";

    /** Nome del database */
    String database = "MapDB";

    /** Nome utente */
    String userId = "MapUser";

    /** Password dell'utente */
    String password = "map";

    /** Connessione al database */
    Connection conn;

    /** Porta fornita dall'utente */
    Integer customPort = null;

    /**
     * Permette di connettersi al database usando le informazioni di default fornite
     * dalla classe
     */
    public DbAccess() {
    }

    /**
     * Permette la connesione al database usando le impostazioni fornite tramite i
     * parametri
     * 
     * @param server   Indirizzo del server
     * @param database Nome del database
     * @param userId   Nome utente
     * @param password Password dell'utente
     * @param port     Numero di porta del database
     */
    public DbAccess(String server, String database, String userId, String password, Integer port) {
        this.server = server;
        this.database = database;
        this.userId = userId;
        this.password = password;
        this.customPort = port;
    }

    /**
     * Inizializza la connesione al database
     * 
     * @throws DatabaseConnectionException Causata quando non è possibile
     *                                     connettersi al databse
     */
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

        String connectionString = "";

        if (this.customPort == null) {
            connectionString = this.DBMS + "://" + this.server + ":" + this.PORT + "/" + this.database
                    + "?user=" + this.userId + "&password=" + this.password + "&serverTimezone=UTC";
        } else {
            connectionString = this.DBMS + "://" + this.server + ":" + this.customPort + "/" + this.database
                    + "?user=" + this.userId + "&password=" + this.password + "&serverTimezone=UTC";
        }

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

    /**
     * Restituisce un riferimento alla connessione del database
     * 
     * @return Connessione al database
     */
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * Permette di chiudere la connesisone con il database
     * 
     * @throws DatabaseConnectionException Causata quando non è possibile
     *                                     connettersi al databse
     */
    public void closeConnection() throws DatabaseConnectionException {
        try {
            this.conn.close();
        } catch (SQLException e) {
            throw new DatabaseConnectionException(
                    "[!] Errore durante la chiusura della connessione: " + e.getMessage());
        }
    }
}