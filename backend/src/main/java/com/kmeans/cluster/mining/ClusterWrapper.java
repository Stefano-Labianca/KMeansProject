package com.kmeans.cluster.mining;

import java.util.ArrayList;
import java.util.Set;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.Tuple;

import lombok.Getter;

@Getter
public class ClusterWrapper {
    private MiddleWrapper middle = new MiddleWrapper();
    private ArrayList<ExampleWrapper> examples = new ArrayList<ExampleWrapper>();
    private Double avgDistance;

    static public ClusterWrapper chain() {
        return new ClusterWrapper();
    }

    public ClusterWrapper setMiddle(Cluster cluster) {
        Tuple t = cluster.getCentroid();

        for (int i = 0; i < t.getLength(); i++) {
            this.middle.addMiddleValue(t.get(i).getValue());
        }

        return this;
    }

    public ClusterWrapper setExamples(Cluster cluster, Data data) {
        Set<Integer> examplesIndex = cluster.clusteredData;
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

    public ClusterWrapper setAvgDistance(Cluster cluster, Data data) {
        Tuple t = cluster.getCentroid();
        Set<Integer> examplesIndex = cluster.clusteredData;

        this.avgDistance = t.avgDistance(data, examplesIndex);

        return this;
    }
}
