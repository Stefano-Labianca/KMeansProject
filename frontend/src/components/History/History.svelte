<script lang="ts">
  import Entry from "$components/Entry/Entry.svelte"
  import { DELAY, TRANSITION_BASE, TRANSITION_X_OUT, TRANSITION_Y_IN } from "$lib/consts"

  import EmptyState from "$components/EmptyState/EmptyState.svelte"
  import history from "$stores/history"
  import { flip } from "svelte/animate"
  import { fly } from "svelte/transition"
  import EmptyGliph from "../../assets/gliph/EmptyGliph.svelte"
</script>

{#if $history?.length > 0}
  <div class="History">
    {#each $history as entry, i (entry)}
      <div
        class="entry"
        animate:flip={TRANSITION_BASE}
        in:fly|global={{ ...TRANSITION_Y_IN, delay: i * DELAY }}
        out:fly|global={TRANSITION_X_OUT}
      >
        <Entry {...entry} />
      </div>
    {/each}
  </div>
{:else}
  <EmptyState gliph={EmptyGliph} text="Empty history" subtitle="Nothing to see here" />
{/if}

<style lang="postcss">
  .History {
  }

  .entry {
    @apply mt-6;
  }
</style>
