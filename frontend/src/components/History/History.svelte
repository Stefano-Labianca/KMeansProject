<script lang="ts">
  import Entry from "$components/Entry/Entry.svelte"
  import type { EntryComponent } from "$components/Entry/entry"
  import history from "$stores/history"
  import dayjs from "dayjs"
  import { nanoid } from "nanoid"

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
    <div class="entry">
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
