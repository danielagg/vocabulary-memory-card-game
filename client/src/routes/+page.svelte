<script lang="ts">
    import { PUBLIC_BACKEND_BASE_URL as BACKEND_BASE_URL } from '$env/static/public';
    import axios, {AxiosError} from 'axios';
    import { onMount } from 'svelte';
    import type {PageData} from './$types'
    import { fetchNextWord } from './+page';
    import  {SubmissionStatus} from './types'
    import Score from './components/score.svelte'
    import {increaseScore, resetScore} from '../store/store'
	import HowToPlay from './components/howToPlay.svelte';
	import Copyright from './components/copyright.svelte';
	import LanguageSelector from './components/languageSelector.svelte';
	import Login from './auth/loginWrapper.svelte'

    export let data: PageData;

    let userInputControl: HTMLElement | null = null;
    let submission = '';
    let submissionCorrectness = SubmissionStatus.Undetermined;
    let correctAnswer = { spanish: '', english: ''}

    onMount(() => {
		userInputControl?.focus(); 
	});

    const onKeyUp = async (e: KeyboardEvent) => {
        if (e.key === 'Enter') await onSubmit();
    }

    const onSubmit = async () => {

        const result = await axios.post(`${BACKEND_BASE_URL}/flashcard/submit`, {
            english: submission,
            spanish: data.word
        }).catch(async (e: Error | AxiosError) => {
            if (axios.isAxiosError(e))  {

                const parsedError = e as AxiosError;
                if(parsedError.response?.status === 400) {
                    const responseData = parsedError.response?.data as {spanish: {word: string}, english: {word: string}};
                    correctAnswer = { spanish: responseData.spanish.word, english: responseData.english.word }
                    submission = '';
                    submissionCorrectness = SubmissionStatus.Incorrect;
                    resetScore();

                    data = await fetchNextWord();
                    userInputControl?.focus(); 
                }
            }
        })

        if(result) {
            correctAnswer = { spanish: result.data.spanish.word, english: result.data.english.word }
            submission = '';
            submissionCorrectness = SubmissionStatus.Correct;
            increaseScore();

            data = await fetchNextWord();
            userInputControl?.focus(); 
        }
    }

</script>

<svelte:head>
    <title>Flashcards Game ??? danielagg.com</title>
	<meta name="robots" content="noindex nofollow" />
</svelte:head>

<main class="min-h-screen w-full bg-gray-100 text-gray-700 dark:bg-gray-900 dark:text-gray-400 flex justify-center">
    <div class="w-full flex flex-col items-center">

        <div class="w-full bg-black flex justify-center py-1">
            <div class="w-full lg:w-1/2 flex items-center justify-end">
                <LanguageSelector />
                <Login />
            </div>
        </div>

        <div class="w-full lg:w-1/2 mt-12 lg:mt-24">
            <div class="flex flex-col lg:flex-row justify-between">
                <div class="hidden lg:block w-2/3">
                    <HowToPlay />
                </div>
                <Score />
            </div>

            <div class="mt-8 lg:mt-12 mx-4 lg:mx-0 bg-gray-50 dark:bg-gray-700 rounded shadow p-12 flex flex-col items-center justify-center">
                <h1 class="text-5xl break-all lg:text-7xl font-bold my-12 dark:text-gray-50">{@html data.word}</h1>

                <div class="mt-10 w-full flex flex-col lg:flex-row justify-between items-center space-y-2 lg:space-y-0">
                    <input bind:this={userInputControl} bind:value={submission} on:keyup={onKeyUp} class="w-full p-4 outline-0 bg-gray-100 border-2 lg:border-r-0 border-gray-200 dark:bg-gray-900 dark:border-gray-900 rounded-lg lg:rounded-xl lg:rounded-br-none lg:rounded-tr-none placeholder-gray-300 dark:placeholder:text-gray-500 dark:text-gray-300" placeholder="Answer" type="text" />
                    <button on:click={onSubmit} class="w-full lg:w-fit bg-gray-700 dark:bg-blue-500  hover:bg-blue-400 text-gray-100 cursor-pointer py-4 px-16 font-bold rounded-lg lg:rounded-tl-none lg:rounded-bl-none">Submit</button>
                </div>
            </div>

            <div class="mx-4 lg:mx-0 h-16 my-6">
                {#if submissionCorrectness === SubmissionStatus.Correct}
                <div class="bg-green-600 dark:bg-green-800 text-white p-4 rounded text-xl flex items-center space-x-2">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-8 h-8">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M9 12.75L11.25 15 15 9.75M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                    
                    <div>You are right!</div>
                </div>
                {/if}

                {#if submissionCorrectness === SubmissionStatus.Incorrect}
        
                <div class="bg-red-600 dark:bg-red-800 text-white p-4 rounded text-xl flex items-center space-x-2">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-8 h-8">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v3.75m9-.75a9 9 0 11-18 0 9 9 0 0118 0zm-9 3.75h.008v.008H12v-.008z" />
                    </svg>
                    
                    <div>Not quite! <span class="font-bold">{correctAnswer.spanish.charAt(0).toUpperCase() + correctAnswer.spanish.slice(1)}</span> means <span class="font-bold underline">{correctAnswer.english}</span></div>
                </div>
                {/if}
            </div>

            <div class="block lg:hidden w-full p-4 text-center">
                <HowToPlay />
            </div>

            <Copyright />
        </div>
        
    </div>
</main>

