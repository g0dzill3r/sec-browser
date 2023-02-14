<script>
    import _ from 'lodash';
    import Alert from "../../../components/Alert.svelte";
    import Server from "$lib/Server.js";
    import {onMount} from "svelte";
    import { withCommas } from "$lib/Utils.js";
    import SECReport from "$lib/SECReport.js";
    import {statesMap} from "$lib/StateUtil.js";

    let loading = false;
    let errors = null;
    let results = null;
    $: totalAdvisors = (() => {
        if (results != null) {
            return _.reduce (results, (acc, el) => acc + el[1], 0);
        } else {
            return 0;
        }
    })();

    async function load () {
        try {
            loading = true;
            errors = null;
            return await SECReport.getByState ();
        }
        catch (e) {
            errors = e.toString ();
        }
        finally {
            loading = false;
        }
    }

    onMount (async () => {
        results = await load ();
    });
</script>

{#if loading}
    <Alert message="Loading..." type="info" />
{:else if errors}
    <Alert message={errors} type="error" />
{/if}

<h1>Advisors By State</h1>

<p>Found {withCommas (totalAdvisors)} total advisors.</p>

<sl-button variant="default" on:click={async () => {
    results = await load ();
}}>
    <sl-icon name="arrow-clockwise"></sl-icon>
    &nbsp;
    Reload
</sl-button>

<br/><br/>

{#if results}
    <table class="ResultsTable">
        <thead>
        <tr>
            <th>Index</th>
            <th>State</th>
            <th>State</th>
            <th>Advisors</th>
        </tr>
        {#each results as result, index (index)}
            <tr>
                <td>
                    {index + 1}.
                </td>
                <td>
                    {statesMap[result[0]] || '-'}
                </td>
                <td>
                    {result[0]}
                </td>
                <td>
                    {withCommas (result[1])}
                </td>
            </tr>
        {/each}
        </thead>
    </table>
{/if}

<style>
    .ResultsTable {
        border-collapse: collapse;
        border-right: 1px solid lightgray;
        border-bottom: 1px solid lightgray;
    }
    .ResultsTable th {
        background-color: #e5e5e5;
    }
    .ResultsTable td, .ResultsTable tr {
        text-align: left;
        min-width: 100px;
        padding: 4px;
        border-left: 1px solid lightgray;
        border-top: 1px solid lightgray;
    }
    .ResultsTable td:nth-child(4) {
        text-align: right;
    }
</style>