package com.kmeans.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kmeans.converter.APIRequest;

@Repository
public interface HistoryRepository extends MongoRepository<APIRequest, String> {

}
