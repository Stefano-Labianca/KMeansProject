package com.kmeans.cluster.database;

/**
 * Eccezione usata quando avviene un errore durante la fase di connesione al
 * database
 */
public class DatabaseConnectionException extends Exception {

    /** Costruttore con errore di default */
    public DatabaseConnectionException() {
        super("Errore nella connesione");
    }

    /**
     * Costruttore in cui è possibile inserire un messaggio di errore personalizzato
     * 
     * @param msg Messaggio di errore
     */
    public DatabaseConnectionException(String msg) {
        super(msg);
    }
}
