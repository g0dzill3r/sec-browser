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
        "q1f5": {
            renderer: ""
        },
        "q1i": {
            renderer: "YesNo"
        },
        "q1m": {
            renderer: "YesNo"
        },
        "q1n": {
            renderer: "YesNo"
        },
        "q10": {
            renderer: "YesNo"
        },
        "q10desc": {
            renderer: "YesNo"
        },
        "q1p": {
            renderer: ""
        },
        "webaddrs": {
            renderer: "OL"
        }
    };
</script>

<PartTable title="Item1" {format} {value} />

<!-- EOF -->