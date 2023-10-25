import type { EntryComponent } from "$components/Entry/entry"
import type { FormInfo } from "$lib/formValidator"

/** Rappresenta un centroide */
export type Middle = {
  /** Contiene le informazioni sul centroide */
  middleData: (string | number)[]
}

/** Rappresenta una riga del database */
export type Example = {
  /** Dati della riga */
  exampleData: (string | number)[]

  /** Distanza dal centroide */
  distance: number
}

/** Rappresenta un cluster */
export type Cluster = {
  /** Centroide */
  middle: Middle

  /** Righe del database associate al centroide */
  examples: Example[]

  /** Distanza media */
  avgDistance: number
}

/** Rappresenta una computazione del K-Means */
export type KMeans = {
  /** Nome delle colonne */
  columnsName: string[]

  /** Cluster generati */
  clusters: Cluster[]

  /** Numero di iterazioni fatte */
  iteration: number

  /** Numero di cluster */
  k: number
}

/** Rappresenta una entry della cronologia */
export type HistoryEntry = EntryComponent & HistoryData

/**
 * Rappresenta il tipo della funzione di calcolo dei centroidi
 *
 * @url Entrypoint
 * @param payload Informazioni raccolte dal client tramite form
 *
 * @typeParam T tipo della computazione
 *
 * @returns Computazione di tipo T
 */
export type Calculate = <T>(url: string, payload: FormInfo) => Promise<T>
