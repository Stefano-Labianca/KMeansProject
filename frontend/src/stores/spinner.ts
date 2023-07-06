import type { DesignOptions } from "$lib/types"
import { writable, type Writable } from "svelte/store"

export const spinnerModifier: Writable<DesignOptions> = writable("default")
export const isButtonFilled: Writable<boolean> = writable(false)
