import { quartOut } from "svelte/easing"

/**
 * Tempo di visualizzazione, in millisecondi, della notifica
 */
export const ALERT_TIMEOUT = 4000

export const DELAY = 90

/**
 * Contiene le informazioni base di una transizione, come
 * durata (in millisecondi) e tipologia di transizione
 */
export const TRANSITION_BASE = {
  duration: 300,
  ease: quartOut,
}

export const TRANSITION_Y_IN = {
  ...TRANSITION_BASE,
  y: 40,
}

export const TRANSITION_Y_OUT = {
  ...TRANSITION_BASE,
  y: -40,
}

export const TRANSITION_X_IN = {
  ...TRANSITION_BASE,
  x: 40,
}

export const TRANSITION_X_OUT = {
  ...TRANSITION_BASE,
  x: -40,
}
