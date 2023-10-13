import type { FormInfo } from "$lib/formValidator"
import type { Calculate } from "../types/kmeans"
import ApiKMeans from "./class/APIKMeans"

const calculate: Calculate = async <T>(url: string, payload: FormInfo): Promise<T> => {
  return new ApiKMeans<T>().calculate(url, payload)
}

const KMeansEndPoint = {
  calculate,
}

export default KMeansEndPoint
