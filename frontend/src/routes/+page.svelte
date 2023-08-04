<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import Text from "$components/Text/Text.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import CrudEndPoint from "../api/crud"
  import type { KMeans } from "../types/kmeans"

  async function caller() {
    console.log(await CrudEndPoint.read<KMeans>("/api/calculate"))
  }
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<Button text="AA" design="primary" fill onClick={caller} />
