<script lang="ts">
  import Table from "$components/Table/Table.svelte"
  import Text from "$components/Text/Text.svelte"
  import { beforeUpdate } from "svelte"
  import type { Cluster, Example, Middle } from "../../types/kmeans"
  import type { TablesComponent } from "./tables"

  export let tables: TablesComponent["tables"]

  let middlesColumns: string[]
  let exampleColumns: string[]
  let middles: Middle[]

  let examples: Example[][]
  let avgDistances: number[]
</script>
