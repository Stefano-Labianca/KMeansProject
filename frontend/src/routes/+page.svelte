<script lang="ts">
  import History from "$components/History/History.svelte"
  import Calculation from "$layouts/Header/Calculation.svelte"
  import history from "$stores/history"
  import type { PageData } from "./$types"

  export let data: PageData

  $history = data.historyData
</script>

<div class="InputCalculation">
  <Calculation />
  <History />
</div>

<style lang="postcss">
  .InputCalculation {
    @apply flex;
    @apply flex-row;
    @apply gap-28;
  }
</style>
