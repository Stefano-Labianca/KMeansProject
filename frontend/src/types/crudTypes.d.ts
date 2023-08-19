type Read = <T>(url: string) => Promise<T[]>

type ReadOne = <T>(url: string, id: string) => Promise<T>

type Create = <T>(url: string, payload: T) => Promise<T>

type Delete = (url: string, id: string) => void
