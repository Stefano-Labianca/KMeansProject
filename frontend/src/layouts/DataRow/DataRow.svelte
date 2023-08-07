<script lang="ts">
  import DataCell from "$components/DataCell/DataCell.svelte"
  import type { DataRowComponent } from "./dataRow"

  export let dRow: DataRowComponent["dRow"]
  export let isLast: DataRowComponent["isLast"]
</script>

<tr class="DataRow">
  {#each dRow as cell, i}
    <DataCell
      {...cell}
      isFirstBottomCell={isLast && i === 0}
      isLastBottomCell={isLast && i === dRow.length - 1}
      isLastMiddleCell={isLast && i > 0 && i < dRow.length - 1}
    />
  {/each}
</tr>

<style lang="postcss">
  .DataRow {
    @apply transition;
    @apply hover:bg-secondary/light;
  }
</style>
