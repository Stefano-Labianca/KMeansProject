<script lang="ts">
  import Entry from "$components/Entry/Entry.svelte"
  import type { EntryComponent } from "$components/Entry/entry"
  import {
    TRANSITION_BASE,
    TRANSITION_X_OUT,
    TRANSITION_Y_IN,
  } from "$lib/consts"
  import history from "$stores/history"
  import dayjs from "dayjs"
  import { nanoid } from "nanoid"

  import { flip } from "svelte/animate"
  import { fly } from "svelte/transition"

  history.update(() => {
    return [
      {
        id: nanoid(),
        title: "First",
        date: dayjs().format("D MMMM YYYY"),
      },
      {
        id: nanoid(),
        title: "Second",
        date: dayjs().add(1, "day").format("D MMMM YYYY"),
      },
      {
        id: nanoid(),
        title: "Third",
        date: dayjs().add(2, "day").format("D MMMM YYYY"),
      },
    ] as EntryComponent[]
  })
</script>

<div class="History">
  {#each $history as entry (entry)}
    <div
      class="entry"
      animate:flip={TRANSITION_BASE}
      in:fly={TRANSITION_Y_IN}
      out:fly={TRANSITION_X_OUT}
    >
      <Entry {...entry} />
    </div>
  {/each}
</div>

<style lang="postcss">
  .History {
  }

  .entry {
    @apply mt-6;
  }
</style>
