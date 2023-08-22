<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import type { EntryComponent } from "$components/Entry/entry"
  import History from "$components/History/History.svelte"
  import Text from "$components/Text/Text.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import dayjs from "dayjs"
  import CrudEndPoint from "../api/crud"
  import KMeansEndPoint from "../api/kmeans"
  import { API_CALCULATE, DAYJS_FORMAT, HISTORY_ENDPOINT } from "../const"
  import type { HistoryEntry, KMeans } from "../types/kmeans"

  import Tables from "$layouts/Tables/Tables.svelte"
  import { dbRecord } from "$stores/dbRecord"
  import history from "$stores/history"
  import { nanoid } from "nanoid"

  let historyData: EntryComponent[] = []
  let kMeans: KMeans | undefined

  const create = async () => {
    kMeans = await KMeansEndPoint.calculate<KMeans>(API_CALCULATE, "playtennis", 2)
  }

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
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="mt-20" />

<Button design="primary" fill text="Get KMeans" onClick={create} />
<div class="mb-4" />

<Button design="primary" fill text="Save KMeans" onClick={save} />
<div class="mb-4" />

<Button design="primary" text="Find All" onClick={findAll} />
<div class="mb-12" />

<History {historyData} />
<div class="mb-12" />

<Tables tables={$dbRecord} />
