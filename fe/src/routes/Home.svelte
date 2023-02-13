<script>
    import Server from "$lib/Server.js";
    import {withCommas} from "$lib/Utils.js";
    import {onMount} from "svelte";

    let results = null;
    let error = null;
    let loading = false;

    async function loadStats () {
        try {
            loading = true;
            error = null;
            const query = "query { secReport { count generatedOn } secIndividuals { count generatedOn } }"
            results = (await Server.gql (query)).data;
        }
        catch (e) {
            error = e.toString ()
        }
        finally {
            loading = false;
        }
        return
    }

    onMount (async () => {
       await loadStats ()
    });
</script>

<style>
    .SecLogo {
        width: 96px;
        height: 96px;
        margin: 6px;
    }
</style>

{#if error}
    <div>
        {error}
    </div>
{/if}

<img src="sec-logo.png" class="SecLogo" alt="" />
<br />
SEC - Registered Investment Advisor Data (full - XML)
<br/>
<a href="https://adviserinfo.sec.gov/compilation">
    https://adviserinfo.sec.gov/compilation
</a>

{#if results}
    <ul>
        <li>Firms
            <ul>
                <li>{withCommas (results.secReport.count)} loaded</li>
                <li>Generated: {results.secReport.generatedOn}</li>
            </ul>
        </li>
        <li>Individuals
            <ul>
                <li>{withCommas (results.secIndividuals.count)} loaded</li>
                <li>Generated: {results.secIndividuals.generatedOn}</li>
            </ul>
        </li>
    </ul>
{/if}
<!-- EOF -->