import type { Wretch } from "wretch/types"
import Client from "./Client"

class ApiKMeans<T> {
  private client: Client = Client.getInstance
  private readonly transport: Wretch

  constructor() {
    this.transport = this.client.getTransport
  }

  async calculate(url: string, tableName: string, k: number): Promise<T> {
    try {
      return await this.transport.post({ tableName, k }, url).json<T>()
    } catch (error) {
      return Promise.reject(error)
    }
  }
}

export default ApiKMeans
