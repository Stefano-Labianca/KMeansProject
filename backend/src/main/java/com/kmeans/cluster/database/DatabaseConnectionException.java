package com.kmeans.cluster.database;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException() {
        super("Errore nella connesione");
    }

    public DatabaseConnectionException(String msg) {
        super(msg);
    }
}
