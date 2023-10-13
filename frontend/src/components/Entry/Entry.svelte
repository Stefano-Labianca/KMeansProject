<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import Text from "$components/Text/Text.svelte"
  import TrashIcon from "$icons/TrashIcon.svelte"
  import type { EntryComponent } from "./entry"

  import { goto } from "$app/navigation"
  import type { AlertComponent } from "$components/Alert/alert"
  import ErrorIcon from "$icons/ErrorIcon.svelte"
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

  let loadingGet = false
  let loadingRemove = false

  const deleteEntry = async () => {
    loadingRemove = true

    try {
      await CrudEndPoint.remove(HISTORY_ENDPOINT.DELETE, id)
    } catch (error) {
      Promise.reject(error)
      loadingRemove = false

      alert.send({
        text: "Server connection error",
        icon: ErrorIcon,
        design: "error",
      } as AlertComponent)
    }

    // TODO: Trovare un modo per mandare la notifica di errore quando il server è spento
    // nel caso della rimozione

    alert.send({
      text: "Entry removed correctly",
      icon: LikeIcon,
      design: "primary",
    } as AlertComponent)

    history.removeEntry(id)
  }

  const getKMeansResult = async () => {
    loadingGet = true
    let entry: HistoryEntry | undefined = undefined

    try {
      entry = await CrudEndPoint.readOne<HistoryEntry>(HISTORY_ENDPOINT.GET, id)
    } catch (error) {
      loadingGet = false

      alert.send({
        text: "Server connection error",
        icon: ErrorIcon,
        design: "error",
      } as AlertComponent)
    }

    if (entry) {
      const { id: hId, title: hTitle, date: hDate, ...tableInfo } = entry
      $dbRecord = tableInfo
      goto("/calculation")
    }
  }
</script>

<div class="Entry">
  <div class="entry-content">
    <Text role="paragraph" text={title} />
    <Text role="subtitle" text={date} design="secondary" />
  </div>

  <div class="entry-buttons">
    <Button loading={loadingGet} design="primary" icon={InfoIcon} fill onClick={getKMeansResult} />
    <Button loading={loadingRemove} design="error" icon={TrashIcon} onClick={deleteEntry} />
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
    @apply gap-4;
  }
</style>
