package com.kmeans.converter;

import java.util.ArrayList;

import lombok.Data;

@Data
public class APIRequest {
    private ArrayList<String> columnsName = new ArrayList<String>();
    private ArrayList<Object> clusters = new ArrayList<>();
    private Integer iteration;
    private Integer k;
}