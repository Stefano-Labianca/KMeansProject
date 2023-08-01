type Read = <T>(url: string) => Promise<T[]>

type ReadOne = <T>(url: string, id: number) => Promise<T>

type Create = <T>(url: string, payload: T) => Promise<T>

type Update = <T>(url: string, payload: T, id: number) => Promise<T>

type Delete = (url: string, id: number) => void
