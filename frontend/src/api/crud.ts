import ApiClient from "./class/APIClient"

const read: Read = async <T>(url: string): Promise<T[]> => {
  return await new ApiClient<T>().read(url)
}

const readOne: ReadOne = async <T>(url: string, id: string): Promise<T> => {
  return await new ApiClient<T>().readOne(url, id)
}

const create: Create = async <T>(url: string, payload: T): Promise<void> => {
  await new ApiClient<T>().create(url, payload)
}

const remove: Delete = async <T>(url: string, id: string): Promise<unknown> => {
  return new ApiClient<T>().delete(url, id)
}

const CrudEndPoint = {
  read,
  readOne,
  create,
  remove,
}

export default CrudEndPoint
