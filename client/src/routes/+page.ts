import { PUBLIC_BACKEND_BASE_URL as BACKEND_BASE_URL } from '$env/static/public';
import axios from 'axios';
// import type { PageLoad } from './$types';
import type { Load } from '@sveltejs/kit';

export const load: Load = async ({ params }: { params: any }) => {
	return await fetchNextWord();
};

export const fetchNextWord = async () => {
	const { data } = await axios.get(`${BACKEND_BASE_URL}/flashcard/random`);
	return data;
};
