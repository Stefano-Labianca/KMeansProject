package com.kmeans.converter;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.OutOfRangeSampleSize;
import com.kmeans.cluster.mining.Cluster;
import com.kmeans.cluster.mining.ClusterSet;
import com.kmeans.cluster.mining.ClusterWrapper;

import java.util.ArrayList;

/**
 * Rappresenta una classe wrapper che permette di strutturare il calcolo del
 * K-Means in un formato più semplice da elaborare per il client
 */
public class APIResponse {
    /**
     * Array di stringhe che rappresentano i nomi delle colonne della tabella
     */
    private ArrayList<String> columnsName = new ArrayList<>();
    /**
     * Array di oggetti che rappresentano i cluster calcolati
     */
    private ArrayList<ClusterWrapper> clusters = new ArrayList<>();
    /**
     * Numero di iterazioni svolte dall'algoritmo
     */
    private Integer iteration;
    /**
     * Numero di cluster
     */
    private Integer k;

    /**
     * Permette la costruzione di un oggetto tramite methods chaining
     *
     * @return Oggetto classe APIResponse
     */
    static public APIResponse build() {
        return new APIResponse();
    }

    /**
     * Imposta il numero di iterazioni svolte dall'algoritmo
     *
     * @param iteration Iterazioni svolte
     * @return Oggetto corrente
     */
    public APIResponse setIteration(Integer iteration) {
        this.iteration = iteration;
        return this;
    }

    /**
     * Imposta il numero di cluster
     *
     * @param k Numero di cluster
     * @return Oggetto corrente
     * @throws OutOfRangeSampleSize Causata quando il valore di {@code k} è maggiore
     *                              del numero di righe della tabella
     */
    public APIResponse setK(Integer k) throws OutOfRangeSampleSize {
        this.k = k;

        return this;
    }

    /**
     * Imposta i nomi delle colonne usate nella tabella
     *
     * @param data Tabella usata nella computazione
     * @return Oggetto corrente
     */
    public APIResponse setColumnsName(Data data) {
        for (int i = 0; i < data.getNumberOfAttributes(); i++) {
            this.columnsName.add(data.getAttribute(i).getName());
        }

        return this;
    }

    /**
     * Imposta i cluster calcolati
     *
     * @param set Insieme di cluster
     * @return Oggetto corrente
     */
    public APIResponse setClusterSet(ClusterSet set, Data data) {
        for (int i = 0; i < this.k; i++) {
            Cluster cluster = set.get(i);
            ClusterWrapper wrapper = ClusterWrapper.build().setMiddle(cluster)
                .setExamples(cluster, data).setAvgDistance(cluster, data);

            this.clusters.add(wrapper);
        }

        return this;
    }

    /**
     * Restituisce i nomi delle colonne
     * @return Nomi delle colonne
     */
    public ArrayList<String> getColumnsName() {
        return this.columnsName;
    }

    /**
     * Restituisce una lista di cluster
     *
     * @return Lista di cluster
     */
    public ArrayList<ClusterWrapper> getClusters() {
        return this.clusters;
    }

    /**
     * Restituisce il numero di iterazioni
     * @return Numero di iterazioni
     */
    public Integer getIteration() {
        return this.iteration;
    }

    /**
     * Restituisce il numero di cluster
     * @return Numero di cluster
     */
    public Integer getK() {
        return this.k;
    }
}
