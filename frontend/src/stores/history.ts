import type { EntryComponent } from "$components/Entry/entry"
import { writable, type Writable } from "svelte/store"

/**
 * Tipo della funzione di rimozione delle entry alla cronologia
 *
 * @param id ID dell'entry da rimuovere
 */
type Remove = (id: EntryComponent["id"]) => void

/**
 * Tipo della funzione di aggiunta delle entry alla cronologia
 * @param entries Cosa aggiungere
 */
type AddEntry = (entries: EntryComponent[]) => void

/** Store che contiene le informazion sulla cronologia */
const history: Writable<EntryComponent[]> = writable([])

/**
 * Aggiunge una nuova entry nella cronologia.
 *
 * @param entries Entries della cronologia
 */
const addEntries: AddEntry = (entries: EntryComponent[]) => {
  history.update($history => {
    return [...$history, ...entries]
  })
}

/**
 * Rimuove una entry dalla cronologia in base al suo id, restituendo
 * l'array aggiornato.
 *
 * @param id ID dell'entry
 */
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
