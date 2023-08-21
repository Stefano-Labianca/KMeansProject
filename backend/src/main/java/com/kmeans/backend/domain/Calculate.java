package com.kmeans.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <h1>Calculate</h1>
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
  /**
   * Nome della tabella da cui leggere tutte le righe
   */
  private String tableName;

  /** Numero di cluster richiesti */
  private Integer k;

}