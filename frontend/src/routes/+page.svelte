<script>
  import EmptyState from "$components/EmptyState/EmptyState.svelte"
  import Text from "$components/Text/Text.svelte"

  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import AddGliph from "../assets/gliph/AddGliph.svelte"
  import EmptyGliph from "../assets/gliph/EmptyGliph.svelte"
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="mt-20">
  <EmptyState
    gliph={EmptyGliph}
    text="Empty history"
    subtitle="Nothing to see here"
  />
</div>

<div class="mt-20">
  <EmptyState
    gliph={AddGliph}
    text="No data available"
    subtitle="Add your first k value to get started"
  />
</div>
