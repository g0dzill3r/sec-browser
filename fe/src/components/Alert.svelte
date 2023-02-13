<script>
    export let message = "No message.";
    export let type = "Info"
    $: classes = "Alert Alert-" + type;
</script>

<div class={classes}>
    {message}
</div>

<style>
    .Alert {
        padding: 8px;
        border: 1px solid lightgray;
        background-color: #f5f5f5;
        border-radius: 6px;
        margin: 8px;
        width: 95%;
        display: block;
    }
    .Alert-info {
        border-left: 3px solid green;
    }
    .Alert-error {
        border-left: 3px solid red;
    }
</style>