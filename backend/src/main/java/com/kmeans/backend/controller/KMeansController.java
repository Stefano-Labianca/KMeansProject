package com.kmeans.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kmeans.backend.repository.KMeansRepository;
import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.mining.KMeansMiner;
import com.kmeans.converter.APIResponse;

/**
 * <h1>KMeansController</h1>
 * Viene usata per permettere al client di richiedere nuovi calcoli da far
 * svolgere all'algoritmo K-Means.
 */
@CrossOrigin(origins = { "http://localhost:5173/" })
@RestController
@RequestMapping("/api")
public class KMeansController {

    private final KMeansRepository _repository;

    // Dependency Injection
    public KMeansController(KMeansRepository repository) {
        this._repository = repository;
    }

    @GetMapping("/calculate")
    public APIResponse getComputation(@RequestParam(value = "k", defaultValue = "1") Integer k) {
        Data databaseData = null;
        KMeansMiner kmeans = null;
        APIResponse response = null;
        Integer iteration;

        try {
            databaseData = _repository.getData("playtennis");
        } catch (Exception e) {
            // TODO: Inivio errore al client (Connessione al database non riuscita)
        }

        try {
            kmeans = new KMeansMiner(k);
            iteration = kmeans.kmeans(databaseData);

            response = APIResponse.chain().setK(k).setIteration(iteration)
                    .setColumnsName(databaseData).setClusterSet(kmeans.getC(), databaseData);

        } catch (Exception e) {
            // TODO: Inivio errore al client (Valore k non valido)
            e.printStackTrace();
        }

        return response;
    }

}
