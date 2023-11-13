package com.kmeans.cluster.mining;

import com.kmeans.cluster.data.Tuple;

import java.util.ArrayList;

/**
 * Rappresenta una classe wrapper che permette di strutturare il centroide di un
 * cluster
 */
public class MiddleWrapper {
    /**
     * Array di oggetti che rappresentano i valori del centroide di un cluster
     */
    private ArrayList<Object> middleData = new ArrayList<Object>();

    /**
     * Questo metodo prende in input un oggetto di tipo Tuple
     * e scorre tutti i suoi valori per inserirli all'interno
     * del centroide di un cluster
     *
     * @param t Record di una tabella
     */
    public void addMiddleValue(Tuple t) {
        for (int i = 0; i < t.getLength(); i++) {
            this.middleData.add(t.get(i).getValue());
        }
    }

    /**
     * Restituisce i dati del centroide
     * @return Dati del centroide
     */
    public ArrayList<Object> getMiddleData() {
        return this.middleData;
    }
}
