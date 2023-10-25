import type { Middle } from "../../types/kmeans"

/**
 * Rappresenta il componente di una tabella
 *
 * @param head Riga di intestazione della tabella
 * @param body Contenuto della tabella
 */
export type TableComponent = {
  head: string[]
  body: Example[] | Middle[]
}
