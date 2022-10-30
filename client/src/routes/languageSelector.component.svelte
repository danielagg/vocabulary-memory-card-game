<script lang="ts">
	  import Spain from './flags/spain.svelte'
    import Ukrainian from './flags/ukraine.svelte'
    import Hungarian from './flags/hungary.svelte'
	  import { isLanguageSelectorEnabled } from './store';

    let hidden = true;
    let enabled: boolean;
    
	  isLanguageSelectorEnabled().subscribe(x => enabled = x);
    const onToggleMenu = () => hidden = !hidden;
</script>

<div class="relative text-left inline-flex mb-12">

    {#if enabled}
    <div>
      <button on:click={onToggleMenu} type="button" class="inline-flex w-full justify-center rounded-md border px-4 py-2 text-sm font-medium focus:outline-none" id="menu-button">
        Languages
        <svg class="-mr-1 ml-2 h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
          <path fill-rule="evenodd" d="M5.23 7.21a.75.75 0 011.06.02L10 11.168l3.71-3.938a.75.75 0 111.08 1.04l-4.25 4.5a.75.75 0 01-1.08 0l-4.25-4.5a.75.75 0 01.02-1.06z" clip-rule="evenodd" />
        </svg>
      </button>
    </div>
  
    <div class={`${hidden && 'hidden'} absolute right-0 z-10 mt-12 w-56 origin-top-right rounded-md bg-gray-700 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none`}  role="menu" tabindex="-1">
      <div class="py-1" role="none">
        <div class="text-gray-200 block px-4 py-2 text-sm" role="menuitem" tabindex="-1" id="menu-item-0">
            <div class="flex items-center space-x-2">
                <Spain />                 
                <p>Spanish</p>
            </div>
        </div>
        <div class="text-gray-200 block px-4 py-2 text-sm" role="menuitem" tabindex="-1" id="menu-item-1">
            <div class="flex items-center space-x-2">
                <Ukrainian />                 
                <p>Ukrainian</p>
            </div>
        </div>
        <div class="text-gray-200 block px-4 py-2 text-sm" role="menuitem" tabindex="-1" id="menu-item-1">
            <div class="flex items-center space-x-2">
                <Hungarian />                 
                <p>Hungarian</p>
            </div>
        </div>
      </div>
    </div>
    {/if}
  </div>