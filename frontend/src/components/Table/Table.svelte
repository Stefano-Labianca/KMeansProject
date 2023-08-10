<script lang="ts">
  import DataRow from "$layouts/DataRow/DataRow.svelte"
  import HeadRow from "$layouts/HeadRow/HeadRow.svelte"
  import type { Example, Middle } from "../../types/kmeans"
  import type { TableComponent } from "./table"

  export let head: TableComponent["head"]
  export let body: TableComponent["body"]

  let data: any

  const isMiddleArray = (): boolean => {
    if ((body as Middle[])[0].middleData !== undefined) {
      data = body as Middle[]
      return true
    }
    return false
  }

  const isExamplesArray = (): boolean => {
    if ((body as Example[])[0].exampleData !== undefined) {
      data = body as Example[]
      return true
    }
    return false
  }
</script>

<table class="Table">
  <thead>
    <HeadRow hRow={head} />
  </thead>

  <tbody>
    {#if isMiddleArray()}
      {#each data as row, i}
        <DataRow dRow={row.middleData} isLast={i === body.length - 1} />
      {/each}
    {/if}

    {#if isExamplesArray()}
      {#each data as row, i}
        <DataRow dRow={row.exampleData} isLast={i === body.length - 1} />
      {/each}
    {/if}
  </tbody>
</table>

<style lang="postcss">
  .Table {
    @apply table-auto;
    @apply rounded-default;
    @apply w-full;
    @apply overflow-hidden;
    @apply border-separate;
    @apply border-spacing-0;
  }
</style>
