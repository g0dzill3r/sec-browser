<script>
    import DataTable from "./DataTable.svelte";
    import {maybeNull} from "$lib/Utils.js";
    import PropertyTable from "../../firms/[slug]/PropertyTable.svelte";

    export let currentEmployers;
</script>


{#each currentEmployers as ce, index}
    <h1>{ce.orgNm}</h1>
    <PropertyTable
        props={{
            "Primary Key": ce.orgPK,
            "Street": ce.str1,
            "Street2": ce.str2,
            "City": ce.city,
            "State": ce.state,
            "Postal Code": ce.postlCd,
            "Country": ce.cntry
        }}
        html={{ "Primary Key": true }}
    />

    <h2>Registrations</h2>
    <DataTable
        headers={[
            "Index", "Category", "Authority", "Status", "Date"
        ]}
        xform={(e, i) => {
            return [
                (i + 1) + '.',
                e.regCat, e.regAuth, e.st, e.stDt
            ];
        }}
        data={ce.currentRegistrations}
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
        data={ce.branchLocations}
    />
{/each}

<style>

</style>

<!-- EOF -->