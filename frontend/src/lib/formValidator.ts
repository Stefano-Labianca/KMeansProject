import { z } from "zod"
/**
 * Tipo usato per rappresentare le informazioni raccolte dall'utente tramite un form
 */
export type FormInfo = {
  /** Indirizzo del server */
  server: string

  /** Nome della tabella */
  table: string

  /** Nome del database */
  database: string

  /** Numero di porta */
  port: number

  /** Username dell'utente */
  username: string

  /** Password utente */
  password: string

  /** Numero di cluster */
  cluster: number
}

/** Schema di validazione del form dell'utente */
export const FORM_SCHEMA = z.object({
  database: z
    .string({
      required_error: "Database name is required",
      invalid_type_error: "Must be a string",
    })
    .nonempty("Database name can not be empty")
    .min(1, "Must be 1 or more characters long")
    .max(255, "Database name is to long"),

  server: z
    .string({
      required_error: "Server name is required",
      invalid_type_error: "Must be a string",
    })
    .nonempty("Server name can not be empty")
    .min(1, "Must be 1 or more characters long")
    .max(255, "Server name is to long"),

  table: z
    .string({
      required_error: "Table name is required",
      invalid_type_error: "Must be a string",
    })
    .nonempty("Table name can not be empty")
    .min(1, "Must be 1 or more characters long")
    .max(255, "Table name is to long"),

  port: z
    .number({
      required_error: "Port number is required",
      invalid_type_error: "Must be a number",
    })
    .positive("Must be greater than 0")
    .int("Must be an integer")
    .max(65535, "Reached maximum range for a port number")
    .safe("Integer value is too big"),

  username: z
    .string({
      required_error: "Username is required",
      invalid_type_error: "Must be a string",
    })
    .nonempty("Username can not be empty")
    .min(1, "Must be 1 or more characters long")
    .max(255, "Username is to long"),

  password: z
    .string({
      required_error: "Table name is required",
      invalid_type_error: "Must be a string",
    })
    .nonempty("Password can not be empty")
    .min(1, "Password must be 1 or more characters long")
    .max(255, "Password is to long"),

  cluster: z
    .number({
      required_error: "Cluster number is required",
      invalid_type_error: "Must be a number",
    })
    .positive("Must be greater than 0")
    .int("Must be an integer")
    .max(100000, "Reached maximum range for a port number")
    .safe("Integer value is too big"),
})
