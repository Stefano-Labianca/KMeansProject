<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import type { EntryComponent } from "$components/Entry/entry"
  import History from "$components/History/History.svelte"
  import Text from "$components/Text/Text.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import dayjs from "dayjs"
  import CrudEndPoint from "../api/crud"
  import KMeansEndPoint from "../api/kmeans"
  import { DAYJS_FORMAT } from "../const"
  import type { HistoryEntry, KMeans } from "../types/kmeans"

  let history = [] as EntryComponent[]
  let kMeans: KMeans | undefined

  const create = async () => {
    kMeans = await KMeansEndPoint.calculate<KMeans>("/api/calculate", "playtennis", 2)
    console.log(kMeans)
  }

  const save = async () => {
    if (kMeans) {
      let payload = {
        ...kMeans,
        date: dayjs().format(DAYJS_FORMAT),
        name: "Dio merda",
      }
      let response = await CrudEndPoint.create<KMeans>("/history/add", payload)
      console.log("response: ", response)
    }
  }

  const findAll = async () => {
    let res = await CrudEndPoint.read<HistoryEntry>("/history/get")
    console.log(res)
  }
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="mt-20" />

<Button design="primary" fill text="Get KMeans" onClick={create} />
<div class="mt-20" />

<Button design="primary" fill text="Save KMeans" onClick={save} />
<div class="mt-20" />

<Button design="primary" fill text="Find All" onClick={findAll} />
<div class="mt-20" />

<History historyData={history} />
