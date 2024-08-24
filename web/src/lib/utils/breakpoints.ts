export let currentBreakpoint = '';

export const updateBreakpoint = () => {
    currentBreakpoint = getBreakpoint();
    return currentBreakpoint;
};

function getBreakpoint() {
    const width = window.innerWidth;
    if (width <= breakpoints["max-sm"]) return 'max-sm';
    if (width <= breakpoints["max-md"]) return 'max-md';
    if (width <= breakpoints["max-lg"]) return 'max-lg';
    if (width <= breakpoints["max-xl"]) return "max-xl";
    return 'max-2xl';
}

const breakpoints = {
    "max-sm": 640,
    "max-md": 768,
    "max-lg": 1024,
    "max-xl": 1280,
    'max-2xl': 1536
};