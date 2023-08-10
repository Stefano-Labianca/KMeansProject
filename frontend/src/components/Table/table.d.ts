import type { Middle } from "../../types/kmeans"

export type TableComponent = {
  head: string[]
  body: Example[] | Middle[]
}
