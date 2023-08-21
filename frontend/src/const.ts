/** Url base del server */
export const BASE_URL = "http://localhost:8080"

/** Stringa di formattazione della data visualizzata nelle entry della cronologia */
export const DAYJS_FORMAT = "D MMMM YYYY"

/** Contiene tutti gli endpoint associati alla manipolazione della cronologia */
export const HISTORY_ENDPOINT = {
  /** Endpoint da usare in caso si vogliano prendere tutte le computazioni salvate o una in particolare */
  GET: "/history/get",

  /** Endpoint da usare in caso si voglia salvare una computazione */
  POST: "/history/add",

  /** Endpoint da usare in caso si voglia eliminare una computazione */
  DELETE: "/history/delete",
}

/** Enpoint da usare quando si vuole richiede una computazione da parte del server */
export const API_CALCULATE = "/api/calculate"
