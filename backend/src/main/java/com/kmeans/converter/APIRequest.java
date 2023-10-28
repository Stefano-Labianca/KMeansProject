package com.kmeans.converter;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 
 * <p>
 * Rappresenta le entry della collezione "calculation" del database MongoDB.
 * </p>
 * 
 * <p>
 * Questa stessa struttura viene usata nel client per inviare al server una
 * quella computazione che si vuole salvare nella collezione.
 * </p>
 */
@Data
@Document(collection = "calculation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIRequest {

    /**
     * Identificativo della entry all'intero della collezione
     */
    @Id
    private String id;

    /**
     * Data di creazione dell'entry
     */
    private String date;

    /**
     * Nome della computazione
     */
    private String title;

    /**
     * Lista contenente i nomi delle tabelle del database MySQL, da cui si è fatta
     * una computazione.
     */
    private ArrayList<String> columnsName = new ArrayList<String>();

    /**
     * Lista di cluster
     */
    private ArrayList<Object> clusters = new ArrayList<>();

    /**
     * Iterazioni fatte dall'algoritmo per ottenere i cluster.
     */
    private Integer iteration;

    /**
     * Numero di centroidi richiesti.
     */
    private Integer k;
}