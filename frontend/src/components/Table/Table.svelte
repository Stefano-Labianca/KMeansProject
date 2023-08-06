<script lang="ts">
  import DataRow from "$layouts/DataRow/DataRow.svelte"
  import FooterRow from "$layouts/FooterRow/FooterRow.svelte"
  import HeadRow from "$layouts/HeadRow/HeadRow.svelte"
  import type { TableComponent } from "./table"

  export let head: TableComponent["head"]
  export let body: TableComponent["body"]

  let rows = body.slice(0, body.length - 1)
  let footer = body[body.length - 1]
</script>

<div class="Table">
  <HeadRow hRow={head.hRow} />

  {#each rows as row}
    <DataRow dRow={row.dRow} />
  {/each}

  <FooterRow dRow={footer.dRow} />
</div>

<style lang="postcss">
  .Table {
    @apply table;
    @apply w-full;
  }
</style>
