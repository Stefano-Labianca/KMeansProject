package com.kmeans.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kmeans.converter.APIRequest;

public interface HistoryRepository extends MongoRepository<APIRequest, String> {

}
