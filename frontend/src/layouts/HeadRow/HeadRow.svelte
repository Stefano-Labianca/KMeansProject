<script lang="ts">
  import HeadCell from "$components/HeadCell/HeadCell.svelte"
  import type { HeadRowComponent } from "./headRow"

  export let hRow: HeadRowComponent["hRow"]
</script>

<div class="HeadRow FadeIn">
  <div class="TableRow">
    {#each hRow as cell}
      <HeadCell {...cell} />
    {/each}
  </div>
</div>

<style lang="postcss">
  .HeadRow {
    @apply table-header-group;
  }
</style>
