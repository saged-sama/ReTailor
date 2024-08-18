import type { Config } from "tailwindcss";

export default {
  content: ["./src/**/*.{html,js,svelte,ts}"],

  theme: {
    extend: {
      fontFamily: {
        "bona-nova": ["Bona Nova", "sans-serif"],
        "bona-nova-sc": ["Bona Nova SC", "sans-serif"],
        "gupter": ["Gupter", "sans-serif"],
        "platypi": ["Platypi", "sans-serif"],
        "exo": ["Exo 2", "sans-serif"],
        "shadows-into-light": ["Shadows Into Light", "cursive"],
      }
    }
  },

  daisyui: {
    themes: [
      "dim", "nord", "forest"
    ]
  },

  plugins: [
    require("daisyui")
  ]
} as Config;
