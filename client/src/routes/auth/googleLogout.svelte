<script lang="ts">
	import { onUserSignedOut } from '../../store/store';
    const onSignOut = () => onUserSignedOut();

</script>

<button class="g_id_signout cursor-pointer underline text-blue-200 text-xs" on:click={onSignOut}>Sign out</button>