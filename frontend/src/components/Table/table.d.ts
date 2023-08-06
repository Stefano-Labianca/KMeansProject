import type { DataRowComponent } from "$layouts/DataRow/dataRow"
import type { HeadRowComponent } from "$layouts/HeadRow/headRow"

export type TableComponent = {
  head: HeadRowComponent
  body: DataRowComponent[]
}
