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
      },
      spacing: {
        '128': '32rem',
        '144': '36rem',
        '160': '40rem',
      }
    }
  },

  daisyui: {
    themes: [
      "dim",
      // {
      //   retailor: {
      //       "primary": "#fa053e",
          
      //       "secondary": "#1cfc03",
                      
      //       "accent": "#00955a",
                      
      //       "neutral": "#040702",
                      
      //       "base-100": "#121c22",
                      
      //       "info": "#14ffd4",
                      
      //       "success": "#00a676",
                      
      //       "warning": "#ffab3e",
                      
      //       "error": "#ff627f",
      //   }
      // }
    ]
  },

  plugins: [
    require("daisyui"),
    require("@tailwindcss/typography"),
  ]
} as Config;
