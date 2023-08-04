export type Middle = {
  middleData: unknown[]
}

export type Example = {
  exampleData: unknown[]
  distance: number
}

export type Cluster = {
  middle: Middle
  examples: Example[]
}

export type KMeans = {
  columnsName: string[]
  clusters: Cluster[]
  iteration: number
  k: number
}
