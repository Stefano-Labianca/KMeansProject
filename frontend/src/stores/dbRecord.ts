import { writable, type Writable } from "svelte/store"
import type { KMeans } from "../types/kmeans"

export const dbRecord: Writable<KMeans> = writable()
