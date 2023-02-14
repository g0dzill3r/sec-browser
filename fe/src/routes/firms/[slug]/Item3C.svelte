<script>
    import "./PropertyTable.css";
    import PartTable from "./PartTable.svelte";
    export let value;

    const format = {
        "stateCD": {
            renderer: ""
        },
        "cntryNm": {
            renderer: ""
        },
    };
</script>

<PartTable title="Item3C" {format} {value} />

<!-- EOF -->