/**
 * Tipo della chiamata la chiamata al metodo GET
 *
 * @param url Endpoint della chiamata
 * @typeParam T tipo da restituire
 * @returns Array contenente oggetti di tipo T
 *
 */
type Read = <T>(url: string) => Promise<T[]>

/**
 * Tipo della chiamata la chiamata al metodo GET
 *
 * @param url Endpoint della chiamata
 * @typeParam T tipo da restituire
 * @returns Oggetto di tipo T
 *
 */
type ReadOne = <T>(url: string, id: string) => Promise<T>

/**
 * Tipo della chiamata la chiamata al metodo POST
 *
 * @param url Endpoint della chiamata
 * @param payload Oggetto di tipo T
 * @typeParam T tipo da aggiungere
 */
type Create = <T>(url: string, payload: T) => Promise<void>

/**
 * Tipo della chiamata al metodo DELETE
 *
 * @param url Endpoint della chiamata
 * @param id Identificatore dell'oggetto di tipo T
 * @typeParam T tipo eliminare
 */
type Delete = (url: string, id: string) => Promise<unknown>
