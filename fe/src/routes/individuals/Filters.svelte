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
            firstName: firstName || null,
            lastName: lastName || null,
            id: id || null,
        };
        onUpdate && onUpdate (update);
        hide ();
        return;
    }

    export let filters;
    $: firstName = filters.firstName || "";
    $: lastName = filters.lastName || "";
    $: id = filters.id || "";

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
    <sl-input label="First Name" value={firstName} on:input={e => set("firstName", e)}></sl-input>

    <br/>
    <sl-input label="Last Name" value={lastName} on:input={e => set("lastName", e)}></sl-input>

    <br/>
    <sl-input label="ID" value={id} on:input={e => set("id", e)}></sl-input>

    <br/>
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