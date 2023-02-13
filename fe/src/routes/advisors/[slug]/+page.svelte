<script>
    import "./PropertyTable.css";
    import {onMount} from "svelte";
    import Server from "$lib/Server.js";
    import Alert from "../../../components/Alert.svelte";
    import _ from "lodash";
    import SECInfo from "./SECInfo.svelte";
    import PropertyTable from "./PropertyTable.svelte";
    import Item1A from "./Item1A.svelte";
    import Item2A from "./Item2A.svelte";
    import Item2B from "./Item2B.svelte";
    import Item3A from "./Item3A.svelte";
    import Item3B from "./Item3B.svelte";
    import Item3C from "./Item3C.svelte";
    import {maybeError} from "$lib/Utils.js";
    import DataTable from "../../individuals/[slug]/DataTable.svelte";
    export let data;

    let loading = false;
    let error = null;
    let firm = null;

    const QUERY = `
    query ($id: String!) {
        res: firmById (id: $id) {
            id
            secId
            info {
                secRegnCD firmCrdNb secNb busNm legalNm umbrRgstn
            }
            mainAddr {
                strt1 strt2 city state cntry postlCd phNb faxNb
            }
            mailingAddr {
                strt1 strt2 city state cntry postlCd phNb faxNb
            }
            registration {
                firmType state date
            }
            noticeFiled {
                states {
                    regltrCd state date
                }
            }
            filing {
                date formVersion
            }
            formInfo {
                part1A {
                   item1 { q1f5 q1i q1m q1n q10 q10desc q1p webaddrs }
                    item2a { q2a1 q2a2 q2a3 q2a4 q2a5 q2a6 q2a7 q2a8 q2a9 q2a10 q2a11 q2a12 q2a13 }
                    item2b { q2B1 q2B2 q2B3 }
                    item3a { orgFormNm orgFormOthNm }
                    item3b { q3B }
                    item3c { stateCD cntryNm }
                    item5a { ttlEmp }
                    item5b { q5B1 q5B2 q5B3 q5B4 q5B5 q5B6 }
                    item5c { q5C1 q5C2 q5C1Oth }
                    item5d {
                        q5D1A q5D1B q5D1C q5D1D q5D1E q5D1F q5D1G q5D1H q5D1I q5D1J q5D1K q5D1L q5D1M q5D2A q5D2B q5D2C q5D2D q5D2E q5D2F q5D2G q5D2H
                         q5D2I q5D2J q5D2K q5D2L q5D2M q5DA1 q5DA2 q5DA3 q5DB1 q5DB2 q5DB3 q5DC1 q5DC2 q5DC3 q5DD1 q5DD2 q5DD3 q5DE1 q5DE2 q5DE3 q5DF1
                         q5DF2 q5DF3 q5DG1 q5DG2 q5DG3 q5DH1 q5DH2 q5DH3 q5DI1 q5DI2 q5DI3 q5DJ1 q5DJ2 q5DJ3 q5DK1 q5DK2 q5DK3 q5DL1 q5DL2 q5DL3 q5DM1
                         q5DM2 q5DM3 q5DN1 q5DN2 q5DN3 q5DN3Oth
                    }
                    item5e { q5E1 q5E2 q5E3 q5E4 q5E5 q5E6 q5E7 q5E7Oth }
                    item5f { q5F1 q5F2A q5F2B q5F2C q5F2D q5F2E q5F2F q5F3 }
                    item5g { q5G1 q5G2 q5G3 q5G4 q5G5 q5G6 q5G7 q5G8 q5G9 q5G10 q5G11 q5G12 q5G120th }
                    item5h { q5H q5HMT500 }
                    item5i { q5I1 q5I2A q5I2B q5I2C }
                    item5j { q5J1 q5J2 }
                    item5k { q5K1 q5K2 q5K3 q5K4 }
                    item5l { q5L1A q5L1B q5L1C q5L1D q5L1E q5L2 q5L3 q5L4 }
                    item6a { q6A1 q6A2 q6A3 q6A4 q6A5 q6A6 q6A7 q6A8 q6A9 q6A10 q6A11 q6A12 q6A13 q6A14 q6A14Oth }
                    item6b { q6B1 q6B2 q6B3 }
                    item7a { q7A1 q7A2 q7A3 q7A4 q7A5 q7A6 q7A7 q7A8 q7A9 q7A10 q7A11 q7A12 q7A13 q7A14 q7A15 q7A16 }
                    item7b { q7B }
                    item8a { q8A1 q8A2 q8A3 }
                    item8b { q8B1 q8B2 q8B3 }
                    item8c { q8C1 q8C2 q8C3 q8C4 }
                    item8d { q8D }
                    item8e { q8E }
                    item8f { q8F }
                    item8g { q8G1 q8G2 }
                    item8h { q8H q8H1 q8H2 }
                    item8i { q8I }
                    item9a { q9A1A q9A1B q9A2A q9A2B }
                    item9b { q9B1A q9B1B q9B2A q9B2B }
                    item9c { q9C1 q9C2 q9C3 q9C4 }
                    item9d { q9D1 q9D2 }
                    item9e { q9E }
                    item9f { q9F }
                    item10a { q10A }
                    item11 { q11 }
                    item11a { q11A1 q11A2 }
                    item11b { q11B1 q11B2 }
                    item11c { q11C1 q11C2 q11C3 q11C4 q11C5 }
                    item11d { q11D1 q11D2 q11D3 q11D4 q11D5 }
                    item11e { q11E1 q11E2 q11E3 q11E4 }
                    item11f { q11F }
                    item11g { q11G }
                    item11h { q11H1A q11H1B q11H1C q11H2 }
                }
            }
            individuals {
                id
                info { firstNm midNm lastNm sufNm }
            }
        }
    }`;
    async function reload () {
        try {
            loading = true;
            error = null;
            firm = null;
            // const query = "query ($id: String!) { res: firmById (id: $id) { json } }";
            const variables = { id: data.slug };
            firm = await Server._gql (QUERY, variables);
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

<h1>
    {#if firm}
        {firm.info?.busNm} ({data.slug})
    {:else}
        Advisor: {data.slug}
    {/if}
</h1>

{#if loading}
    <Alert message="Loading..." />
{:else if error}
    <Alert message={error} type="error" />
{/if}

{#if firm}
    <a href="https://www.sec.gov/about/forms/formadv-part1a.pdf" class="Unadorned">
        <sl-icon name="icon-name-here">
            https://www.sec.gov/about/forms/formadv-part1a.pdf
        </sl-icon>
        &nbsp;
        Questionnaire
    </a>

    <sl-tab-group>
        <sl-tab slot="nav" panel="info">SEC Info</sl-tab>
        <sl-tab slot="nav" panel="address">Address</sl-tab>
        <sl-tab slot="nav" panel="registration">Registration</sl-tab>
        <sl-tab slot="nav" panel="filing">Filing</sl-tab>
        <sl-tab slot="nav" panel="filed">Notice Filed</sl-tab>
        <sl-tab slot="nav" panel="responses">Responses</sl-tab>
        <sl-tab slot="nav" panel="individuals">Individuals</sl-tab>
        <sl-tab slot="nav" panel="raw">Raw</sl-tab>

        <sl-tab-panel name="info">
            <SECInfo info={firm.info} />
        </sl-tab-panel>
        <sl-tab-panel name="address">
            <h2>Main Address</h2>
            <PropertyTable props={firm.mainAddr} />
            <h2>Mailing Address</h2>
            <PropertyTable props={firm.mailingAddr} />
        </sl-tab-panel>
        <sl-tab-panel name="registration">
            <PropertyTable props={firm.registration} />
        </sl-tab-panel>
        <sl-tab-panel name="filing">
            <PropertyTable props={firm.filing} />
        </sl-tab-panel>
        <sl-tab-panel name="filed">
            <DataTable
                headers={[ "State", "Status", "Date"]}
                data={firm.noticeFiled.states}
                xform={(e, i) => {
                    return [
                        e.regltrCd,
                        e.state,
                        e.date
                    ]
                }}
            />
        </sl-tab-panel>
        <sl-tab-panel name="responses">
            <Item1A value={firm.formInfo.part1A.item1} />
            <Item2A value={firm.formInfo.part1A.item2a} />
            <Item2B value={firm.formInfo.part1A.item2b} />

            <Item3A value={firm.formInfo.part1A.item3a} />
            <Item3B value={firm.formInfo.part1A.item3b} />
            <Item3C value={firm.formInfo.part1A.item3c} />
        </sl-tab-panel>
        <sl-tab-panel name="individuals">
            <DataTable
                headers={[ "Index", "ID", "Name" ]}
                data={firm.individuals}
                html={[ 1 ]}
                xform={(e, i) => {
                    const toName = (ei) => {
                        let s = ei.firstNm + ' ';
                        if (ei.midNm) {
                            s += ei.midNm + ' ';
                        }
                        s += ei.lastNm + ' ';
                        if (ei.sufNm) {
                            s += ei.sufNm;
                        }
                        return s;
                    };
                    return [
                        (i + 1) + '.',
                        "<a href=\"/individuals/" + e.id + "\">" + e.id + "</a>",
                        toName (e.info)
                    ];
                }}
            />
        </sl-tab-panel>
        <sl-tab-panel name="raw">
            <pre>{JSON.stringify (firm, null, 2)}</pre>
        </sl-tab-panel>
    </sl-tab-group>
{/if}

<sl-button variant="default" on:click={reload}>
    <sl-icon name="arrow-clockwise"></sl-icon>
    &nbsp;
    Reload
</sl-button>


<style>
</style>

<!-- EOF -->