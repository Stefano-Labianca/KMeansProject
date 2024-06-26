<script lang="ts">
  import EmptyState from "$components/EmptyState/EmptyState.svelte"
  import Table from "$components/Table/Table.svelte"
  import Text from "$components/Text/Text.svelte"
  import { DELAY, TRANSITION_Y_IN } from "$lib/animation"
  import { dbRecord } from "$stores/dbRecord"

  import { goto } from "$app/navigation"
  import Button from "$components/Button/Button.svelte"
  import { fly } from "svelte/transition"
  import AddGliph from "../../assets/gliph/AddGliph.svelte"
  import type { Cluster, Example, KMeans, Middle } from "../../types/kmeans"

  let middlesColumns: string[] = []
  let exampleColumns: string[] = []
  let middles: Middle[] = []

  let examples: Example[][] = []
  let avgDistances: number[] = []

  let loading = false

  const initExamples = (examples: Example[][]) => {
    for (let i = 0; i < examples.length; i++) {
      for (let j = 0; j < examples[i].length; j++) {
        let arr = [...examples[i][j].exampleData]
        arr.push(examples[i][j].distance)

        examples[i][j].exampleData = arr
      }
    }
  }

  const getClustersData = <T, K extends keyof T>(clusters: T[], key: K) => {
    return clusters.map(cluster => cluster[key])
  }

  const copy = (obj: KMeans) => {
    return JSON.parse(JSON.stringify(obj))
  }

  $: if ($dbRecord) {
    let tables: KMeans = copy($dbRecord)

    middlesColumns = tables.columnsName
    exampleColumns = [...middlesColumns, "distance"]

    let clusters: Cluster[] = tables.clusters

    middles = getClustersData(clusters, "middle")
    examples = getClustersData(clusters, "examples")
    avgDistances = getClustersData(clusters, "avgDistance")

    initExamples(examples)
  }
</script>

<Text text="Calculation" role="title" />

<div class="mt-4" />

{#if $dbRecord}
  <Text text="Iteration: {$dbRecord.iteration}" design="secondary" role="paragraph" />

  <div class="mt-4" />

  <div class="TableContent HideScrollBar">
    {#each middles as middle, i (i)}
      <div transition:fly|global={{ ...TRANSITION_Y_IN, delay: i * DELAY }}>
        <Text text="Cluster {i}" role="paragraph" />
        <Table head={middlesColumns} body={[middle]} />

        <div class="mt-4" />

        <Text text="Data for cluster {i}" role="paragraph" />
        <Table head={exampleColumns} body={examples[i]} />

        <div class="mt-4" />

        <Text text="Average distance: {avgDistances[i]}" />
        <div class="mt-12" />
      </div>
    {/each}
  </div>
{:else}
  <div class="EmptyState">
    <EmptyState
      gliph={AddGliph}
      subtitle="Calculate your first tables or check your history"
      text="No data available"
    />
  </div>
{/if}

<div class="mt-4" />

<Button
  text="Go back"
  {loading}
  design="primary"
  onClick={async () => {
    loading = true
    await goto("/", {
      keepFocus: true,
    })
  }}
/>

<style lang="postcss">
  .TableContent {
    @apply h-[35rem];
    @apply w-full;
    @apply overflow-y-auto;
  }

  .EmptyState {
    @apply flex;
    @apply items-center;
    @apply justify-center;
    @apply h-[35rem];
  }
</style>
