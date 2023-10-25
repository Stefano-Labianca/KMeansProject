import ApiClient from "./class/APIClient"

/**
 * Espone al client la chiamata al metodo GET
 *
 * @param url Endpoint della chiamata
 * @typeParam T tipo da restituire
 * @returns Array contenente oggetti di tipo T
 *
 */
const read: Read = async <T>(url: string): Promise<T[]> => {
  return await new ApiClient<T>().read(url)
}

/**
 * Espone al client la chiamata al metodo GET
 *
 * @param url Endpoint della chiamata
 * @typeParam T tipo da restituire
 * @returns Oggetto di tipo T
 *
 */
const readOne: ReadOne = async <T>(url: string, id: string): Promise<T> => {
  return await new ApiClient<T>().readOne(url, id)
}

/**
 * Espone al client la chiamata al metodo POST
 *
 * @param url Endpoint della chiamata
 * @param payload Oggetto di tipo T
 * @typeParam T tipo da aggiungere
 */
const create: Create = async <T>(url: string, payload: T): Promise<void> => {
  await new ApiClient<T>().create(url, payload)
}

/**
 * Espone al client la chiamata al metodo DELETE
 *
 * @param url Endpoint della chiamata
 * @param id Identificatore dell'oggetto di tipo T
 * @typeParam T tipo eliminare
 */
const remove: Delete = async <T>(url: string, id: string): Promise<unknown> => {
  return new ApiClient<T>().delete(url, id)
}

/**
 * Oggetto contenente i metodi che applicano operazioni CRUD
 */
const CrudEndPoint = {
  read,
  readOne,
  create,
  remove,
}

export default CrudEndPoint
