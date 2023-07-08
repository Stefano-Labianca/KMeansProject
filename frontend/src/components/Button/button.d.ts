import type { BaseComponent, DesignOptions } from "$lib/types"

/**
 * Rappresenta il componente di un bottone
 * @param icon Icona del bottone
 * @param text Contenuto del bottone
 * @param loading Se impostato a true il bottone mostra l'icona di
 * caricamento, altrimenti mostra testo e icona
 * @param inverted Se impostato a true, inverte l'ordine del testo e
 * dell'icona, altrimenti non lo fa
 * @param design Rappresenta lo stile del bottone
 * @param disabled Se true allora il bottone è disabilitato, altrimenti
 * è attivo. Un bottone è disabilitato quando mostra lo spinner di caricamento
 * @param fill Se true riempie il background del bottone, altrimenti no
 * @param onClick Contiene una funzione che verrà eseguita ogni qual volta
 * l'evento 'on:click' viene attivato
 */
export type ButtonComponent = {
  icon?: BaseComponent
  text: string
  loading?: boolean
  inverted?: boolean
  design?: DesignOptions
  disabled?: boolean
  fill?: boolean
  onClick?: () => void
}
