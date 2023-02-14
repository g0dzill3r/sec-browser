<script>
    import {onMount} from "svelte";
    import {maybeError, withCommas} from "$lib/Utils.js";
    import Server from "$lib/Server.js";
    import PropertyTable from "../firms/[slug]/PropertyTable.svelte";

    let configuration = { "isLoaded": false };
    let error = null;
    let loading = false;
    let elapsed = -1;
    let alert = null;
    let server = Server.getServerUrl();

    async function invoke (op) {
        try {
            loading = true;
            error = null;
            alert = null;
            await op ();
        }
        catch (e) {
            console.log (e);
            error = maybeError(e);
        } finally {
            loading = false;
        }
        return;
    }

    async function loadSystemConfiguration () {
        await invoke (async () => {
            const query = "query { res: getSystemConfiguration { env filesystem } }";
            configuration = await Server._gql (query);
        });
    }

    async function reloadFirms () {
        await invoke (async () => {
            const start = Date.now ();
            const query = "mutation { res: reloadFirms { count }  }";
            const res = await Server._gql (query);
            elapsed = Date.now() - start;
            alert = "Reloaded " + withCommas (res.count) + " firms in " + withCommas (elapsed) + "ms."
        });
    }

    async function reloadIndividuals () {
        await invoke (async () => {
            const start = Date.now ();
            const query = "mutation { res: reloadIndividuals { count }  }";
            const res = await Server._gql (query);
            elapsed = Date.now() - start;
            alert = "Reloaded " + withCommas (res.count) + " individuals in " + withCommas (elapsed) + "ms."
        });
    }

    onMount (async () => {
        await loadSystemConfiguration();
    });
</script>

<h1>System Operations</h1>


{#if loading}
    <sl-alert variant="primary" open>
        <sl-spinner style="font-size: 2rem;"></sl-spinner>
        Loading ...
    </sl-alert>
    <br/>
{:else if error}
    <sl-alert variant="danger" open>
        <sl-icon slot="icon" name="exclamation-octagon"></sl-icon>
        {error}
    </sl-alert>
    <br/>
{:else if alert }
    <sl-alert variant="success" open>
        <sl-icon slot="icon" name="check2-circle"></sl-icon>
        {alert}
    </sl-alert>
    <br/>
{/if}

<h2>Server</h2>

<sl-input value={server} on:input={(e) => {
    const url = e.target.value;
    Server.setServerUrl (url);
}}></sl-input>

<h2>Configuration</h2>

<PropertyTable props={configuration} />

<h2>Updating</h2>

<sl-button variant="default" on:click={reloadFirms}>
    <sl-icon name="arrow-clockwise"></sl-icon>&nbsp;Reload Firms
</sl-button>

<sl-button variant="default" on:click={reloadIndividuals}>
    <sl-icon name="arrow-clockwise"></sl-icon>&nbsp;Reload Individuals
</sl-button>

<style>
    .Loading {
        margin-bottom: 24px;
    }
</style>