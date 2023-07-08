import type { BaseComponent, DesignOptions } from "$lib/types"

/**
 * Rappresenta il componente Alter, ovvero una notifica visibile a schermo.
 * @param id ID della notifica
 * @param icon Icona della notifica
 * @param text Testo della notifica
 * @param design Il tipo di design da associare alla notifica
 */
export type AlertComponent = {
  id?: string
  icon?: BaseComponent
  text: string
  design?: DesignOptions
}
