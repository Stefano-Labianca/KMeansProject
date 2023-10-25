import type { HTMLInputAttributes } from "svelte/elements"

/**
 * Rappresenta il componente di un campo di input
 *
 * @param label Label del campo di input
 * @param icon Icona del campo di input
 * @param placeholder Placeholder del campo di input
 * @param id ID del campo di input
 * @param optional Se è true il campo è opzionale, altrimenti false
 * @param value Valore inserito dall'utente
 * @param error Errore mostrato in caso di input errato
 */
export interface InputComponent extends HTMLInputAttributes {
  label?: string
  icon?: BaseComponent
  placeholder?: string
  id?: string
  optional?: boolean
  value?: string | number
  error?: string
}
