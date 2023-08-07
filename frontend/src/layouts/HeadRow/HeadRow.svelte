<script lang="ts">
  import HeadCell from "$components/HeadCell/HeadCell.svelte"
  import type { HeadRowComponent } from "./headRow"

  export let hRow: HeadRowComponent["hRow"]
</script>

<tr class="HeadRow">
  {#each hRow as cell}
    <HeadCell {...cell} />
  {/each}
</tr>

<style lang="postcss">
  .HeadRow {
    @apply transition;

    @apply hover:bg-secondary/light;
  }
</style>
