import { findAll } from "../api/init"
import type { PageLoad } from "./$types"

export const load: PageLoad = async () => {
  return {
    historyData: await findAll(),
  }
}
