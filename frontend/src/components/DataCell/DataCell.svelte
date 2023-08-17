<script lang="ts">
  import Text from "$components/Text/Text.svelte"
  import type { DataCellComponent } from "./dataCell"

  export let data: DataCellComponent["data"]

  export let isFirstCell: boolean = false
  export let isLastCell: boolean = false
  export let isBaseCell: boolean = false

  export let isFirstBottomCell: boolean = false
  export let isLastBottomCell: boolean = false
  export let isLastMiddleCell: boolean = false
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
  <Text text={data.toString()} />
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
    @apply rounded-bl-default;
    @apply border-r-0;
  }

  .last-bottom {
    @apply rounded-br-default;
  }

  .last-base {
    @apply border-b-default;
    @apply border-r-0;
  }
</style>
