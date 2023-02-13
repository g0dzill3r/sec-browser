<style>
    .Sidebar a {
        text-decoration: none;
        color: black;
    }
</style>


<script>
    const advisors = [
        ["Home", "/", "house"],
        ["System", "/system", "gear"],
        ["GraphQL", "/gql", "cloud-upload"],
        ["Advisors", "/advisors", "person-fill"],
        ["Advisors byState", "/advisors/byState", "globe-americas"],
        ["Individuals", "/individuals", "person"],
    ];
</script>

<div class="Sidebar">
        {#each advisors as [label, path, icon]}
            <sl-menu-item>
                <sl-icon slot="prefix" name={icon}></sl-icon>
                <a href={path}>
                    {label}
                </a>
            </sl-menu-item>
        {/each}
</div>



<!-- EOF -->