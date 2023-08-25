<script lang="ts">
  import History from "$components/History/History.svelte"
  import Calculation from "$layouts/Header/Calculation.svelte"
  import Header from "$layouts/Header/Header.svelte"
  import Tables from "$layouts/Tables/Tables.svelte"
  import history from "$stores/history"
  import type { PageData } from "./$types"

  export let data: PageData

  $history = data.historyData
</script>

<Header />

<div class="Main">
  <div class="UserInfo">
    <Calculation />

    <div class="mt-10" />

    <History />
  </div>

  <div class="TablesInfo HideScrollBar">
    <Tables />
  </div>
</div>

<style lang="postcss">
  .Main {
    @apply flex;
    @apply flex-row;
    @apply justify-around;
    @apply h-full;
  }

  .UserInfo {
    @apply flex;
    @apply flex-col;

    @apply mr-16;
    @apply md:w-1/2;
  }

  .TablesInfo {
    @apply flex;
    @apply flex-col;
    @apply w-full;
    @apply h-full;
    @apply gap-6;
  }
</style>
