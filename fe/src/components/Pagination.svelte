<script>
    import {clamp} from "$lib/Utils.js";

    export let skip = 0;
    export let total = -1;
    export let limit = 25;
    export let side = 2;

    export let controller = (e) => null;

    $: atStart = skip === 0;
    $: page = skip / limit;
    $: max = total - total % limit;
    $: maxPage = max / limit;
    $: atEnd = skip >= max;
    $: paging = determinePaging (page, side, maxPage)

    function determinePaging (page,side, maxPage) {
        let arr = [];
        for (let i = page - side; i <= page + side; i ++) {
            arr.push (i);
        }
        while (arr[0] < 0) {
            arr = arr.map (i => i + 1);
        }
        while (arr[arr.length - 1] > maxPage) {
            arr = arr.map (i => i - 1);
        }
        return [arr[0] !== 0, arr, arr[arr.length - 1] !== maxPage];
    }
</script>

<div class="Pagination">

    <sl-button variant="default" size="small" circle on:click={()=> {
    controller (0);
}}>
        <sl-icon name="chevron-bar-left" label="Settings"></sl-icon>
    </sl-button>

    <sl-button variant="default" size="small" circle on:click={() => {
    controller (clamp (skip - limit, 0, max))
}}>
        <sl-icon name="chevron-left" label="Settings"></sl-icon>
    </sl-button>

    {#if paging[0]}
        ● ● ●
    {/if}

    {#each paging[1] as page}
        <sl-button disabled={page === skip / limit} variant="default" size="small" circle on:click={() => {
            controller (page * limit)
        }}>
            {page + 1}
        </sl-button>
    {/each}

    {#if paging[paging.length - 1]}
        ● ● ●
    {/if}

    <sl-button variant="default" size="small" circle on:click={() => {
        controller (clamp (skip + limit, 0, max))
    }}>
        <sl-icon name="chevron-right" label="Settings"></sl-icon>
    </sl-button>

    <sl-button variant="default" size="small" circle on:click={() => {
        controller (max);
    }}>
        <sl-icon name="chevron-bar-right" label="Settings"></sl-icon>
    </sl-button>

</div>

<style>
    sl-button {
        margin: 4px;
    }
    .Pagination button {
        margin: 4px;
        text-align: center;
    }
</style>

<!-- EOF -->