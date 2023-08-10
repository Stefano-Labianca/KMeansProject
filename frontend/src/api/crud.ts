import ApiClient from "./class/APIClient"

const read: Read = async <T>(url: string): Promise<T[]> => {
  return await new ApiClient<T>().read(url)
}

const readOne: ReadOne = async <T>(url: string, id: number): Promise<T> => {
  return await new ApiClient<T>().readOne(url, id)
}

const create: Create = async <T>(url: string, payload: T): Promise<T> => {
  return await new ApiClient<T>().create(url, payload)
}

const update: Update = async <T>(url: string, payload: T, id: number): Promise<T> => {
  return await new ApiClient<T>().update(url, payload, id)
}

const remove: Delete = async <T>(url: string, id: number): Promise<void> => {
  await new ApiClient<T>().delete(url, id)
}

const CrudEndPoint = {
  read,
  readOne,
  create,
  update,
  remove,
}

export default CrudEndPoint
