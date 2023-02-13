<script>
    import _ from 'lodash';
    export let addr = null;
</script>

{#if addr}

<div class="Address">
    {addr.strt1} <br/>
    {#if _.isNull (addr.strt1)}
        {addr.strt2} <br/>
    {/if}
    {addr.city}, {addr.state} {addr.postlCd}
</div>

{/if}

<!-- EOF -->