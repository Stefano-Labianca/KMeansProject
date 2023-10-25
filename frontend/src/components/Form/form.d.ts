import type { ButtonComponent } from "$components/Button/button"
import type { HTMLFormAttributes } from "svelte/elements"

/**
 * Rappresenta il componente di un form
 * @param button Bottone di invio del form
 */
export interface FormComponent extends HTMLFormAttributes {
  button: ButtonComponent
}
