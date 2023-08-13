import type { Wretch } from "wretch/types"
import Client from "./Client"

class ApiClient<T> {
  private client: Client = Client.getInstance
  private readonly transport: Wretch

  constructor() {
    this.transport = this.client.getTransport
  }

  async read(url: string): Promise<T[]> {
    try {
      return await this.transport.get(url).json<T[]>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  async readOne(url: string, id: number): Promise<T> {
    try {
      return await this.transport.get(`${url}/${id}`).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  async create(url: string, payload: T): Promise<T> {
    try {
      return await this.transport.post(payload, url).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  async update(url: string, payload: T, id: number): Promise<T> {
    try {
      return await this.transport.patch(payload, `${url}/${id}`).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }

  async delete(url: string, id: number) {
    try {
      await this.transport.delete(`${url}/${id}`).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }
}

export default ApiClient
