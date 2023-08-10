import wretch, { type Wretch } from "wretch"
import { BASE_URL } from "../../const"

class ApiKMeans<T> {
  private readonly _transport: Wretch

  constructor() {
    this._transport = wretch(BASE_URL, { mode: "cors" })
      .catcher(400, error => console.log(error.json))
      .catcher(500, error => console.log(error.json))
      .catcher(503, error => console.log(error.json))
  }

  async calculate(url: string, tableName: string, k: number): Promise<T> {
    try {
      return await this._transport.post({ tableName, k }, url).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }
}

export default ApiKMeans
