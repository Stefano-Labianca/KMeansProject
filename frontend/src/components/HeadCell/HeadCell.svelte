<script lang="ts">
  import Text from "$components/Text/Text.svelte"
  import type { HeadCellComponent } from "./headCell"

  export let columnName: HeadCellComponent["columnName"]

  export let isFirstField: boolean = false
  export let isLastField: boolean = false
  export let isBaseField: boolean = false
</script>

<th
  class="HeadCell TableCell"
  class:first-field-name={isFirstField}
  class:last-field-name={isLastField}
  class:base-field={isBaseField}
>
  <Text text={columnName} design="primary" />
</th>

<style lang="postcss">
  .HeadCell {
    @apply bg-primary/light;
  }

  .first-field-name {
    @apply rounded-tl-default;
    @apply border-b-0;
    @apply border-r-0;
  }

  .last-field-name {
    @apply rounded-tr-default;
    @apply border-b-0;
  }

  .base-field {
    @apply border-b-0;
    @apply border-r-0;
  }
</style>
