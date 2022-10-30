import { writable, readable } from 'svelte/store';
import { PFClient } from '@pataflags/sdk-js';
import { PUBLIC_KOOPLE_API_KEY as KOOPLE_API_KEY } from '$env/static/public';

export const score = writable(0);
const potatoClient = PFClient.initialize(KOOPLE_API_KEY, { id: 'daniel.agg@xs.nestle.com' });

export const increaseScore = () => score.update((x) => (x += 1));
export const resetScore = () => score.set(0);

export const isLanguageSelectorEnabled = () =>
	readable(potatoClient.isEnabled('language-selector'));

export const isAuthenticationEnabled = () => readable(potatoClient.isEnabled('authentication'));
