import { ALERT_TIMEOUT } from "$lib/consts"
import { nanoid } from "nanoid"
import { writable, type Writable } from "svelte/store"
import type { AlertComponent } from "../components/Alert/alert"

type Send = (alert: AlertComponent) => void
type Remove = (id: AlertComponent["id"]) => void

const alerts: Writable<AlertComponent[]> = writable([])

const send: Send = alert => {
  const id = nanoid()
  alert.id = id

  alerts.update($alerts => {
    return [...$alerts, alert]
  })

  setTimeout(() => remove(id), ALERT_TIMEOUT)
}

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
