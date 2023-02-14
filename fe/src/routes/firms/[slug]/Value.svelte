<script>
    import YesNo from "./YesNo.svelte";

    export let value;
    export let type;
</script>

{#if value === null}
    -
{:else if type === "YesNo"}
    <YesNo value={value} />
{:else if type === "OL"}
    <ol>
        {#each value as el}
            <li>{el}</li>
        {/each}
    </ol>
{:else}
    <span>{value}</span>
{/if}

<style>

</style>

