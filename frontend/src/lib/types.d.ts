/**
 * Rappresenta i possibili design da applicare ad un componente.
 */
export type DesignOptions = "default" | "primary" | "secondary" | "error"

/**
 * Rappresenta un componente Svelte
 */
export type BaseComponent = ComponentType<SvelteComponentTyped>
