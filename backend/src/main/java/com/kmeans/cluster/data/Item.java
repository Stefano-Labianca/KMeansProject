package com.kmeans.cluster.data;

import java.util.Set;

public abstract class Item {
    private final Attribute attribute;
    private Object value;

    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public Object getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    abstract double distance(Object a);

    public void update(Data data, Set<Integer> clusteredData) {
        this.value = data.computePrototype(clusteredData, this.attribute);
    }
}
