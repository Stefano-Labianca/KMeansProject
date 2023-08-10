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

export type Calculate = <T>(url: string, tableName: string, k: number) => Promise<T>
