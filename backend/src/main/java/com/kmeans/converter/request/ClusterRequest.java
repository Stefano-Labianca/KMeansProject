package com.kmeans.converter.request;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ClusterRequest {
    private MiddleRequest middle = new MiddleRequest();
    private ArrayList<ExampleRequest> examples = new ArrayList<ExampleRequest>();
    private Double avgDistance;
}
