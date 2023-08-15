<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import Text from "$components/Text/Text.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import type { KMeans } from "../types/kmeans"

  let kmeansRes: KMeans | undefined

  const caller = async () => {}
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="mt-20" />

<Button text="Click Me" onClick={caller} fill />
<div class="mt-20" />

<Tables tables={kmeansRes} />
