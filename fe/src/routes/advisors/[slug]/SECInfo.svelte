<script>
    import PropertyTable from "./PropertyTable.svelte";
    export let info = null;
</script>

<div class="SECInfo">
    <PropertyTable props={info} />
</div>

<style>
    .SecInfo {
        margin: 8px;
        border-left: 3px solid green;
        padding: 8px;
    }
</style>