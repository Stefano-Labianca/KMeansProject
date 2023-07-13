<script lang="ts">
  import type { GliphComponent } from "./gliph"

  export let gliph: GliphComponent["gliph"]
</script>

<div class="Gliph">
  <svelte:component this={gliph} />
</div>

<style lang="postcss">
  .Gliph {
    @apply w-gliph;
    @apply h-gliph;
  }
</style>
