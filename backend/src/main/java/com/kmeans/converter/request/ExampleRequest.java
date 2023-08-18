package com.kmeans.converter.request;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ExampleRequest {
    private ArrayList<Object> exampleData = new ArrayList<Object>();
    private Double distance;
}
