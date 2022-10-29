import { writable } from 'svelte/store';

export const score = writable(0);

export const increaseScore = () => score.update((x) => (x += 1));
export const resetScore = () => score.set(0);
