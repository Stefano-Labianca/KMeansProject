/** @type {import('tailwindcss').Config} */
export default {
	content: ["./src/**/*.{html,js,svelte,ts}"],
	theme: {
		extend: {
			fontFamily: {
				sans: ["Outfit", "sans-serif"],
			},

			colors: {
				transparent: "transparent",
				current: "currentColor",
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
