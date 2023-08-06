<script lang="ts">
  import FooterCell from "$components/FooterCell/FooterCell.svelte"
  import type { FooterRowComponent } from "./footerRow"

  export let dRow: FooterRowComponent["dRow"]
</script>

<div class="FooterRow FadeIn">
  <div class="TableRow">
    {#each dRow as cell}
      <FooterCell {...cell} />
    {/each}
  </div>
</div>

<style lang="postcss">
  .FooterRow {
    @apply table-footer-group;
  }
</style>
