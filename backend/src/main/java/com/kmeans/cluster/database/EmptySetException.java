package com.kmeans.cluster.database;

/**
 * Eccezione generata quando si prova ad applicare l'algoritmo su una tabella
 * vuota
 */
public class EmptySetException extends Exception {

    /** Costruttore con errore di default */
    public EmptySetException() {
        super();
    }

    /**
     * Costruttore in cui è possibile inserire un messaggio di errore personalizzato
     * 
     * @param msg Messaggio di errore
     */
    public EmptySetException(String msg) {
        super(msg);
    }

}
