import type { BaseComponent, DesignOptions } from "$lib/types"

/**
 * Descrive il componente dell'icona
 * @param icon Componente Svelte che contiene un'icona in formato svg
 * @param design Tipologia di design da applicare
 */
export type IconComponent = {
  icon: BaseComponent
  design?: DesignOptions
}
