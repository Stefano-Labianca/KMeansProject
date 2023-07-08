import { quartOut } from "svelte/easing"

// Misc
export const ALERT_TIMEOUT = 4000

// Transitions
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
