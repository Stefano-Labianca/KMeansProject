<script>
  import Gliph from "$components/Gliph/Gliph.svelte"
  import Text from "$components/Text/Text.svelte"

  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import AddGliph from "../assets/gliph/AddGliph.svelte"
  import EmptyGliph from "../assets/gliph/EmptyGliph.svelte"
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<Gliph gliph={AddGliph} />
<Gliph gliph={EmptyGliph} />
