<script>
    import "./PropertyTable.css";
    import PartTable from "./PartTable.svelte";
    export let value;

    const format = {
        "q3B": {
            renderer: ""
        },
    };
</script>

<PartTable title="Item3B" {format} {value} />

<!-- EOF -->