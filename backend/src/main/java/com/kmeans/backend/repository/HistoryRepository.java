package com.kmeans.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kmeans.converter.APIRequest;

/**
 * 
 * <p>
 * Permette di interrogare il database MongoDB tramite operazioni CRUD.
 * </p>
 */
public interface HistoryRepository extends MongoRepository<APIRequest, String> {

}
