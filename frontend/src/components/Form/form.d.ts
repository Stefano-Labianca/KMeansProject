import type { ButtonComponent } from "$components/Button/button"
import type { HTMLFormAttributes } from "svelte/elements"

export interface FormComponent extends HTMLFormAttributes {
  button: ButtonComponent
}
