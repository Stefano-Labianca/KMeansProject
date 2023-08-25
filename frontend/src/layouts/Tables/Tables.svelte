<script lang="ts">
  import EmptyState from "$components/EmptyState/EmptyState.svelte"
  import Table from "$components/Table/Table.svelte"
  import Text from "$components/Text/Text.svelte"
  import { DELAY, TRANSITION_Y_IN } from "$lib/consts"
  import { dbRecord } from "$stores/dbRecord"
  import { fly } from "svelte/transition"
  import AddGliph from "../../assets/gliph/AddGliph.svelte"
  import type { Cluster, Example, KMeans, Middle } from "../../types/kmeans"

  let middlesColumns: string[] = []
  let exampleColumns: string[] = []
  let middles: Middle[] = []

  let examples: Example[][] = []
  let avgDistances: number[] = []

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

{#if $dbRecord}
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
{:else}
  <EmptyState gliph={AddGliph} subtitle="Add your first value to get started" text="No data available" />
{/if}
