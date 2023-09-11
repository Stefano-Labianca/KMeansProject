import type { Wretch } from "wretch/types"
import Client from "./Client"

/**
 * Classe che permette di autare delle chiamate a tutti quegli endpoint dedicati
 * per l'algoritmo KMeans.
 *
 * @typeParam T - Tipo del risultato della computazione
 */
class ApiKMeans<T> {
  /**
   * Instanza del client
   * @readonly
   */
  private readonly client: Client = Client.getInstance

  /**
   * Permette di attuare delle chiamate al server
   * @readonly
   */
  private readonly transport: Wretch

  /**
   * Inizializza il client per attuare delle chiamate al server
   */
  constructor() {
    this.transport = this.client.getTransport
  }

  /**
   * Attua una richiesta di tipo POST e permette di attuare una nuova computazione da
   * parte dell'algoritmo KMeans, restituendo il risultato calcolato come una Promise.
   *
   * I parametri passati, come `tableName` e `k`, verranno inseriti all'intero del
   * body della richiesta, mentre `url` viene usata per capire a quale endpoint bisogna
   * fare la richiesta.
   *
   * In caso il server dovesse generare un errore, verrà restituito undefined e verrà
   * lanciata un eccezione, con annesso messaggio di errore
   *
   * @param url Endpoint a cui fare la richiesta
   * @param databaseName Nome del database
   * @param tableName Nome della tabella da usare nel KMeans
   * @param k Numero di cluster da calcolare
   * @returns Il risulato della computazione oppure undefine
   */
  async calculate(url: string, databaseName: string, tableName: string, k: number): Promise<T> {
    try {
      return await this.transport.post({ databaseName, tableName, k }, url).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }
}

export default ApiKMeans
