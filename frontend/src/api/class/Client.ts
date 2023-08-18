import ErrorIcon from "$icons/ErrorIcon.svelte"
import alerts from "$stores/alert"
import wretch, { type Wretch } from "wretch"
import type { WretchError } from "wretch/resolver"
import { BASE_URL } from "../../const"

/**
 * Classe Singleton usata per avere un'unica istanza del client
 * per attuare delle chiamate al server
 */
class Client {
  /**
   * Istanza della classe
   */
  private static instance: Client

  /**
   * Costruttore della classe Client. La sua visibilità è privata così da
   * rispettare il Singleton design patter
   */
  // eslint-disable-next-line @typescript-eslint/no-empty-function
  private constructor() {}

  private sendErrorAlert(error: WretchError): void {
    alerts.send({
      text: error.json.message,
      design: "error",
      icon: ErrorIcon,
    })
  }

  /**
   * Permette di creare una sola istanza della classe Client.
   * Ulteriori chiamate del metodo, non creeranno altre istanze.
   *
   * @returns Istanza della classe
   */
  public static get getInstance(): Client {
    if (!Client.instance) {
      this.instance = new Client()
    }

    return Client.instance
  }

  /**
   * Restituisce una nuova istanza wretch, usata per effettuare delle chiamate
   * agli endpoint esposti dal server
   *
   * @returns Una nuova istanza wretch
   */
  public get getTransport(): Wretch {
    return wretch(BASE_URL, {
      mode: "cors",
    })
      .catcher(400, error => this.sendErrorAlert(error))
      .catcher(404, error => this.sendErrorAlert(error))
      .catcher(500, error => this.sendErrorAlert(error))
  }
}

export default Client
