import type { HTMLInputAttributes } from "svelte/elements"

export interface InputComponent extends HTMLInputAttributes {
  label?: string
  icon?: BaseComponent
  placeholder?: string
  id?: string
  optional?: boolean
  value?: string | number
  error?: string
}
