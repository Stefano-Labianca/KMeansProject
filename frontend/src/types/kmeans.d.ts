export type Middle = {
  middleData: EntryType[]
}

export type Example = {
  exampleData: EntryType[]
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

export type EntryType = string | number

export type Calculate = <T>(url: string, tableName: string, k: number) => Promise<T>
