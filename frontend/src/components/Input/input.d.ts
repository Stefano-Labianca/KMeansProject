import type { IComponent } from "$lib/types"
import type { HTMLInputAttributes } from "svelte/elements"

export interface IInput extends HTMLInputAttributes {
  label?: string
  icon?: IComponent
  placeholder?: string
  id?: string
  optional?: boolean
  value?: string | number
}
