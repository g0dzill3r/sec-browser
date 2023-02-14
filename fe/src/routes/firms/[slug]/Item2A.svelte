<script>
    import "./PropertyTable.css";
    import PartTable from "./PartTable.svelte";
    export let value;

    /**
     * Renderer for the item1 properties.
     *
     * @type {{q10desc: {}, q10: {}, q1p: {}, q1i: {}, q1f5: {}, q1m: {}, q1n: {}}}
     * @private
     */

    const format = {
        "q2a1": {
            "renderer": "YesNo"
        },
        "q2a2": {
            "renderer": "YesNo"
        },
        "q2a3": {
            "renderer": "YesNo"
        },
        "q2a4": {
            "renderer": "YesNo"
        },
        "q2a5": {
            "renderer": "YesNo"
        },
        "q2a6": {
            "renderer": "YesNo"
        },
        "q2a7": {
            "renderer": "YesNo"
        },
        "q2a8": {
            "renderer": "YesNo"
        },
        "q2a9": {
            "renderer": "YesNo"
        },
        "q2a10": {
            "renderer": "YesNo"
        },
        "q2a11": {
            "renderer": "YesNo"
        },
        "q2a12": {
            "renderer": "YesNo"
        },
        "q2a13": {
            "renderer": "YesNo"
        },
    };
</script>

<PartTable title="Item2A" {format} {value} />

<!-- EOF -->