<script lang="ts">
  import DataRow from "$layouts/DataRow/DataRow.svelte"
  import HeadRow from "$layouts/HeadRow/HeadRow.svelte"
  import type { Example, Middle } from "../../types/kmeans"
  import type { TableComponent } from "./table"

  export let head: TableComponent["head"]
  export let body: TableComponent["body"]

  const isMiddleArray = (x: any): x is Middle[] => {
    return (x as Middle[])[0].middleData !== undefined
  }

  const isExamplesArray = (x: any): x is Example[] => {
    return (x as Example[])[0].exampleData !== undefined
  }
</script>

<table class="Table">
  <thead>
    <HeadRow hRow={head} />
  </thead>

  <tbody>
    {#if isMiddleArray(body)}
      <DataRow dRow={body[0].middleData} isLast />
    {/if}

    {#if isExamplesArray(body)}
      {#each body as row, i}
        <DataRow dRow={row.exampleData} isLast={i === body.length - 1} />
      {/each}
    {/if}
  </tbody>
</table>

<style lang="postcss">
  .Table {
    @apply table-auto;
    @apply rounded-default;
    @apply overflow-hidden;
    @apply border-separate;
    @apply border-spacing-0;
    @apply w-full;
  }
</style>
