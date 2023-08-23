<script lang="ts">
  import type { EntryComponent } from "$components/Entry/entry"
  import dayjs from "dayjs"
  import CrudEndPoint from "../api/crud"
  import { DAYJS_FORMAT, HISTORY_ENDPOINT } from "../const"
  import type { HistoryEntry, KMeans } from "../types/kmeans"

  import history from "$stores/history"
  import { nanoid } from "nanoid"

  import type { ButtonComponent } from "$components/Button/button"
  import Form from "$components/Form/Form.svelte"
  import Input from "$components/Input/Input.svelte"
  import Text from "$components/Text/Text.svelte"
  import InfoIcon from "$icons/InfoIcon.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import Tables from "$layouts/Tables/Tables.svelte"
  import { dbRecord } from "$stores/dbRecord"

  let historyData: EntryComponent[] = []
  let kMeans: KMeans | undefined

  const save = async () => {
    if (kMeans) {
      let payload = {
        ...kMeans,
        date: dayjs().format(DAYJS_FORMAT),
        title: nanoid(),
      } as HistoryEntry

      let response = await CrudEndPoint.create<HistoryEntry>(HISTORY_ENDPOINT.POST, payload)
      await findAll()
    }
  }

  const findAll = async () => {
    let res = await CrudEndPoint.read<HistoryEntry>(HISTORY_ENDPOINT.GET)
    historyData = res.map(e => {
      const { date, title, id } = e
      return { date, title, id }
    })

    $history = historyData
  }

  const button: ButtonComponent = {
    text: "Click me",
    icon: InfoIcon,
    design: "primary",
  }
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="mt-20" />

<Form method="post" {button} let:errors>
  <Input name="table" label="Table name" placeholder="Ex. playtennis" type="text" error={errors.table?.[0]} />
  <div class="mt-4" />

  <Input name="cluster" label="Clusters amount" placeholder="Ex. 5" type="number" error={errors.cluster?.[0]} />
  <div class="mt-4" />
</Form>

<!-- <History {historyData} />
<div class="mb-12" /> -->

<Tables tables={$dbRecord} />
