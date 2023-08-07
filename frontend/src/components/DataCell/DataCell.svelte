<script lang="ts">
  import Text from "$components/Text/Text.svelte"
  import type { DataCellComponent } from "./dataCell"

  export let data: DataCellComponent["data"]
  export let isFirstCell: DataCellComponent["isFirstCell"] = false
  export let isLastCell: DataCellComponent["isLastCell"] = false
  export let isBaseCell: DataCellComponent["isBaseCell"] = false

  export let isFirstBottomCell: DataCellComponent["isFirstBottomCell"] = false
  export let isLastBottomCell: DataCellComponent["isLastBottomCell"] = false
  export let isLastMiddleCell: DataCellComponent["isLastMiddleCell"] = false
</script>

<td
  class="TableCell"
  class:first-cell={isFirstCell}
  class:last-cell={isLastCell}
  class:base-cell={isBaseCell}
  class:first-bottom={isFirstBottomCell}
  class:last-bottom={isLastBottomCell}
  class:last-base={isLastMiddleCell}
>
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

  .first-bottom {
    @apply rounded-bl-corner;
    @apply border-b-default;
  }

  .last-bottom {
    @apply rounded-br-corner;
    @apply border-b-default;
  }

  .last-base {
    @apply border-b-default;
  }
</style>
