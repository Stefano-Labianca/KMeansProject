<script lang="ts">
  import DataCell from "$components/DataCell/DataCell.svelte"
  import type { DataRowComponent } from "./dataRow"

  export let dRow: DataRowComponent["dRow"]
</script>

<div class="DataRow FadeIn">
  <div class="TableRow">
    {#each dRow as cell}
      <DataCell {...cell} />
    {/each}
  </div>
</div>

<style lang="postcss">
  .DataRow {
    @apply table-row-group;
  }
</style>
