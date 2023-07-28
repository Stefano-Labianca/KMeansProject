package com.kmeans.converter;

import java.util.ArrayList;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.OutOfRangeSampleSize;
import com.kmeans.cluster.mining.Cluster;
import com.kmeans.cluster.mining.ClusterSet;
import com.kmeans.cluster.mining.ClusterWrapper;

import lombok.Getter;

@Getter
public class APIResponse {
    private ArrayList<String> columnsName = new ArrayList<>();
    private ArrayList<ClusterWrapper> clusters = new ArrayList<>();
    private Integer iteration;
    private Integer k;

    /**
     * Permette la costruzione di un oggetto tramite methode chaining
     * 
     * @return Oggetto classe APIResponse
     */
    static public APIResponse chain() {
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
     * @throws OutOfRangeSampleSize
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
            ClusterWrapper wrapper = ClusterWrapper.chain().setMiddle(cluster)
                    .setExamples(cluster, data).setAvgDistance(cluster, data);

            this.clusters.add(wrapper);
        }

        return this;
    }

}