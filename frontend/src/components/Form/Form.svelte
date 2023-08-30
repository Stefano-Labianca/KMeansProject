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

    async onSubmit(values: { table: string; cluster: number }, context) {
      if (!context.form) return

      loading = true

      const { table, cluster } = values
      const response = await KMeansEndPoint.calculate<KMeans>(API_CALCULATE, table, cluster)

      $dbRecord = response
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
