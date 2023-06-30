import type { DesignOptions } from "$lib/types"

/**
 * Descrive il componente dell'icona
 * @param icon Componente Svelte che contiene un'icona in formato svg
 * @param design Tipologia di design da applicare
 */
export type IconComponent = {
  icon: ComponentType<SvelteComponentTyped>
  design: DesignOptions
}
