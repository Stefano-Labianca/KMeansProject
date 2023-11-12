package com.kmeans.cluster.mining;

import java.io.Serializable;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.OutOfRangeSampleSize;
import com.kmeans.cluster.data.Tuple;

/**
 * Classe che rappresenta un insieme di cluster
 * 
 * @see Cluster
 */
public class ClusterSet implements Serializable {
    /** Insieme di cluster */
    private Cluster[] C;

    /** Indice della prima posizione vuota dell'array */
    private int i = 0;

    /**
     * Inizializza un'insieme di {@code k} cluster
     * 
     * @param k Numero di cluster
     * @throws OutOfRangeSampleSize Viene causata quando il valore k supera il
     *                              numero di righe della tabella
     */
    public ClusterSet(int k) throws OutOfRangeSampleSize {
        if (k <= 0) {
            throw new NegativeArraySizeException("k deve essere maggiore di 0");
        }

        this.C = new Cluster[k];
    }

    /**
     * Aggiunge un cluster nell'insieme
     * 
     * @param c Cluster da aggiungere
     */
    public void add(Cluster c) {
        this.C[i] = c;
        this.i++;
    }

    /**
     * Restiuisce l'i-esimo cluster
     * 
     * @return i-esimo cluster
     */
    public Cluster get(int i) {
        return this.C[i];
    }

    /**
     * Inizializza i centroidi usando i dati di una tabella
     * 
     * @param data Dati di una tabella
     * @throws OutOfRangeSampleSize Viene causata quando il valore k supera il
     *                              numero di righe della tabella
     */
    public void initializeCentroids(Data data) throws OutOfRangeSampleSize {
        int[] centroidIndexes = data.sampling(this.C.length);

        for (int cIndex : centroidIndexes) {
            Tuple cID = data.getItemSet(cIndex);
            this.add(new Cluster(cID));
        }
    }

    /**
     * Determina a quale cluster una tupla è "più vicina"
     * 
     * @param tuple Tupla a cui associare il cluster più vicino
     * @return Cluster da associare alla tupla
     */
    Cluster nearestCluster(Tuple tuple) {
        double min = tuple.getDistance(this.C[0].getCentroid());
        double tempD = 0.0D;
        Cluster nearest = this.C[0];

        for (int i = 1; i < this.C.length; i++) {
            tempD = tuple.getDistance(this.C[i].getCentroid());

            if (tempD < min) {
                min = tempD;
                nearest = this.C[i];
            }
        }

        return nearest;
    }

    /**
     * Restituisce il cluster che contiene una tupla con un dato indice {@code id}
     * 
     * @param id Indice del cluster da trovare
     * @return Cluster corrente
     */
    Cluster currentCluster(int id) {
        for (Cluster cluster : this.C) {
            if (cluster.contain(id)) {
                return cluster;
            }
        }

        return null;
    }

    /**
     * Aggiorna i centroidi sulla base dei dati di una tabella
     * 
     * @param data dati di una tabella
     */
    void updateCentroids(Data data) {
        for (Cluster cluster : this.C) {
            cluster.computeCentroid(data);
        }
    }

    /**
     * Rappresenta con una stringa l'insieme di cluster
     * 
     * @return Stringa rappresentate l'insieme di cluster
     */
    public String toString() {
        String result = "";

        for (int i = 0; i < this.C.length; i++) {
            result += i + ": " + this.C[i].toString() + "\n";
        }

        return result;
    }

    /**
     * Rappresenta con una stringa i dati della tabella
     * 
     * @param Data Informazioni di una tabella
     * @return Stringa rappresentate i dati della tabella
     */
    public String toString(Data data) {
        String str = "";

        for (int i = 0; i < this.C.length; i++) {
            if (this.C[i] != null) {
                str += i + ": " + this.C[i].toString(data) + "\n";
            }
        }

        return str;
    }
}
