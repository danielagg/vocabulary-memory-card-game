<svelte:head>
    <script src="https://accounts.google.com/gsi/client" async defer></script>
</svelte:head>

<script lang="ts">
    import { PUBLIC_GOOGLE_CLIENT_ID as CLIENT_ID } from '$env/static/public';
</script>

<div>
    <div id="g_id_onload"
        data-client_id={CLIENT_ID}
        data-context="signin"
        data-ux_mode="popup"
        data-callback='onLogin'
        data-auto_prompt="false">
    </div>

    <div class="g_id_signin"
        data-type="standard"
        data-shape="rectangular"
        data-theme="filled_blue"
        data-text="signin_with"
        data-size="large"
        data-logo_alignment="left"
        data-width="200">
    </div>
</div>