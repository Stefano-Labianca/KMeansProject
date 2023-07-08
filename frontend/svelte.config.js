import adapter from "@sveltejs/adapter-auto"
import { vitePreprocess } from "@sveltejs/kit/vite"

/** @type {import('@sveltejs/kit').Config} */
const config = {
  preprocess: vitePreprocess(),

  kit: {
    adapter: adapter({
      precompress: true,
    }),
    alias: {
      $lib: "./src/lib",
      $pages: "./src/pages",
      $stores: "./src/stores",
      $components: "./src/components",
      $icons: "./src/assets/icons",
      $layouts: "./src/layouts",
    },
  },
}

export default config
