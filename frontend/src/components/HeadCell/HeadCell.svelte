<script lang="ts">
  import Text from "$components/Text/Text.svelte"
  import type { HeadCellComponent } from "./headCell"

  export let columnName: HeadCellComponent["columnName"]

  export let isFirstField: HeadCellComponent["isFirstField"] = false
  export let isLastField: HeadCellComponent["isLastField"] = false
  export let isBaseField: HeadCellComponent["isBaseField"] = false
</script>

<th
  class="HeadCell TableCell"
  class:first-field-name={isFirstField}
  class:last-field-name={isLastField}
  class:base-field={isBaseField}
>
  <Text text={columnName} design="primary" role="paragraph" />
</th>

<style lang="postcss">
  .HeadCell {
    @apply bg-primary/light;
  }

  .first-field-name {
    @apply rounded-tl-corner;
    @apply border-b-0;
    @apply border-r-0;
  }

  .last-field-name {
    @apply rounded-tr-corner;
    @apply border-b-0;
  }

  .base-field {
    @apply border-b-0;
    @apply border-r-0;
  }
</style>
