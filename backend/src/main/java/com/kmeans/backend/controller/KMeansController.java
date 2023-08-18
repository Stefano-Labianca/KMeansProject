package com.kmeans.backend.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kmeans.backend.domain.Calculate;
import com.kmeans.backend.repository.KMeansRepository;
import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.OutOfRangeSampleSize;
import com.kmeans.cluster.database.DatabaseConnectionException;
import com.kmeans.cluster.database.EmptySetException;
import com.kmeans.cluster.database.NoValueException;
import com.kmeans.cluster.mining.KMeansMiner;
import com.kmeans.converter.APIResponse;

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

    @Autowired
    private KMeansRepository repository;

    /**
     * Permette di calcolare l'algoritmo K-Means sulla base della richiesta
     * fatta dal client, tramite una richiesta di tipo POST.
     * Se il calcolo è avvento con successo, allora invierà al
     * client il risultato, altrimenti manderà degli errori:
     * <ul>
     * <li>Errore 400,Bad Request:Avviene quando la richiesta fatta dal Client non è
     * valida</li>
     * <li>Errore 500,Internal Server Error:Avviene quando c'è un errore interno al
     * server</li>
     * </ul>
     * 
     * @param calculate Contenuto del body della richiesta del client
     * @return Risultato della computazione in formato JSON
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/calculate")
    public APIResponse getComputation(@RequestBody Calculate calculate) {
        Data databaseData = null;
        KMeansMiner kmeans = null;
        APIResponse response = null;
        Integer iteration;
        Integer k = calculate.getK();
        String tableName = calculate.getTableName();

        try {
            databaseData = repository.getData(tableName); // Nome della tabella data nel body della richiesta
        } catch (DatabaseConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Database connection error", e);

        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found", e);
        }

        catch (EmptySetException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Empty table", e);
        } catch (NoValueException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Found empty column", e);
        }

        try {
            kmeans = new KMeansMiner(k);
            iteration = kmeans.kmeans(databaseData);

            response = APIResponse.chain().setK(k).setIteration(iteration)
                    .setColumnsName(databaseData).setClusterSet(kmeans.getC(), databaseData);

        } catch (OutOfRangeSampleSize e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "K value is too big", e);
        } catch (NegativeArraySizeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "K value is negative", e);
        }

        return response;
    }
}
