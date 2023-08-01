package com.kmeans.cluster.mining;

import java.util.ArrayList;

import com.kmeans.cluster.data.Tuple;

import lombok.Getter;

/**
 * <h1>MiddleWrapper</h1>
 * Rappresenta una classe wrapper che permette di strutturare il centroide di un
 * cluster
 */
@Getter
public class MiddleWrapper {
    private ArrayList<Object> middleData = new ArrayList<Object>();

    /**
     * Inserisce un valore all'intero del centroide tutti i dati associati ad esso,
     * contenuti in una riga di una tabella del database
     * 
     * @param t Record di una tabella
     */
    public void addMiddleValue(Tuple t) {
        for (int i = 0; i < t.getLength(); i++) {
            this.middleData.add(t.get(i).getValue());
        }
    }
}
