<script lang="ts">
  import { dbRecord } from "$stores/dbRecord"
  import { createForm } from "felte"
  import { minLength, minValue, number, safeParse, string } from "valibot"
  import KMeansEndPoint from "../../api/kmeans"
  import { API_CALCULATE } from "../../const"
  import type { KMeans } from "../../types/kmeans"
  import Button from "../Button/Button.svelte"
  import type { FormComponent } from "./form"

  type $$Props = FormComponent

  export let button: FormComponent["button"]

  let loading = false

  type FormInfo = { database: string; table: string; cluster: number }
  type FormError = { database: string; table: string; cluster: string }

  const { form, errors } = createForm({
    validate: (values: FormInfo) => {
      let err: FormError = {
        table: "",
        database: "",
        cluster: "",
      }

      const databaseSchema = string("Must be a string", [minLength(1, "Empty database name")])
      const tableSchema = string("Must be a string", [minLength(1, "Empty table name")])
      const clusterSchema = number("Must be a number", [minValue(1, "Must be greater than zero")])

      let databaseError = safeParse(databaseSchema, values.database)
      let tableError = safeParse(tableSchema, values.table)
      let clusterError = safeParse(clusterSchema, values.cluster)

      if (!databaseError.success) {
        err.database = databaseError.error.message
      }

      if (!tableError.success) {
        err.table = tableError.error.message
      }

      if (!clusterError.success) {
        err.cluster = clusterError.error.message
      }

      return err
    },

    async onSubmit(values: FormInfo, context) {
      if (!context.form) return

      loading = true

      const { database, table, cluster } = values
      const response = await KMeansEndPoint.calculate<KMeans>(API_CALCULATE, database, table, cluster)

      if (response) $dbRecord = response

      loading = false
    },
  })
</script>

<form use:form {...$$restProps} class="Form">
  <slot errors={$errors} />

  <div class="ButtonContent">
    <Button {loading} {...button} fill inverted type="submit" />
  </div>
</form>

<style lang="postcss">
  .Form {
    @apply flex;
    @apply flex-col;
    @apply w-full;
  }

  .ButtonContent {
    @apply w-full;
  }
</style>
