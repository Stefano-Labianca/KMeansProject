import { fontFamily, opacity, transitionDuration } from "tailwindcss/defaultTheme"

/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{html,js,svelte,ts}"],
  theme: {
    extend: {
      fontFamily: {
        sans: ["Outfit", ...fontFamily.sans],
      },

      fontSize: {
        small: "1rem",
        medium: "1.5rem",
        large: "2.5rem",
      },

      borderRadius: {
        default: "5px",
        corner: "16px",
      },

      borderWidth: {
        default: "2px",
      },

      transitionDuration: {
        default: transitionDuration["300"],
      },

      transitionTimingFunction: {
        default: "cubic-bezier(0.165, 0.84, 0.44, 1)",
      },

      gap: {
        small: "0.5rem",
        medium: "0.75rem",
      },

      padding: {
        default: "1.25rem",
        small: "0.5rem",
      },

      width: {
        gliph: "9.375rem",
      },

      height: {
        gliph: "9.375rem",
      },

      opacity: {
        light: opacity[5],
        soft: opacity[10],
        medium: opacity[30],
        strong: opacity[80],
      },

      colors: {
        background: "#070707",
        title: "#F1F1EF",
        subtitle: "#ADADAD",
        gray: "#9CA3AF",
        overlay: "#101010",
        primary: "#7053FF",
        secondary: "#409FD4",
        error: "#EB5757",
      },
    },
  },
  plugins: [],
}
