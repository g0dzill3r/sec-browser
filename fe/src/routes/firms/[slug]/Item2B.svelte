<script>
    import "./PropertyTable.css";
    import PartTable from "./PartTable.svelte";
    export let value;

    const format = {
        "q2B1": {
            renderer: "YesNo"
        },
        "q2B2": {
            renderer: "YesNo"
        },
        "q2B3": {
            renderer: "YesNo"
        },
    };
</script>

<PartTable title="Item2B" {format} {value} />

<!-- EOF -->