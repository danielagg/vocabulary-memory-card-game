<script>
    /** @type {import('./$types').PageData} */
    export let data;
  </script>

<main class="min-h-screen w-full bg-gray-100 text-gray-700 flex justify-center lg:items-center">
    <div class="w-full lg:w-1/2 flex flex-col">

        <!-- <div class="lg:self-end flex flex-col items-center lg:items-end mt-8 lg:mt-0">
            <h2 class="text-5xl font-bold text-sky-600">6</h2>
            <p class="text-sm uppercase">Score</p>
        </div> -->
        
        <div class="mt-8 lg:mt-12 mx-4 lg:mx-0 bg-gray-50 rounded shadow p-12 flex flex-col items-center justify-center">
            <h1 class="text-5xl break-all lg:text-7xl font-bold my-12">{@html data.word}</h1>

            <div class="mt-10 w-full flex flex-col lg:flex-row justify-between items-center space-y-2 lg:space-y-0">
                <input class="w-full p-4 outline-0 bg-gray-100 border-2 lg:border-r-0 border-gray-200 rounded-lg lg:rounded-xl lg:rounded-br-none lg:rounded-tr-none placeholder-gray-300" placeholder="Answer" type="text" />
                <button class="w-full lg:w-fit bg-gray-700 border-2 border-gray-700 hover:bg-sky-500 hover:border-sky-500 text-gray-100 cursor-pointer py-4 px-12 font-bold rounded-lg lg:rounded-tl-none lg:rounded-bl-none">Submit</button>
            </div>
        </div>

    </div>
</main>

