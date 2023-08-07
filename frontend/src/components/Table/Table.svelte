<script lang="ts">
  import DataRow from "$layouts/DataRow/DataRow.svelte"
  import HeadRow from "$layouts/HeadRow/HeadRow.svelte"
  import type { TableComponent } from "./table"

  export let head: TableComponent["head"]
  export let body: TableComponent["body"]
</script>

<table class="Table">
  <thead>
    <HeadRow hRow={head.hRow} />
  </thead>

  <tbody>
    {#each body as row, i}
      <DataRow dRow={row.dRow} isLast={i === body.length - 1} />
    {/each}
  </tbody>
</table>

<style lang="postcss">
  .Table {
    @apply table;
    @apply w-full;
    @apply rounded-default;

    @apply overflow-hidden;
    @apply border-separate;
    @apply border-spacing-0;
  }
</style>
