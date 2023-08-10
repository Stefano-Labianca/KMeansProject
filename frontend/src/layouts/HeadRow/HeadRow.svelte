<script lang="ts">
  import HeadCell from "$components/HeadCell/HeadCell.svelte"
  import type { HeadRowComponent } from "./headRow"

  export let hRow: HeadRowComponent["hRow"]
</script>

<tr class="HeadRow">
  {#each hRow as cell, i}
    <HeadCell
      columnName={cell}
      isFirstField={i === 0}
      isBaseField={i > 0 && i < hRow.length - 1}
      isLastField={i === hRow.length - 1}
    />
  {/each}
</tr>

<style lang="postcss">
  .HeadRow {
    @apply transition;
    @apply hover:bg-secondary/light;
  }
</style>
