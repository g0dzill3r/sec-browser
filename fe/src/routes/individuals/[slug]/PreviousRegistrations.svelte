<script>
    import DataTable from "./DataTable.svelte";
    import {maybeNull} from "$lib/Utils.js";
    import PropertyTable from "../../firms/[slug]/PropertyTable.svelte";

    export let previousRegistrations;
</script>

{#each previousRegistrations as pe, index}
    <h1>{pe.orgNm}</h1>
    <PropertyTable
        props={{
            "Primary Key": "<a href=\"/firms/" + pe.orgPK + "\">" + pe.orgPK + "</a>",
            "Reg Begin": pe.regBeginDt,
            "Reg End": pe.regEndDt
        }}
        html={{
            "Primary Key": true
        }}
    />

    <h2>Locations</h2>
    <DataTable
        headers={[
            "Index", "Street", "Street2", "City", "State", "PostalCode", "Country"
        ]}
        xform={(e, i) => {
            return [
                (i + 1) + '.',
                e.str1,
                maybeNull (e.str2),
                e.city, e.state, e.postlCd, e.cntry
            ];
        }}
        data={pe.branchLocations}
    />
{/each}

<style>

</style>

<!-- EOF -->