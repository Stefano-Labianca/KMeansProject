import type { AlertComponent } from "$components/Alert/alert"
import type { EntryComponent } from "$components/Entry/entry"
import ErrorIcon from "$icons/ErrorIcon.svelte"
import alert from "$stores/alert"
import { findAll } from "../api/init"
import type { PageLoad } from "./$types"

/**
 * Permette la visualizzazione della cronologia dopo che il server ha raccolto i dati
 *
 * @returns Informazioni sulla cronologia
 */
export const load: PageLoad = async () => {
  let historyData: EntryComponent[] = []

  try {
    historyData = await findAll()
  } catch (error) {
    alert.send({
      text: "Server connection error",
      icon: ErrorIcon,
      design: "error",
    } as AlertComponent)
  }

  return {
    historyData,
  }
}
