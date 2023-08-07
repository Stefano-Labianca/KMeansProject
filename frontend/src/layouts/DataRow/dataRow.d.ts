import type { DataCellComponent } from "$components/DataCell/dataCell"

export type DataRowComponent = {
  dRow: DataCellComponent[]
  isLast: boolean
}
