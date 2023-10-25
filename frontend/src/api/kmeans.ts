import type { FormInfo } from "$lib/formValidator"
import type { Calculate } from "../types/kmeans"
import ApiKMeans from "./class/APIKMeans"

/**
 * Espone il metodo POST per la creazione di una computazione
 *
 * @param url Endpoint
 * @param payload Informazioni sulla computazione
 *
 * @typeParam T tipo della computazione
 *
 * @returns Risultato della computazione
 */
const calculate: Calculate = async <T>(url: string, payload: FormInfo): Promise<T> => {
  return new ApiKMeans<T>().calculate(url, payload)
}

const KMeansEndPoint = {
  calculate,
}

export default KMeansEndPoint
