import type { EntryComponent } from "$components/Entry/entry"
import { writable, type Writable } from "svelte/store"

type Remove = (id: EntryComponent["id"]) => void

const history: Writable<EntryComponent[]> = writable([])

const removeEntry: Remove = id => {
  history.update($history => {
    return $history.filter(entry => entry.id !== id)
  })
}

export default {
  ...history,
  removeEntry,
}
