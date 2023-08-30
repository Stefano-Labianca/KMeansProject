<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import Text from "$components/Text/Text.svelte"
  import TrashIcon from "$icons/TrashIcon.svelte"
  import type { EntryComponent } from "./entry"

  import type { AlertComponent } from "$components/Alert/alert"
  import InfoIcon from "$icons/InfoIcon.svelte"
  import LikeIcon from "$icons/LikeIcon.svelte"
  import alert from "$stores/alert"
  import { dbRecord } from "$stores/dbRecord"
  import history from "$stores/history"
  import CrudEndPoint from "../../api/crud"
  import { HISTORY_ENDPOINT } from "../../const"
  import type { HistoryEntry } from "../../types/kmeans"

  export let title: EntryComponent["title"]
  export let date: EntryComponent["date"]
  export let id: EntryComponent["id"]

  const removeEntry = async () => {
    await CrudEndPoint.remove(HISTORY_ENDPOINT.DELETE, id)

    alert.send({
      text: "Entry removed correctly",
      icon: LikeIcon,
      design: "primary",
    } as AlertComponent)

    history.removeEntry(id)
  }

  const getKMeansResult = async () => {
    const entry = await CrudEndPoint.readOne<HistoryEntry>(HISTORY_ENDPOINT.GET, id)
    const { id: hId, title: hTitle, date: hDate, ...tableInfo } = entry

    $dbRecord = tableInfo
  }
</script>

<div class="Entry">
  <div class="entry-content">
    <Text role="paragraph" text={title} />
    <Text role="subtitle" text={date} design="secondary" />
  </div>

  <div class="entry-buttons">
    <Button design="primary" icon={InfoIcon} fill onClick={getKMeansResult} />
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
    @apply w-full;
  }

  .entry-buttons {
    @apply flex;
    @apply gap-small;
  }
</style>
