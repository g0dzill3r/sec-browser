<script>
    import {onMount} from "svelte";
    import Alert from "../../components/Alert.svelte";
    import {maybeError, withCommas} from "$lib/Utils.js";
    import SECReport from "$lib/SECReport.js";
    import Server from "$lib/Server.js";

    let error = null;
    let loading = false;
    let elapsed = -1;
    let alert = null;

    async function reloadFirms () {
        const start = Date.now ();
        try {
            loading = true;
            error = null;
            alert = null;
            const query = "mutation { res: reloadFirms { count }  }";
            const res = await Server._gql (query);
            elapsed = Date.now() - start;
            alert = "Reloaded " + withCommas (res.count) + " firms in " + withCommas (elapsed) + "ms."
        } catch (e) {
            error = maybeError (e);
        } finally {
            loading = false;
        }
        return;
    }
    async function reloadIndividuals () {
        const start = Date.now ();
        try {
            loading = true;
            error = null;
            alert = null;
            const query = "mutation { res: reloadIndividuals { count }  }";
            const res = await Server._gql (query);
            elapsed = Date.now() - start;
            alert = "Reloaded " + withCommas (res.count) + " individuals in " + withCommas (elapsed) + "ms."
        } catch (e) {
            error = maybeError (e);
        } finally {
            loading = false;
        }
        return;
    }

    onMount (async () => {
        // report = await loadStats ()
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