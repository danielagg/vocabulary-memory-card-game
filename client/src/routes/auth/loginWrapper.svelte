<script lang="ts">
    import { browser } from '$app/environment';
    import jwt_decode from "jwt-decode";
	import GoogleLogin from './googleLogin.svelte';

    let loginButtonVisible = false;

    if (browser) {
        window.onLogin = (response: any) => {
            const responsePayload = jwt_decode(response.credential);
            console.log(responsePayload)
        }

        // Google expects "onLogin" to be defined on "window"
        // we must wait while we actually hack that onLogin onto window,
        // because if we don't, Google will just throw a "onLogin is not a function" error,
        // and the button will be hidden
        loginButtonVisible = true;

        // another hack, why we have GoogleLogin in a separate component:
        // Google's button has an "g_id_onload" listener --> even if we do this loading state in here w/ svelte,
        // this will be called automatically by Google, still throwing this error
    }
</script>

<div class="h-10">
    {#if loginButtonVisible}
        <GoogleLogin />
    {/if}
</div>
