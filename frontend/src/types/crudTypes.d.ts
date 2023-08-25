type Read = <T>(url: string) => Promise<T[]>

type ReadOne = <T>(url: string, id: string) => Promise<T>

type Create = <T>(url: string, payload: T) => Promise<void>

type Delete = (url: string, id: string) => Promise<void>
