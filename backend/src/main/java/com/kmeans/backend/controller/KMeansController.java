package com.kmeans.backend.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.kmeans.backend.repository.KMeansRepository;
import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.OutOfRangeSampleSize;
import com.kmeans.cluster.database.DatabaseConnectionException;
import com.kmeans.cluster.database.EmptySetException;
import com.kmeans.cluster.database.NoValueException;
import com.kmeans.cluster.mining.KMeansMiner;
import com.kmeans.converter.APIResponse;
import com.kmeans.backend.domain.Calculate;

/**
 * <h1>KMeansController</h1>
 * Viene usata per permettere al client di richiedere nuovi calcoli da far
 * svolgere all'algoritmo K-Means.
 * 
 * La route che espone è {@code /api}.
 */
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class KMeansController {

    private final KMeansRepository repository;

    // Dependency Injection
    public KMeansController(KMeansRepository repository) {
        this.repository = repository;
    }

    /**
     * Permette di ottenere una computazione in base al valore k, cioè il numero di
     * Cluster da creare. Se il calcolo è avvento con successo, allora invierà al
     * client il risultato, altrimenti manderà degli errori.
     * 
     * @param k Numero di cluster
     * @return Risultato della computazione in formato JSON
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/calculate")
    public APIResponse getComputation(@RequestBody Calculate calculate) {
        Data databaseData = null;
        KMeansMiner kmeans = null;
        APIResponse response = null;
        Integer iteration;
        Integer k= calculate.getK();
        String tableName= calculate.getTableName();
        try {
            databaseData = repository.getData(tableName); // Nome della tabella data nel body della richiesta
        } catch (DatabaseConnectionException | SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Errore con la connesione al database", e);

        } catch (EmptySetException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Tabella vuota", e);
        } catch (NoValueException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Trovata colonna vuota", e);
        }

        try {
            kmeans = new KMeansMiner(k);
            iteration = kmeans.kmeans(databaseData);

            response = APIResponse.chain().setK(k).setIteration(iteration)
                    .setColumnsName(databaseData).setClusterSet(kmeans.getC(), databaseData);

        } catch (OutOfRangeSampleSize e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valore k troppo grande", e);
        } catch (NegativeArraySizeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valore k negativo", e);
        }

        return response;
    }

}
