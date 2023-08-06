<script lang="ts">
  import Text from "$components/Text/Text.svelte"
  import type { FooterCellComponent } from "./footerCell"

  export let data: FooterCellComponent["data"]
  export let isFirstCell: FooterCellComponent["isFirstCell"] = false
  export let isLastCell: FooterCellComponent["isLastCell"] = false
  export let isBaseCell: FooterCellComponent["isBaseCell"] = false
</script>

<td class="TableCell" class:first-cell={isFirstCell} class:last-cell={isLastCell} class:base-cell={isBaseCell}>
  <Text text={data} />
</td>

<style lang="postcss">
  .first-cell {
    @apply border-r-0;
    @apply rounded-bl-default;
  }

  .last-cell {
    @apply rounded-br-default;
  }

  .base-cell {
    @apply border-r-0;
  }
</style>
