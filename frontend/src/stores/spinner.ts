import type { DesignOptions } from "$lib/types"
import { writable, type Writable } from "svelte/store"

/**
 * Store che permette la modifica del design del componente Spinner
 */
export const spinnerModifier: Writable<DesignOptions> = writable("default")

/**
 * Store che segnala se il bottone premuto è pieno (true) oppure no (false)
 */
export const isButtonFilled: Writable<boolean> = writable(false)
