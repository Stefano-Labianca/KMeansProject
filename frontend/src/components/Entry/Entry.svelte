<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import Text from "$components/Text/Text.svelte"
  import TrashIcon from "$icons/TrashIcon.svelte"
  import type { EntryComponent } from "./entry"

  import history from "$stores/history"
  import CrudEndPoint from "../../api/crud"

  export let title: EntryComponent["title"]
  export let date: EntryComponent["date"]
  export let id: EntryComponent["id"]

  const removeEntry = async () => {
    CrudEndPoint.remove("/history/delete", id)
    history.removeEntry(id)
  }
</script>

<div class="Entry">
  <div class="entry-content">
    <Text role="paragraph" text={title} />
    <Text role="subtitle" text={date} design="secondary" />
  </div>

  <div class="entry-button">
    <Button design="error" icon={TrashIcon} onClick={removeEntry} />
  </div>
</div>

<style lang="postcss">
  .Entry {
    @apply flex;
    @apply justify-between;
    @apply bg-secondary/light;
    @apply pl-default;
    @apply rounded-default;
    @apply w-full;
  }

  .entry-content {
    @apply py-small;
  }
</style>
