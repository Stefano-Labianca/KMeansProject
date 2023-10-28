package com.kmeans.cluster.data;

/**
 * Eccezione da usare in caso il numero di cluster sia superiore del numero di
 * righe contenute nella tabella
 */
public class OutOfRangeSampleSize extends Exception {

    /**
     * Costruttore della classe in cui è possibile aggiungere un messaggio di errore
     * personalizzato
     * 
     * @param msg Messaggio di errore
     */
    public OutOfRangeSampleSize(String msg) {
        super(msg);
    }
}