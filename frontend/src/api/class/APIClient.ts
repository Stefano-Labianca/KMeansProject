import type { Wretch } from "wretch/types"
import Client from "./Client"

/**
 * Classe che permette di autare delle chiamate a tutti quegli endpoint dedicate
 * alle operazioni CRUD.
 *
 * @typeParam T - Tipo del risultato della chiamata
 */
class ApiClient<T> {
  /**
   * Instanza del client
   * @readonly
   */
  private client: Client = Client.getInstance

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
   * Attua l'operazione di `Read`, cioè una richiesta di tipo GET, ottenendo un
   * array di risultati attraverso una Promise.
   *
   * In caso di errore da parte del server, viene lanciata un'eccezione e
   * viene restituito undefined
   *
   * @param url Endpoint della richiesta
   * @returns Array di risultati o undefined
   */
  async read(url: string): Promise<T[]> {
    try {
      return await this.transport.get(url).json<T[]>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  /**
   * Attua l'operazione di `Read`, cioè una richiesta di tipo GET, ottenendo un
   * singolo risultato attraverso una Promise, usando l'`id`
   *
   * In caso di errore da parte del server, viene lanciata un'eccezione e
   * viene restituito undefined
   *
   * @param url Endpoint della richiesta
   * @param id Identidicativo univoco della entry
   * @returns Risultato o undefined
   */
  async readOne(url: string, id: number): Promise<T> {
    try {
      return await this.transport.get(`${url}/${id}`).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  /**
   * Attua l'operazione di `Create`, cioè una richiesta POST.
   *
   * In caso di errore da parte del server, viene lanciata un'eccezione e
   * viene restituito undefined
   *
   * @param url Enpoint della richiesta
   * @param payload Contenuto del body
   * @returns Risultato o undefined
   */
  async create(url: string, payload: T): Promise<T> {
    try {
      return await this.transport.post(payload, url).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  /**
   * Attua l'operazione di `Update`, cioè una richiesta PATCH.
   * A differenza della `Create`, vado ad aggiornare il contenuto di una entry
   * già presente, identificata tramite l'`id`
   *
   * In caso di errore da parte del server, viene lanciata un'eccezione e
   * viene restituito undefined
   *
   * @param url Enpoint della richiesta
   * @param payload Contenuto aggionato
   * @param id Id del contenuto da aggiornare
   *
   * @returns Risultato o undefined
   */
  async update(url: string, payload: T, id: number): Promise<T> {
    try {
      return await this.transport.patch(payload, `${url}/${id}`).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  /**
   * Attua l'operazione di `Delete`, cioè una richiesta DELETE.
   * Vado ad eliminare un'entry identificata dal suo `id`
   *
   * In caso di errore da parte del server, viene lanciata un'eccezione e
   * viene restituito undefined
   *
   * @param url Enpoint della richiesta
   * @param id Id del contenuto da eliminare
   *
   * @returns Risultato o undefined
   */
  async delete(url: string, id: number) {
    try {
      await this.transport.delete(`${url}/${id}`).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }
}

export default ApiClient
