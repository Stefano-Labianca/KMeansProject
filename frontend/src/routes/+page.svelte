<script lang="ts">
  import dayjs from "dayjs"
  import CrudEndPoint from "../api/crud"
  import { DAYJS_FORMAT, HISTORY_ENDPOINT } from "../const"
  import type { HistoryEntry } from "../types/kmeans"

  import history from "$stores/history"
  import { nanoid } from "nanoid"

  import Button from "$components/Button/Button.svelte"
  import type { ButtonComponent } from "$components/Button/button"
  import Form from "$components/Form/Form.svelte"
  import History from "$components/History/History.svelte"
  import Input from "$components/Input/Input.svelte"
  import Text from "$components/Text/Text.svelte"
  import InfoIcon from "$icons/InfoIcon.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import { dbRecord } from "$stores/dbRecord"

  import type { AlertComponent } from "$components/Alert/alert"
  import ErrorIcon from "$icons/ErrorIcon.svelte"
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

<Button text="Save" fill design="primary" onClick={save} />

<History />
<div class="mb-12" />

<Tables />
