package com.kmeans.backend.controller;

import java.util.List;

import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kmeans.backend.repository.HistoryRepository;
import com.kmeans.converter.APIRequest;

/**
 * <h1>HistoryController</h1>
 * <p>
 * Questa classe espose una serie di endopoint, con route principale
 * {@code /history}, che permette al client di attuare operazioni CRUD su un
 * database MongoDB.
 * </p>
 * 
 * <p>
 * Le operazioni che espone sono del tipo: Create, Read e Delete dove, per ogni
 * operazione, viene associato un metodo HTTP.
 * 
 * Ogni operazione ha associato una sua route a cui il client può accedervi.
 * </p>
 * 
 * <p>
 * Le route che espone sono: {@code /add}, {@code /get}, {@code /get/id},
 * {@code /delete/id} dove id dell'URI rappresenta l'id di un'etry della
 * collezione di MongoDB usata per salvare e leggere tutti i calcoli fatti
 * dall'algoritmo.
 * </p>
 */
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/history")
public class HistoryController {

    /**
     * Consente la comunicazione tra server e MongoDB, permettendo di attuare
     * operazioni CRUD su una collezione di MongoDB.
     */
    private final HistoryRepository repository;

    /**
     * Costruttore usato per la Dependecy Injection di un oggetto di tipo
     * {@link SimpleMongoRepository} che permette di attuare operazioni CRUD.
     * 
     * @param repository Oggetto di tipo {@link SimpleMongoRepository}
     */
    public HistoryController(HistoryRepository repository) {
        this.repository = repository;
    }

    /**
     * <p>
     * Permette di salvare una computazione fatta dall'algoritmo all'intero della
     * collezione del database, inviando al client un codice di stato HTTP 202, per
     * confermarli il successo della sua richiesta POST.
     * </p>
     * 
     * <p>
     * La computazione salvata si trova all'intero del body della richiesta.
     * </p>
     * 
     * @param req Body della richiesta contenente una computazione
     * @return Entry salvata nella collezione
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public APIRequest save(@RequestBody APIRequest req) {
        return this.repository.save(req);
    }

    /**
     * <p>
     * Restituisce tutte le entry della collezione di MongoDB, restituendo un codice
     * di stato HTTP 200.
     * </p>
     * 
     * @return Tutte le entry della collezione
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get")
    public List<APIRequest> findAll() {
        return this.repository.findAll();
    }

    /**
     * <p>
     * Restituisce una entry della collezione di di MongoDB, identificata
     * dall'{@code id} contenuto nell'URI della richiesta.
     * 
     * Questo id verrà salvato all'intero del parametro del metodo
     * </p>
     * 
     * <p>
     * In caso l'id inviato non dovesse corrispondere a nessuna entry, il server
     * manderà un errore di tipo <b>NOT FOUND</b> con codice di stato 404, per
     * indicare la mancanza dell'entry ricercata all'intero della collezione.
     * </p>
     * 
     * @param id Id dell'entry da visualizzare
     * @return Entry identificata dall'{@code id}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/{id}")
    public APIRequest findById(@PathVariable String id) {
        return this.repository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found");
        });
    }

    /**
     * <p>
     * Elimina una entry della collezione di di MongoDB, identificata
     * dall'{@code id} contenuto nell'URI della richiesta.
     * 
     * Questo id verrà salvato all'intero del parametro del metodo
     * </p>
     * 
     * @param id Id dell'entry da eliminare
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        this.repository.deleteById(id);
    }

}
