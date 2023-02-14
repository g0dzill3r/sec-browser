<script>
    import {onMount} from "svelte";
    import Server from "$lib/Server.js";
    import {maybeError, withCommas} from "$lib/Utils.js";
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

    const QUERY = "query ($req: SECIndividualFindRequest!) { " +
        "  res: findIndividuals (req: $req) { " +
        "    skip" +
        "    limit" +
        "    total " +
        "    results { " +
        "      id" +
        "      info { lastNm firstNm midNm indvlPK link sufNm }" +
        "      currentEmployers { orgNm orgPK }" +
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

<h1>Registered Individuals</h1>

{#if loading}
    <sl-alert variant="primary" open>
        <sl-spinner style="font-size: 2rem;"></sl-spinner>
        Loading ...
    </sl-alert>
{:else if error}
    <sl-alert variant="danger" open>
        <sl-icon slot="icon" name="exclamation-octagon"></sl-icon>
        {error}
    </sl-alert>
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


    <table class="IndividualTable">
        <thead>
        <tr>
            <th colspan="4" style="text-align: center">
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
            <th>ID</th>
            <th>CurrentEmployer(s)</th>
        </tr>
        </thead>
        <tbody>
        {#each results.results as indv, index}
            <tr>
                <td>{index + skip + 1}.</td>
                <td>
                    <a href={"/individuals/" + indv.id}>
                        {indv.info.firstNm} {indv.info.midNm || ''} { indv.info.lastNm} {indv.info.sufNm || ''}
                    </a>
                </td>
                <td>{indv.id}</td>
                <td>
                    <a href={"/firms/" + indv.currentEmployers[0].orgPK}>
                        {indv.currentEmployers[0].orgNm}
                    </a>
                </td>
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
    .IndividualTable {
        border-collapse: collapse;
        border-bottom: 1px solid lightgray;
        border-right: 1px solid lightgray;
        border-radius: 6px;
        margin-bottom: 8px;
        width: 100%;
    }
    .IndividualTable td, .IndividualTable th {
        padding: 6px;
        text-align: left;
        border-top: 1px solid lightgray;
        border-left: 1px solid lightgray;
    }
</style>