<script>
    export let value;
</script>

{#if value}
    <span class="Check">
        <i class="fa-solid fa-check"></i>
    </span>

{:else}
    <span class="Nope">
        <i class="fa-solid fa-xmark"></i>
    </span>
{/if}

<style>
    .Check {
        font-weight: bold;
        color: green;
    }
    .Nope {
        font-weight: bold;
        color: red;
    }
</style>