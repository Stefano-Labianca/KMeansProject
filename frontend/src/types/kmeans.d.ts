import type { EntryComponent } from "$components/Entry/entry"
import type { FormInfo } from "$lib/formValidator"

export type Middle = {
  middleData: (string | number)[]
}

export type Example = {
  exampleData: (string | number)[]
  distance: number
}

export type Cluster = {
  middle: Middle
  examples: Example[]
  avgDistance: number
}

export type KMeans = {
  columnsName: string[]
  clusters: Cluster[]
  iteration: number
  k: number
}

export type HistoryEntry = EntryComponent & HistoryData

export type Calculate = <T>(url: string, payload: FormInfo) => Promise<T>
