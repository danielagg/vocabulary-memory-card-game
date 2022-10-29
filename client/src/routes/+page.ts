import { PUBLIC_BACKEND_BASE_URL as BACKEND_BASE_URL } from '$env/static/public';

/** @type {import('./$types').PageLoad} */
export const load = async ({ params }: { params: any }) => {
	const getRandomSpanishWord = async () => {
		const result = await fetch(`${BACKEND_BASE_URL}/flashcard/random`);
		return result.json();
	};

	return await getRandomSpanishWord();
};
