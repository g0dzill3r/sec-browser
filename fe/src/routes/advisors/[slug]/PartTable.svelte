<script>
    import _ from 'lodash';
    import Value from "./Value.svelte";

    export let title = "Untitled";
    export let format = {};
    export let value = null;
</script>

<h2>{title}</h2>

{#if value}
    <table class="PropertyTable">
        <tbody>
        {#each _.keys (format) as key}
            <tr>
                <th>{key}</th>
                <td>
                    <Value type={format[key].renderer} value={value[key]} />
                </td>
            </tr>
        {/each}
        </tbody>
    </table>
{:else}
    Value is {value}.
{/if}


<style>

</style>