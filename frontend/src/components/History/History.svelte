<script lang="ts">
  import Entry from "$components/Entry/Entry.svelte"
  import { DELAY, TRANSITION_BASE, TRANSITION_X_OUT, TRANSITION_Y_IN } from "$lib/consts"

  import history from "$stores/history"
  import { flip } from "svelte/animate"
  import { fly } from "svelte/transition"
  import type { HistoryComponent } from "./history"

  export let historyData: HistoryComponent["history"] = undefined
</script>

{#if historyData}
  <div class="History">
    {#each $history as entry, i (entry)}
      <div
        class="entry"
        animate:flip={TRANSITION_BASE}
        in:fly={{ ...TRANSITION_Y_IN, delay: i * DELAY }}
        out:fly={TRANSITION_X_OUT}
      >
        <Entry {...entry} />
      </div>
    {/each}
  </div>
{/if}

<style lang="postcss">
  .History {
  }

  .entry {
    @apply mt-6;
  }
</style>
