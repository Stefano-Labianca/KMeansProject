import type { Calculate } from "../types/kmeans"
import ApiKMeans from "./class/APIKMeans"

const calculate: Calculate = async <T>(url: string, tableName: string, k: number): Promise<T> => {
  return new ApiKMeans<T>().calculate(url, tableName, k)
}

const KMeansEndPoint = {
  calculate,
}

export default KMeansEndPoint
