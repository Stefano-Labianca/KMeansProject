package com.kmeans.backend.domain;

/**
 * <p>
 * Rappresenta la struttura del body di una richiesta fatta all'endpoint
 * {@code /api/calculate}.
 * </p>
 */
public class Calculate {
  /**
   * Indirizzo del server
   */
  private String server;

  /**
   * Nome della tabella da cui leggere tutte le righe
   */
  private String table;

  /**
   * Nome del database su cui lavorare
   */
  private String database;

  /**
   * Numero di porta
   */
  private Integer port;

  /**
   * Nome dell'utente che ha creato il database
   */
  private String username;

  /**
   * Password dell utente
   */
  private String password;

  /**
   * Numero di cluster richiesti
   */
  private Integer cluster;

  /**
   * Costuttore vuoto della classe
   */
  public Calculate() {
  }

  /**
   * Restituisce l'indirizzo del server da usare
   * @return Indirizzo del server
   */
  public String getServer() {
    return this.server;
  }

  /**
   * Restituisce il nome della tabella da usare
   * @return Tabella da usare
   */
  public String getTable() {
    return this.table;
  }

  /**
   * Restituisce il nome del database da usare
   * @return Database da usare
   */
  public String getDatabase() {
    return this.database;
  }

  /**
   * Restituisce il numero di porta usato dal database
   * @return Numero di porta del database
   */
  public Integer getPort() {
    return this.port;
  }

  /**
   * Restituisce il nome dell'utente che può accedere al database
   * @return Utente per il databse
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Restituisce la password dell'utente
   * @return Password utente
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Restituisce il numero di cluster scelti
   * @return Numero di cluster
   */
  public Integer getCluster() {
    return this.cluster;
  }
}