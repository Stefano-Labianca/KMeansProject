import type { BaseComponent, DesignOptions } from "$lib/types"

export type AlertComponent = {
  id?: string
  icon?: BaseComponent
  text: string
  design?: DesignOptions
}
