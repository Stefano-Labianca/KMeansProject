<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import Text from "$components/Text/Text.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import KMeansEndPoint from "../api/kmeans"
  import type { KMeans } from "../types/kmeans"

  let kmeansRes: KMeans | undefined

  const notFound = async () => {
    await KMeansEndPoint.calculate<KMeans>("/api/calculate", "aaaa", 5)
  }

  /** Spegni Docker per farlo funzionare */
  const databaseError = async () => {
    await KMeansEndPoint.calculate<KMeans>("/api/calculate", "playtennis", 5)
  }

  const negativeK = async () => {
    await KMeansEndPoint.calculate<KMeans>("/api/calculate", "playtennis", -5)
  }

  const bigFatK = async () => {
    await KMeansEndPoint.calculate<KMeans>("/api/calculate", "playtennis", 10000)
  }
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="mt-20" />

<Button text="Table not found error" onClick={notFound} fill />
<div class="mt-4" />

<Button text="Database connection error" onClick={databaseError} fill />
<div class="mt-4" />

<Button text="k is too big" onClick={bigFatK} fill />
<div class="mt-4" />

<Button text="k is negative" onClick={negativeK} fill />
