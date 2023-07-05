import type { DesignOptions } from "$lib/types"

/**
 * Descrive le proprieta' pubbliche del componente "Text".
 * @param text Contenuto testuale del testo
 * @param role Ruolo del componente. Questo puo' essere un paragrafo, un titolo o un sottotitolo
 * @param design Tipologia di design da applicare
 */
export type TextComponent = {
  text?: string
  role?: "paragraph" | "title" | "subtitle"
  design?: DesignOptions
}
