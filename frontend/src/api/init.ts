import type { EntryComponent } from "$components/Entry/entry"
import dayjs from "dayjs"
import { nanoid } from "nanoid"
import { DAYJS_FORMAT, HISTORY_ENDPOINT } from "../const"
import type { HistoryEntry, KMeans } from "../types/kmeans"
import CrudEndPoint from "./crud"

export const findAll = async () => {
  try {
    const res: HistoryEntry[] = await CrudEndPoint.read<HistoryEntry>(HISTORY_ENDPOINT.GET)
    const historyData: EntryComponent[] = res.map(e => {
      const { date, title, id } = e
      return { date, title, id }
    })

    return historyData
  } catch (error) {
    return Promise.reject(error)
  }
}

export const save = async (record: KMeans) => {
  if (!record) return undefined

  const payload: HistoryEntry = {
    ...record,
    date: dayjs().format(DAYJS_FORMAT),
    title: nanoid(10),
  } as HistoryEntry

  try {
    // TODO: Spezza il cristo di playload in caso di cluster enormi

    await CrudEndPoint.create<HistoryEntry>(HISTORY_ENDPOINT.POST, payload)
  } catch (error) {
    return Promise.reject(error)
  }
}
