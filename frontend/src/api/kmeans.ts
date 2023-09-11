import type { Calculate } from "../types/kmeans"
import ApiKMeans from "./class/APIKMeans"

const calculate: Calculate = async <T>(url: string, databaseName: string, tableName: string, k: number): Promise<T> => {
  return new ApiKMeans<T>().calculate(url, databaseName, tableName, k)
}

const KMeansEndPoint = {
  calculate,
}

export default KMeansEndPoint
