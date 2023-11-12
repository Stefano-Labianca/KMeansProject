<script lang="ts">
  import Entry from "$components/Entry/Entry.svelte"
  import { DELAY, TRANSITION_BASE, TRANSITION_X_OUT, TRANSITION_Y_IN } from "$lib/animation"

  import EmptyState from "$components/EmptyState/EmptyState.svelte"
  import Text from "$components/Text/Text.svelte"
  import history from "$stores/history"
  import { flip } from "svelte/animate"
  import { fly } from "svelte/transition"
  import EmptyGliph from "../../assets/gliph/EmptyGliph.svelte"
</script>

<div class="HistoryContent">
  <Text text="History" role="title" />

  <div class="mt-4" />

  {#if $history?.length > 0}
    <div class="History HideScrollBar">
      {#each $history as entry, i (entry)}
        <div
          class="SingleEntry"
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
</div>

<style lang="postcss">
  .HistoryContent {
    @apply w-full;
  }

  .History {
    @apply flex;
    @apply flex-col;
    @apply w-full;
    @apply h-[20rem];
    @apply overflow-y-auto;
  }

  .SingleEntry {
    @apply mt-3;
  }
</style>
