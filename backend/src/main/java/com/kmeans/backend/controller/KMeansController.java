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
 * <p>
 * Viene usata per permettere al client di richiedere nuovi calcoli da far
 * svolgere all'algoritmo K-Means.
 * </p>
 * 
 * <p>
 * La route principale esposta è {@code /api}, mentre la sua sotto-route è
 * {@code /calculate}.
 * 
 * Il client, quando fa una richiesta alla route {@code
 * /api/calculate}, avviene il calcolo dell'algoritmo KMeans sulla base dei
 * parametri presenti nel corpo della richiesta.
 * </p>
 */
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class KMeansController {

    /**
     * Consente la comunicazione tra server e MySQL, permettendo la raccolta di
     * tutti i dati contenuti in una tabella.
     */
    @Autowired
    private KMeansRepository repository;

    /**
     * <p>
     * Permette di calcolare l'algoritmo K-Means sulla base della richiesta
     * fatta dal client, tramite una richiesta di tipo POST.
     * Se il calcolo è avvento con successo, allora invierà al
     * client il risultato, con codice di stato HTTP 200.
     * </p>
     * 
     * In base all'errore causato durante la computazione, il server manderà i
     * seguenti errori al client:
     * <ul>
     * <li><b>Errore 400,Bad Request</b>: Avviene quando la richiesta fatta dal
     * Client non è valida
     * </li>
     * <li><b>Errore 500,Internal Server Error</b>: Avviene quando c'è un
     * errore interno al
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

        String server = calculate.getServer();
        String tableName = calculate.getTable();
        String databaseName = calculate.getDatabase();
        Integer port = calculate.getPort();
        String username = calculate.getUsername();
        String password = calculate.getPassword();
        Integer k = calculate.getCluster();

        try {
            databaseData = repository.getData(server, databaseName, tableName, username, password, port);
        } catch (DatabaseConnectionException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Database not found", e);

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

            response = APIResponse.build().setK(k).setIteration(iteration)
                    .setColumnsName(databaseData).setClusterSet(kmeans.getC(), databaseData);

        } catch (OutOfRangeSampleSize e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "K value is too big", e);
        } catch (NegativeArraySizeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "K value is negative", e);
        }

        return response;
    }
}
