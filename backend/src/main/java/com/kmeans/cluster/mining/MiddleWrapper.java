package com.kmeans.cluster.mining;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class MiddleWrapper {
    private ArrayList<Object> middleData = new ArrayList<Object>();

    public void addMiddleValue(Object value) {
        this.middleData.add(value);
    }
}
