import type { BaseComponent, DesignOptions } from "$lib/types"

export type ButtonComponent = {
  icon?: BaseComponent
  text: string
  loading?: boolean
  inverted?: boolean
  design?: DesignOptions
  disabled?: boolean
  fill?: boolean
}
