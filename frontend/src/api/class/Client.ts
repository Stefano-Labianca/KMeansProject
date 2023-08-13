import wretch, { type Wretch } from "wretch"
import { BASE_URL } from "../../const"

class Client {
  private static instance: Client

  // eslint-disable-next-line @typescript-eslint/no-empty-function
  private constructor() {}

  public static get getInstance(): Client {
    if (!Client.instance) {
      this.instance = new Client()
    }

    return Client.instance
  }

  public get getTransport(): Wretch {
    return wretch(BASE_URL, {
      mode: "cors",
    })
      .catcher(400, error => console.log(error.json))
      .catcher(500, error => console.log(error.json))
      .catcher(503, error => console.log(error.json, "Non disponibile"))
  }
}

export default Client
