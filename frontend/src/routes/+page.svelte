<script lang="ts">
  import type { DataCellComponent } from "$components/DataCell/dataCell"
  import type { HeadCellComponent } from "$components/HeadCell/headCell"
  import Table from "$components/Table/Table.svelte"
  import Text from "$components/Text/Text.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import type { DataRowComponent } from "$layouts/DataRow/dataRow"
  import type { HeadRowComponent } from "$layouts/HeadRow/headRow"

  const head = {
    hRow: [
      {
        columnName: "AAAAAA",
        isFirstField: true,
      },
      {
        columnName: "AAAA",
        isBaseField: true,
      },
      {
        columnName: "AAAA",
        isBaseField: true,
      },
      {
        columnName: "A",
        isLastField: true,
      },
    ] as HeadCellComponent[],
  } as HeadRowComponent

  const body = [
    {
      dRow: [
        {
          data: "BBBB",
          isFirstCell: true,
        },
        {
          data: "BB",
          isBaseCell: true,
        },
        {
          data: "BBBBBBBBB",
          isBaseCell: true,
        },
        {
          data: "BB",
          isLastCell: true,
        },
      ] as DataCellComponent[],
    },
    {
      dRow: [
        {
          data: "CCCCCCCCCC",
          isFirstCell: true,
        },
        {
          data: "C",
          isBaseCell: true,
        },
        {
          data: "CCC",
          isBaseCell: true,
        },
        {
          data: "CCC",
          isLastCell: true,
        },
      ] as DataCellComponent[],
    },
  ] as DataRowComponent[]
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="mt-20" />

<Table {head} {body} />
