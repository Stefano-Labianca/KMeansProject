package com.kmeans.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * <p>
 * Rappresenta la struttura del body di una richiesta fatta all'endpoint
 * {@code /api/calculate}.
 * </p>
 * 
 */
@Getter
@NoArgsConstructor
public class Calculate {
  /** Indirizzo del server */
  private String server;

  /** Nome della tabella da cui leggere tutte le righe */
  private String table;

  /** Nome del database su cui lavorare */
  private String database;

  /** Numero di porta */
  private Integer port;

  /** Nome dell'utente che ha creato il database */
  private String username;

  /** Password dell utente */
  private String password;

  /** Numero di cluster richiesti */
  private Integer cluster;

}