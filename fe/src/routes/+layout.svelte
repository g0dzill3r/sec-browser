<script>
    import Header from "../global/Header.svelte";
    import Footer from "../global/Footer.svelte";
    import Sidebar from "../global/Sidebar.svelte";
</script>

<style>
    body {
        border: 0;
        margin: 0;
    }
    .App {
        min-height: 1024px;
        font-family: 'Roboto', sans-serif;
    }
    .AppWrapper {
        display: flex;
    }
    .AppContent {
        padding: 8px;
        flex-grow: 1;
    }
    .Sidebar {
        background-color: #fefefe;
        border-right: 1px solid gray;
        width: 200px;
        min-height: 800px;
    }
</style>

<div class="App">
    <Header />
    <div class="AppWrapper">
        <div class="Sidebar">
            <Sidebar />
        </div>
        <div class="AppContent">
            <slot></slot>
        </div>
    </div>
    <Footer />
</div>