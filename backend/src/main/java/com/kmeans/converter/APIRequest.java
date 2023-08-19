package com.kmeans.converter;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Document(collection = "calculation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIRequest {

    @Id
    private String id;
    private String date;
    private String title;

    private ArrayList<String> columnsName = new ArrayList<String>();
    private ArrayList<Object> clusters = new ArrayList<>();
    private Integer iteration;
    private Integer k;
}