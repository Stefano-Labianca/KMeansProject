package com.kmeans.cluster.mining;

import java.util.ArrayList;

import com.kmeans.cluster.data.Tuple;

import lombok.Getter;

@Getter
public class ExampleWrapper {
    private ArrayList<Object> exampleData = new ArrayList<Object>();
    private Double distance;

    static public ExampleWrapper chain() {
        return new ExampleWrapper();
    }

    public ExampleWrapper setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public ExampleWrapper addExampleValue(Tuple t) {
        for (int i = 0; i < t.getLength(); i++) {
            this.exampleData.add(t.get(i).getValue());
        }

        return this;
    }
}
