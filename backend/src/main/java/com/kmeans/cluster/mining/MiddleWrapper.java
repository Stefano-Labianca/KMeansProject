package com.kmeans.cluster.mining;

import java.util.ArrayList;

import com.kmeans.cluster.data.Tuple;

import lombok.Getter;

/**
 * Rappresenta una classe wrapper che permette di strutturare il centroide di un
 * cluster
 */
@Getter
public class MiddleWrapper {
    /**
     * Array di oggetti che rappresentano i valori del centroide di un cluster
     */
    private ArrayList<Object> middleData = new ArrayList<Object>();

    /**
     * Questo metodo prende in input un oggetto di tipo Tuple
     * e scorre tutti i suoi valori per inserirli all'interno
     * del centroide di un cluster
     * @param t Record di una tabella
     */
    public void addMiddleValue(Tuple t) {
        for (int i = 0; i < t.getLength(); i++) {
            this.middleData.add(t.get(i).getValue());
        }
    }
}
