<script lang="ts">
  import Text from "$components/Text/Text.svelte"
  import type { DataCellComponent } from "./dataCell"

  export let data: DataCellComponent["data"]
  export let isFirstCell: DataCellComponent["isFirstCell"] = false
  export let isLastCell: DataCellComponent["isLastCell"] = false
  export let isBaseCell: DataCellComponent["isBaseCell"] = false
</script>

<td class="TableCell" class:first-cell={isFirstCell} class:last-cell={isLastCell} class:base-cell={isBaseCell}>
  <Text text={data} />
</td>

<style lang="postcss">
  .first-cell {
    @apply border-b-0;
    @apply border-r-0;
  }

  .last-cell {
    @apply border-b-0;
  }

  .base-cell {
    @apply border-b-0;
    @apply border-r-0;
  }
</style>
