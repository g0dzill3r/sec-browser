<script>
    import "./PropertyTable.css";
    import _ from 'lodash';
    import Value from "./Value.svelte";
    export let props = {};
    export let html = {};
</script>

<table class="PropertyTable">
    <tbody>
        <tr class="Header">
            <th>Property</th>
            <th>Value</th>
        </tr>
        {#each _.toPairs (props) as pair}
            <tr>
                <th>{pair[0]}</th>
                <td>
                    {#if html[pair[0]]}
                        {@html pair[1]}
                    {:else}
                        <Value value={pair[1]} />
                    {/if}
                </td>
            </tr>
        {/each}
    </tbody>
</table>

<style>

</style>

<!-- EOF -->