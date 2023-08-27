package com.kmeans.cluster.mining;

import java.io.Serializable;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.OutOfRangeSampleSize;
import com.kmeans.cluster.data.Tuple;

public class ClusterSet implements Serializable {
    private Cluster[] C;
    private int i = 0;

    public ClusterSet(int k) throws OutOfRangeSampleSize {
        if (k <= 0) {
            throw new NegativeArraySizeException("k deve essere maggiore di 0");
        }

        this.C = new Cluster[k];
    }

    public void add(Cluster c) {
        this.C[i] = c;
        this.i++;
    }

    public Cluster get(int i) {
        return this.C[i];
    }

    public void initializeCentroids(Data data) throws OutOfRangeSampleSize {
        int[] centroidIndexes = data.sampling(this.C.length);

        for (int cIndex : centroidIndexes) {
            Tuple cID = data.getItemSet(cIndex);
            this.add(new Cluster(cID));
        }
    }

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

    Cluster currentCluster(int id) {
        for (Cluster cluster : this.C) {
            if (cluster.contain(id)) {
                return cluster;
            }
        }

        return null;
    }

    void updateCentroids(Data data) {
        for (Cluster cluster : this.C) {
            cluster.computeCentroid(data);
        }
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < this.C.length; i++) {
            result += i + ": " + this.C[i].toString() + "\n";
        }

        return result;
    }

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
