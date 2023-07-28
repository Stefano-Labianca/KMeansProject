package com.kmeans.cluster.data;

public class DiscreteItem extends Item {
    public DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    public double distance(Object a) {
        if (this.getValue().equals(a)) {
            return 0;
        }

        return 1;
    }
}
