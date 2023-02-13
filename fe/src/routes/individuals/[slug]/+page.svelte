<script>
    import "../../advisors/[slug]/PropertyTable.css";
    import {onMount} from "svelte";
    import Server from "$lib/Server.js";
    import {maybeError, maybeNull} from "$lib/Utils.js";
    import PropertyTable from "../../advisors/[slug]/PropertyTable.svelte";
    import DataTable from "./DataTable.svelte";
    import CurrentEmployers from "./CurrentEmployers.svelte";
    import PreviousRegistrations from "./PreviousRegistrations.svelte";
    export let data;

    let loading = false;
    let error = null;
    let indv = null;

    const QUERY = `
    query ($id: String!) {
      res: individualById (id: $id) {
        id
        info {
            lastNm firstNm midNm indvlPK actvAGReg link sufNm
        }
        otherNms {
            lastNm firstNm midNm sufNm
        }
        currentEmployers {
             orgNm orgPK str1 str2 city state cntry postlCd
             currentRegistrations {
                 regAuth regCat st stDt
             }
             branchLocations {
               str1 str2 city state cntry postlCd
             }
        }
        exams {
            exmCd
            exmNm
            exmDt
        }
        designation {
            dsgntnNm
        }
        previousRegistrations {
            orgNm orgPK regBeginDt regEndDt
            branchLocations {
                str1 str2 city state cntry postlCd
            }
        }
        employmentHistory {
            fromDt toDt orgNm city state
        }
        otherBusiness {
            desc
        }
        drps {
            hasRegAction hasCriminal hasBankrupt hasCivilJudc hasBond hasJudgment hasInvstgn hasCustComp hasTermination
        }
      }
    }`

    function hack (it) {
        it.info.link = "<a href=\"" + indv.info.link + "\">" + indv.info.link + "</a>";  // Yes, this is a hack. :)
        it.currentEmployers[0].orgPK = "<a href=\"/advisors/" + it.currentEmployers[0].orgPK + "\">" + it.currentEmployers[0].orgPK + "</a>";
        return;
    }

    async function reload () {
        try {
            loading = true;
            error = null;
            indv = null;
            const variables = { id: data.slug };
            indv = await Server._gql (QUERY, variables);
            hack (indv);
        }
        catch (e) {
            error = maybeError (e);
        }
        finally {
            loading = false;
        }
    }

    onMount (async () => {
        await reload ();
    });
</script>

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
{/if}

<h1>
    {#if indv}
        {indv.info.firstNm} {indv.info.lastNm} ({data.slug})
    {:else}
        Individual: {data.slug}
    {/if}
</h1>

{#if indv}
    <sl-tab-group>
        <sl-tab slot="nav" panel="identity">Identity</sl-tab>
        <sl-tab slot="nav" panel="current">Current Employers</sl-tab>
        <sl-tab slot="nav" panel="exams">Exams</sl-tab>
        <sl-tab slot="nav" panel="designations">Designations</sl-tab>
        <sl-tab slot="nav" panel="previous">Previous</sl-tab>
        <sl-tab slot="nav" panel="history">History</sl-tab>
        <sl-tab slot="nav" panel="other">Other</sl-tab>
        <sl-tab slot="nav" panel="drps">DRPs</sl-tab>
        <sl-tab slot="nav" panel="json">Raw</sl-tab>

        <sl-tab-panel name="identity">
            <PropertyTable props={indv.info} html={{ link: true }} />
            <h2>Other Names</h2>
            <DataTable
                    headers={["Index", "First", "Middle", "Last", "Suffix"]}
                    data={indv.otherNms}
                    xform={(e, index) => {
                        return [
                            (index + 1) + '.',
                            e.firstNm,
                            e.midNm,
                            e.lastNm,
                            maybeNull (e.sufNm)
                        ];
                    }}
            />
        </sl-tab-panel>
        <sl-tab-panel name="current">
            <CurrentEmployers currentEmployers={indv.currentEmployers} />
        </sl-tab-panel>
        <sl-tab-panel name="previous">
            <PreviousRegistrations previousRegistrations={indv.previousRegistrations} />
        </sl-tab-panel>
        <sl-tab-panel name="exams">
            <DataTable
                headers={["Index", "Name", "Code", "Date"]}
                data={indv.exams}
                xform={(e, index) => {
                    return [
                        (index + 1) + '.',
                        e.exmNm,
                        e.exmCd,
                        e.exmDt
                    ];
                }}
            />
        </sl-tab-panel>
        <sl-tab-panel name="designations">
            <DataTable
                headers={[ "Index", "Designation" ]}
                data={indv.designation}
                xform={(e, index) => {
                    return [
                        (index + 1) + '.',
                        e.dsgntnNm
                    ];
                }}
            />
        </sl-tab-panel>

        <sl-tab-panel name="history">
            <DataTable
                    headers={["Index", "Organization", "City", "State", "From", "To"]}
                    xform={(e, i) => {
                        return [
                            (i + 1) + '.',
                            e.orgNm,
                            e.city,
                            e.state,
                            e.fromDt,
                            e.toDt
                        ];
                    }}
                    data={indv.employmentHistory}
            />
        </sl-tab-panel>
        <sl-tab-panel name="other">
            {#each indv.otherBusiness as other, index}
                <sl-card class="card-basic">
                    {index + 1}. {other.desc}
                </sl-card>
            {/each}
        </sl-tab-panel>
        <sl-tab-panel name="drps">
            {#each indv.drps as drp}
                <PropertyTable props={drp} />
            {/each}
        </sl-tab-panel>
        <sl-tab-panel name="json">
            <pre>{JSON.stringify (indv, null, 2)}</pre>
        </sl-tab-panel>
    </sl-tab-group>
{/if}


<style>

</style>