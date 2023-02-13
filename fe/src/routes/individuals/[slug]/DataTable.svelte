<script>
    export let data = [];
    export let headers = [ "Missing" ];
    export let xform = (e) => e;
    export let html = [];
</script>

<table class="DataTable">
    <thead>
        <tr>
            {#each headers as header, index}
                <td>{header}</td>
            {/each}
        </tr>
    </thead>
    <tbody>
    {#each data as els, index}
        <tr>
            {#each xform (els, index) as el, col}
                <td>
                    {#if html.indexOf (col) !== -1}
                        {@html el}
                    {:else}
                        {el}
                    {/if}
                </td>
            {/each}
        </tr>
    {/each}
    {#if data.length === 0}
        <tr>
            <td colspan={headers.length}>None found.</td>
        </tr>
    {/if}
    </tbody>
</table>

<style>
    .DataTable {
        border-collapse: collapse;
        border: 2px solid lightgray;
    }
    .DataTable tr:not(:last-child) {
        border-bottom: 1px solid lightgray;
    }
    .DataTable thead tr {
        background-color: #f5f5f5;
        border-bottom: 1px solid lightgray;
    }
    .DataTable td, .DataTable th {
        text-align: left;
        padding: 8px;
    }
</style>