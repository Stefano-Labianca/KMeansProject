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

  const { form, errors } = createForm({
    validate: values => {
      let err: { table?: string; cluster?: string } = { table: undefined, cluster: undefined }

      const tableSchema = string("Must be a string", [minLength(1, "Empty table name")])
      const clusterSchema = number("Must be a number", [minValue(1, "Must be greater than zero")])

      let tableError = safeParse(tableSchema, values.table)
      let clusterError = safeParse(clusterSchema, values.cluster)

      if (!tableError.success) {
        err.table = tableError.error.message
      }

      if (!clusterError.success) {
        err.cluster = clusterError.error.message
      }

      return err
    },

    async onSubmit(values, context) {
      if (!context.form) return

      const { table, cluster } = values
      const response = await KMeansEndPoint.calculate<KMeans>(API_CALCULATE, table, cluster)

      $dbRecord = response
    },
  })
</script>

<form use:form {...$$restProps} class="Form">
  <slot errors={$errors} />

  <Button {...button} fill type="submit" />
</form>

<style lang="postcss">
  .Form {
    @apply flex;
    @apply flex-col;
    @apply w-full;
  }
</style>
