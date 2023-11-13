import { ALERT_TIMEOUT } from "$lib/animation"
import { nanoid } from "nanoid"
import { writable, type Writable } from "svelte/store"
import type { AlertComponent } from "../components/Alert/alert"

/**
 * Rappresenta il metodo di invio di una notifica
 * @param alert Notifica da visualizzare
 */
type Send = (alert: AlertComponent) => void

/**
 * Rappresenta il metodo di cancellazione di una notifica
 * @param id ID della notifica da eliminare
 */
type Remove = (id: AlertComponent["id"]) => void

/**
 * Store che permette di contenere le notifiche attualmente attive, cioe' quelle
 * che sono visualizzabili a schermo
 */
const alerts: Writable<AlertComponent[]> = writable([])

/**
 * Permette l'invio di una notifica
 * @param alert Componente Alert da inviare
 */
const send: Send = alert => {
  const id = nanoid()
  alert.id = id

  alerts.update($alerts => {
    return [...$alerts, alert]
  })

  setTimeout(() => remove(id), ALERT_TIMEOUT)
}

/**
 * Permette la cancellazione di una notifica
 * @param id ID di una notifica
 */
const remove: Remove = id => {
  alerts.update($alerts => {
    return $alerts.filter(alert => alert.id !== id)
  })
}

export default {
  ...alerts,
  send,
  remove,
}
