<script>
    export let data;
    export let headers;
    export let xform;
</script>

<table>
    <tr>
        <td>Headers</td>
        <td>{headers}</td>
    </tr>
    <tr>
        <td>Xform</td>
        <td>{xform}</td>
    </tr>
    <tr>
        <td>Data</td>
        <td>
            <pre>
                {JSON.stringify (data, null, 2)}
            </pre>
        </td>
    </tr>
</table>

<!-- EOF -->