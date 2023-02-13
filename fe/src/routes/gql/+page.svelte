<script>
    import Server from "../../lib/Server";
    import _ from "lodash";
    import {onMount} from "svelte";

    function getServerUrl () {
        // return window.location.protocol + window.location.hostname + ":8080/gql"
        return "http://localhost:8080/gql";
    }

    function reset () {
        query = "query {\n noOp\n}";
        updateVariables (JSON.stringify ({}, null, 2));
        console.log ("reset ()");
        return;
    }

    let query = null;
    let variables = null;
    let variablesErr = null;
    let loading = false;
    let serverUrl = getServerUrl ();
    let result = null;
    $: error = checkError (result);
    let banner = null;
    let tabGroup = null;

    function checkError (result) {
        try {
            return result.errors[0].message;
        }
        catch (e) {
            return null;
        }
    }

    function updateVariables (newValue) {
        variables = newValue;
        try {
            JSON.parse(newValue);
            variablesErr = "";
        }
        catch (e) {
            variablesErr = "Error";
        }
        return;
    }

    async function sendQuery () {
        if (variablesErr) {
            banner = "Variables not JSON.";
            return;
        }

        try {
            banner = "Loading ...";
            result = null;
            result = await Server.gql (query, variables);
            tabGroup.show ("results");
            banner = null;
        }
        catch (e) {
            banner = e.toString ();
        }
        finally {
            // ?
        }
        return;
    }

    function setVariables (newValue) {
        variables = newValue;
        try {
            const parsed = JSON.parse (newValue);
            if (! _.isObject (parsed)) {
                variablesErr = "Not an object.";
            } else if (_.isArray (parsed)) {
                variablesErr = "Array not valid variables.";
            } else {
                variablesErr = null;
            }
        }
        catch (e) {
            variablesErr = e.toString ();
        }
        return;
    }

    onMount (async () => {
        reset ();
    });
</script>

<h1>GraphQL Client</h1>

<form class="GraphQLForm">

    {#if banner}
        <div class="Loading">
            {banner}
        </div>
    {/if}

    {#if error}
        <sl-alert variant="danger" open>
            <sl-icon slot="icon" name="exclamation-octagon"></sl-icon>
            {error}
        </sl-alert>
        <br/>
    {/if}

    <sl-input placeholder="Type something" disabled value="{Server.getServerUrl ()}"></sl-input>

    <sl-tab-group bind:this={tabGroup}>
        <sl-tab slot="nav" panel="query">Query</sl-tab>
        <sl-tab slot="nav" panel="variables">
            <div>
                Variables
                {#if variablesErr}
                    <sl-badge variant="danger" pulse pill>
                        <sl-icon name="emoji-frown"></sl-icon>
                    </sl-badge>

                {/if}
            </div>
        </sl-tab>
        <sl-tab slot="nav" panel="results">Results</sl-tab>
        <sl-tab slot="nav" panel="raw">Raw</sl-tab>

        <sl-tab-panel name="query">
            <sl-textarea rows="12" on:input={e => query = e.target.value} value={query}></sl-textarea>
        </sl-tab-panel>
        <sl-tab-panel name="variables">
            <sl-textarea rows="12" on:input={e => setVariables (e.target.value)} value={variables} help-text={variablesErr}></sl-textarea>
        </sl-tab-panel>
        <sl-tab-panel name="results">
            <sl-textarea disabled rows="12" value={JSON.stringify (result && result.data, null, 2)}></sl-textarea>
        </sl-tab-panel>
        <sl-tab-panel name="raw">
            <sl-textarea disabled rows="12" value={JSON.stringify (result, null, 2)}></sl-textarea>
        </sl-tab-panel>
    </sl-tab-group>

    <sl-button variant="default" on:click={sendQuery}>
        <sl-icon name="cloud-upload"></sl-icon>
        &nbsp;
        Send
    </sl-button>
    <sl-button variant="default" on:click={reset}>
        <sl-icon name="x-circle"></sl-icon>
        &nbsp;
        Reset
    </sl-button>
</form>


<style>
    .GraphQLForm {
        padding: 8px;
    }
    .GraphQLForm label {
        display: block;
    }
    .GraphQLForm textarea {
        width: 90%;
    }
    .Loading {
        margin: 16px;
        padding: 12px;
        background-color: whitesmoke;
        border-radius: 6px;
        border: 1px solid #e5e5e5;
        border-left: 3px solid lightgreen;
    }
    .Result {
        margin: 16px;
        padding: 12px;
        background-color: whitesmoke;
        border-radius: 6px;
        border: 1px solid #e5e5e5;
    }
    .Error {
        border: 1px solid red;
        color: red;
    }
</style>