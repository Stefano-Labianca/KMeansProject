package com.kmeans.cluster.mining;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.Tuple;

import lombok.Getter;

/**
 * <h1>ClusterWrapper</h1>
 * Rappresenta una classe wrapper che permette di strutturare un Cluster
 */
@Getter
public class ClusterWrapper {
    private MiddleWrapper middle = new MiddleWrapper();
    private ArrayList<ExampleWrapper> examples = new ArrayList<ExampleWrapper>();
    private Double avgDistance;

    /**
     * Permette la costruzione di un oggetto tramite methods chaining
     * 
     * @return Oggetto classe ClusterWrapper
     */
    static public ClusterWrapper chain() {
        return new ClusterWrapper();
    }

    /**
     * Salva il centroide di un custer.
     * 
     * @param cluster Cluster da cui estrarre il centroide
     * @return Oggetto corrente
     */
    public ClusterWrapper setMiddle(Cluster cluster) {
        Tuple t = cluster.getCentroid();
        this.middle.addMiddleValue(t);

        return this;
    }

    /**
     * Raccoglie tutti i record che appartengono ad un cluster
     * 
     * @param cluster Cluster di appartenenza
     * @param data    Contenuto di una tabella del database
     * @return Oggetto corrente Oggetto corrente
     */
    public ClusterWrapper setExamples(Cluster cluster, Data data) {
        Set<Integer> examplesIndex = this.getExamplesIndex(cluster, data);
        Tuple t = cluster.getCentroid();

        for (Integer i : examplesIndex) {

            Tuple tuple = data.getItemSet(i);
            Double distance = t.getDistance(tuple);

            ExampleWrapper example = ExampleWrapper.chain()
                    .addExampleValue(tuple).setDistance(distance);

            this.examples.add(example);
        }

        return this;
    }

    /**
     * Calcola la distanza media dei record associati ad un cluster
     * 
     * @param cluster Cluster di appartenenza
     * @param data    Dati di una tabella di un database
     * @return Oggetto corrente
     */
    public ClusterWrapper setAvgDistance(Cluster cluster, Data data) {
        Tuple t = cluster.getCentroid();
        Set<Integer> examplesIndex = this.getExamplesIndex(cluster, data);

        this.avgDistance = t.avgDistance(data, examplesIndex);

        return this;
    }

    private Set<Integer> getExamplesIndex(Cluster cluster, Data data) {
        Set<Integer> clusteredData = new TreeSet<Integer>();

        for (int index = 0; index < data.getNumberOfExamples(); index++) {
            if (cluster.contain(index)) {
                clusteredData.add(index);
            }
        }

        return clusteredData;
    }

}
