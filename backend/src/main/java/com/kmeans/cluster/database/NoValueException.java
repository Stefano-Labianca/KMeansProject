package com.kmeans.cluster.database;

/**
 * Eccezione lanciata quando si trova una colonna vuota
 */
public class NoValueException extends Exception {

    /** Costruttore con errore di default */
    public NoValueException() {
        super();
    }

    /**
     * Costruttore in cui è possibile inserire un messaggio di errore personalizzato
     * 
     * @param msg Messaggio di errore
     */
    public NoValueException(String msg) {
        super(msg);
    }
}
