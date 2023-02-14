<script>
    let drawer = null;

    export function show () {
        drawer.show();
    }

    export function hide () {
        drawer.hide();
    }

    export let onUpdate;
    function update () {
        const update = {
            name: name || null,
            id: id || null,
            secId: secId|| null,
            state: state || null
        };
        onUpdate && onUpdate (update);
        hide ();
        return;
    }

    export let filters;
    $: name = filters.name || "";
    $: state = filters.state || "";
    $: id = filters.id || "";
    $: secId = filters.secId || "";

    const clear = () => {
        filters = {};
    }

    function set (which, e) {
        const copy = Object.assign ({}, filters);
        copy[which] = e.target.value;
        filters = copy;
        return;
    }
</script>

<sl-drawer bind:this={drawer} label="Filters..." class="drawer-overview">
    Use the following filters to limit the registered investment
    advisors returned.

    <br/>
    <br/>
    <sl-input label="Name" value={name} on:input={e => set("name", e)}></sl-input>

    <br/>
    <sl-input label="State" value={state} on:input={e => set("state", e)}></sl-input>

    <br/>
    <sl-input label="ID" value={id} on:input={e => set("id", e)}></sl-input>

    <br/>
    <sl-input label="SEC ID" value={secId} on:input={e => set("secId", e)}></sl-input>

    <sl-button slot="footer"  on:click={hide}>
        Cancel
    </sl-button>
    &nbsp;
    <sl-button slot="footer" variant="primary" on:click={clear}>
        Clear
    </sl-button>
    &nbsp;
    <sl-button slot="footer" variant="primary" on:click={update}>
        Update
    </sl-button>
</sl-drawer>


<style>

</style>