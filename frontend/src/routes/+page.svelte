<script lang="ts">
  import dayjs from "dayjs"
  import CrudEndPoint from "../api/crud"
  import { DAYJS_FORMAT, HISTORY_ENDPOINT } from "../const"
  import type { HistoryEntry } from "../types/kmeans"

  import history from "$stores/history"
  import { nanoid } from "nanoid"

  import { dbRecord } from "$stores/dbRecord"

  import type { AlertComponent } from "$components/Alert/alert"
  import History from "$components/History/History.svelte"
  import ErrorIcon from "$icons/ErrorIcon.svelte"
  import Calculation from "$layouts/Header/Calculation.svelte"
  import Header from "$layouts/Header/Header.svelte"
  import Tables from "$layouts/Tables/Tables.svelte"
  import alerts from "$stores/alert"

  const save = async () => {
    if (!$dbRecord) {
      alerts.send({
        text: "Empty calculation",
        design: "error",
        icon: ErrorIcon,
      } as AlertComponent)

      return
    }

    let payload: HistoryEntry = {
      ...$dbRecord,
      date: dayjs().format(DAYJS_FORMAT),
      title: nanoid(),
    } as HistoryEntry

    console.log("Salvataggio: ", $dbRecord)

    let response = await CrudEndPoint.create<HistoryEntry>(HISTORY_ENDPOINT.POST, payload)
    await findAll()
  }

  const findAll = async () => {
    let res = await CrudEndPoint.read<HistoryEntry>(HISTORY_ENDPOINT.GET)
    let historyData = res.map(e => {
      const { date, title, id } = e
      return { date, title, id }
    })

    $history = historyData
  }
</script>

<Header />

<Calculation />
<History />

<Tables />

<!-- <Button text="Save" fill design="primary" onClick={save} />

<div class="mb-12" />

 -->
