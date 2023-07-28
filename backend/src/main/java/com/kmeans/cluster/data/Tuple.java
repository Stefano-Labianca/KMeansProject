package com.kmeans.cluster.data;

import java.util.Set;

public class Tuple {
    private final Item[] tuple;

    public Tuple(int size) {
        this.tuple = new Item[size];
    }

    public int getLength() {
        return this.tuple.length;
    }

    public Item get(int i) {
        return this.tuple[i];
    }

    public void add(Item item, int i) {
        this.tuple[i] = item;
    }

    public double getDistance(Tuple t) {
        double distance = 0;

        for (int i = 0; i < this.tuple.length; i++) {
            distance += this.tuple[i].distance(t.get(i).getValue());
        }

        return distance;
    }

    public double avgDistance(Data data, Set<Integer> clusteredData) {
        double p = 0.0D;
        double sumD = 0.0D;

        for (Integer index : clusteredData) {
            double d = this.getDistance(data.getItemSet(index));
            sumD += d;
        }

        p = sumD / (double) clusteredData.size();
        return p;
    }
}
