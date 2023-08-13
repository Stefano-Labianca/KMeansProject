<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import Table from "$components/Table/Table.svelte"
  import Text from "$components/Text/Text.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import KMeansEndPoint from "../api/kmeans"
  import type { Cluster, Example, KMeans, Middle } from "../types/kmeans"

  let kmeansRes: KMeans | undefined

  let middlesColumns: string[]
  let exampleColumns: string[]
  let middles: Middle[]

  let examples: Example[][]
  let avgDistances: number[]

  const caller = async () => {
    kmeansRes = await KMeansEndPoint.calculate<KMeans>("/api/calculate", "playtennis", 4)
    console.log(kmeansRes)

    middlesColumns = kmeansRes.columnsName
    exampleColumns = [...middlesColumns, "distance"]
    let clusters: Cluster[] = kmeansRes.clusters

    middles = clusters.map(cluster => cluster.middle)

    examples = clusters.map(cluster => cluster.examples)
    avgDistances = clusters.map(cluster => cluster.avgDistance)

    for (let i = 0; i < examples.length; i++) {
      for (let j = 0; j < examples[i].length; j++) {
        let arr = [...examples[i][j].exampleData]
        arr.push(examples[i][j].distance)

        examples[i][j].exampleData = arr
      }
    }
  }
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="mt-20" />

<Button text="Click Me" onClick={caller} fill />
<div class="mt-20" />

<div class="px-36">
  {#if kmeansRes}
    {#each middles as middle, i}
      <Text text="Cluster {i}" role="paragraph" />
      <Table head={middlesColumns} body={[middle]} />

      <div class="mt-4" />

      <Text text="Dati per il cluster {i}" role="paragraph" />
      <Table head={exampleColumns} body={examples[i]} />

      <div class="mt-4" />
      <Text text="Distanza media: {avgDistances[i]}" />

      <div class="mt-12" />
    {/each}
  {/if}
</div>
