import { opacity } from "tailwindcss/defaultTheme"

/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{html,js,svelte,ts}"],
  theme: {
    extend: {
      fontFamily: {
        sans: ["Outfit", "sans-serif"],
      },

      fontSize: {
        small: "1rem",
        medium: "1.5rem",
        large: "2.5rem",
      },

      borderRadius: {
        default: "5px",
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
        "chart-bg": "#101010",
        primary: "#7053FF",
        secondary: "#409FD4",
        error: "#EB5757",
      },
    },
  },
  plugins: [],
}
