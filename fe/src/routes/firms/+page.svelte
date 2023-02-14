<script>
    import _ from 'lodash';
    import {onMount} from "svelte";
    import Server from "$lib/Server.js";
    import {maybeError, withCommas} from "$lib/Utils.js";
    import Alert from "../../components/Alert.svelte";
    import Address from "../../components/Address.svelte";
    import Pagination from "../../components/Pagination.svelte";
    import Filters from "./Filters.svelte";

    let skip = 0;
    let limit = 25;
    let loading = false;
    let error = null;
    let results = null;

    let drawer = null;
    let filters = {  };
    $: hasFilters = Object.keys (filters).length !== 0;
    const updateFilters = (newFilters) => {
        filters = newFilters;
        skip = 0;
        reload ();
    };
    const showDrawer = () => drawer.show ();

    const QUERY = "query ($req: SECFirmFindRequest!) { " +
        "  res: findFirms (req: $req) { " +
        "    skip" +
        "    limit" +
        "    total " +
        "    results { " +
        "      id" +
        "      secId" +
        "      info { secRegnCD firmCrdNb secNb busNm legalNm umbrRgstn }" +
        "      mainAddr { strt1 strt2 city state postlCd }" +
        "      individualsCount" +
        "    }" +
        "  }" +
        "}"
    
    async function reload () {
        try {
            loading = true;
            error = null;
            results = null;
            const variables = {
                req: {
                    skip,
                    limit,
                    filters
                }
            };
            results = await Server._gql (QUERY, variables);
        } catch (e) {
            error = maybeError (e);
        } finally {
            loading = false;
        }
    }

    onMount (async () => {
        await reload ();
    });
</script>

<h1>Registered Investment Advisors</h1>

{#if loading}
    <Alert message="Loading..." />
{:else if error}
    <Alert message={error} type="error" />
{/if}

{#if results}
    <p>
        Displaying {results.results.length} of {withCommas (results.total)} results.
        {#if hasFilters}
            <sl-icon name="funnel-fill" on:click={showDrawer}></sl-icon>
        {:else}
            <sl-icon on:click={showDrawer} name="funnel"></sl-icon>
        {/if}
    </p>

    <table class="FirmTable">
        <thead>
            <tr>
                <th colspan="6" style="text-align: center">
                    <Pagination
                        skip={results.skip}
                        limit={results.limit}
                        total={results.total}
                        controller={newSkip => {
                            skip = newSkip;
                            reload ()
                        }}
                    />
                </th>
            </tr>
            <tr>
                <th>Index</th>
                <th>Name</th>
                <th>Address</th>
                <th>ID</th>
                <th>SEC ID</th>
                <th>Reps</th>
            </tr>
        </thead>
        <tbody>
        {#each results.results as firm, index}
            <tr>
                <th>{index + skip + 1}.</th>
                <th>
                    <a href={"/firms/" + firm.id}>
                        {firm.info.busNm}
                    </a>
                </th>
                <th>
                    <Address addr={firm.mainAddr} />
                </th>
                <th>{firm.id}</th>
                <th>{firm.secId}</th>
                <th>{firm.individualsCount}</th>
            </tr>
        {/each}
        </tbody>
    </table>
{/if}

<sl-button variant="default" on:click={reload}>
    <sl-icon name="arrow-clockwise"></sl-icon>
    &nbsp;
    Reload
</sl-button>

<Filters filters={filters} onUpdate={newFilters => updateFilters (newFilters)} bind:this={drawer} />

<style>
    .FirmTable {
        border-collapse: collapse;
        border-bottom: 1px solid lightgray;
        border-right: 1px solid lightgray;
        border-radius: 6px;
        margin-bottom: 8px;
        width: 100%;
    }
    .FirmTable td, .FirmTable th {
        padding: 6px;
        text-align: left;
        border-top: 1px solid lightgray;
        border-left: 1px solid lightgray;
    }
</style>