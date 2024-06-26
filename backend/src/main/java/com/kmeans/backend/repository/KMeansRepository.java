package com.kmeans.backend.repository;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.database.DatabaseConnectionException;
import com.kmeans.cluster.database.EmptySetException;
import com.kmeans.cluster.database.NoValueException;

/**
 * 
 * <p>
 * Permette di interrogare il database MySQL per ottenere tutti i dati contenuti
 * in una tabella del database.
 * </p>
 * 
 * <p>
 * Questa viene usata principalemente nell'ambito del calcolo del KMeans, in
 * quanto attua una query del tipo {@code SELECT DISTINCT * FROM tableName},
 * dove {@code tableName} è il nome di una tabella del database a cui si è
 * collegati.
 * </p>
 */
@Repository
public class KMeansRepository {

    /**
     * Ottiene tutti i dati contenuti in una tabella di un database.
     * Insieme al risultato, espone una serie di eccezioni che, nel caso di presenza
     * di errori interi al server o da parte del client, verranno inviati a
     * quest'ultimo con annesso messaggio di errore e codice di stato HTTP.
     * 
     * @param server       Indirizzo del server
     * @param databaseName Nome del database da usare
     * @param tableName    Nome della tabella da cui prendere i dati
     * @param userId       Nome dell'utente che ha creato il database
     * @param password     Password dell utente
     * @param port         Numero di porta del dataabase
     * 
     * @return Contenuto della tabella
     * 
     * @throws DatabaseConnectionException Causata quando avviene un errore con la
     *                                     connessione al database
     * @throws SQLException                Causato quando avviene un errore con
     *                                     l'esecuzione di una query
     * @throws EmptySetException           Causata quando si prova a calcolare il
     *                                     KMeans su una tabella vuota
     * @throws NoValueException            Causata quando si prova a calcolare il
     *                                     KMeans su una tabella contenente delle
     *                                     righe vuote
     */
    public Data getData(String server, String databaseName, String tableName, String userId, String password,
            Integer port)
            throws DatabaseConnectionException, SQLException, EmptySetException, NoValueException {
        return new Data(server, databaseName, tableName, userId, password, port);
    }
}