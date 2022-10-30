<script lang="ts">
    import { browser } from '$app/environment';
    import jwt_decode from "jwt-decode";
	import { onUserSignedIn, authenticatedUser } from '../../store/store';
	import type { AuthenticatedUser } from '../types';
	import GoogleLogin from './googleLogin.svelte';
	import GoogleLogout from './googleLogout.svelte';

    let loginButtonVisible = false;
    let user: AuthenticatedUser | null = null;
    authenticatedUser.subscribe(x => user = x);
    
    if (browser) {
        window.onLogin = (response: any) => {
            const responsePayload = jwt_decode(response.credential) as AuthenticatedUser;
            onUserSignedIn(responsePayload);
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

<div class="h-10 flex items-center">
    {#if loginButtonVisible && !user}
        <GoogleLogin />
    {/if}

    {#if user}
        <div class="text-gray-200 flex items-center space-x-2">
            <img src={user.picture} alt={`Avatar of ${user.name}`} class="h-8 w-8 rounded-full" />
            <div>{user.given_name}</div>

            <div class="pl-6">
                <GoogleLogout />
            </div>
        </div>
    {/if}
</div>
