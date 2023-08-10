import wretch, { type Wretch } from "wretch"
import { BASE_URL } from "../../const"

class ApiClient<T> {
  private readonly _transport: Wretch

  constructor() {
    this._transport = wretch(BASE_URL, {
      mode: "cors",
    })
      .catcher(400, error => console.log(error.json))
      .catcher(500, error => console.log(error.json))
      .catcher(503, error => console.log(error.json, "Non disponibile"))
  }

  async read(url: string): Promise<T[]> {
    try {
      return await this._transport.get(url).json<T[]>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  async readOne(url: string, id: number): Promise<T> {
    try {
      return await this._transport.get(`${url}/${id}`).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  async create(url: string, payload: T): Promise<T> {
    try {
      return await this._transport.post(payload, url).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  async update(url: string, payload: T, id: number): Promise<T> {
    try {
      return await this._transport.patch(payload, `${url}/${id}`).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  async delete(url: string, id: number) {
    try {
      await this._transport.delete(`${url}/${id}`).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }
}

export default ApiClient
