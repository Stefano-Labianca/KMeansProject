package com.kmeans.backend.repository;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.database.DatabaseConnectionException;
import com.kmeans.cluster.database.EmptySetException;
import com.kmeans.cluster.database.NoValueException;

@Repository
public class KMeansRepository {
    public Data getData(String databaseName)
            throws DatabaseConnectionException, SQLException, EmptySetException, NoValueException {
        return new Data(databaseName);
    }
}