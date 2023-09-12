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
  <div class="CalculationInput">
    <Calculation />

    <div class="mt-10" />
    <History />
  </div>

  <div class="CalculationResult">
    <Tables />
  </div>
</div>

<style lang="postcss">
  .Main {
    @apply flex;
    @apply flex-row;
    @apply w-full;
    @apply justify-around;
  }

  .CalculationInput {
    @apply flex-row;
    @apply w-1/2;
    @apply mr-16;
  }

  .CalculationResult {
    @apply flex;
    @apply flex-col;
    @apply w-full;
    @apply gap-5;
  }
</style>
