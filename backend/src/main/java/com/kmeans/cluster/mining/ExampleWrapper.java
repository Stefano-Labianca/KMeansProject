package com.kmeans.cluster.mining;

import java.util.ArrayList;

import com.kmeans.cluster.data.Tuple;

import lombok.Getter;

/**
 * Rappresenta una classe wrapper che permette di strutturare un Example, o
 * record di una tabella di database
 */
@Getter
public class ExampleWrapper {
    private ArrayList<Object> exampleData = new ArrayList<Object>();
    private Double distance;

    /**
     * Permette la costruzione di un oggetto tramite methode chaining
     * 
     * @return Oggetto classe ExampleWrapper
     */
    static public ExampleWrapper build() {
        return new ExampleWrapper();
    }

    /**
     * Imposta la distanza di un record dal centroide di un cluster
     * 
     * @param distance Distanza calcolata
     * @return Oggetto corrente
     */
    public ExampleWrapper setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Imposta il contenuto di un record di una tabella all'intero dell'oggetto
     * 
     * @param t Contenuto di un record
     * @return Oggetto corrente
     */
    public ExampleWrapper addExampleValue(Tuple t) {
        for (int i = 0; i < t.getLength(); i++) {
            this.exampleData.add(t.get(i).getValue());
        }

        return this;
    }
}
