import type { EntryComponent } from "$components/Entry/entry"
import { writable, type Writable } from "svelte/store"

type Remove = (id: EntryComponent["id"]) => void
type AddEntry = (entries: EntryComponent[]) => void

const history: Writable<EntryComponent[]> = writable([])

const addEntries: AddEntry = (entries: EntryComponent[]) => {
  history.update($history => {
    return [...$history, ...entries]
  })
}

const removeEntry: Remove = id => {
  history.update($history => {
    return $history.filter(entry => entry.id !== id)
  })
}

export default {
  ...history,
  addEntries,
  removeEntry,
}
