import { writable, type Writable } from "svelte/store"
import type { KMeans } from "../types/kmeans"

/**
 * Store che permette di contenere le informazioni su una computazione
 */
export const dbRecord: Writable<KMeans> = writable()
