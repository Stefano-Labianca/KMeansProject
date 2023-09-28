<script lang="ts">
  import type { AlertComponent } from "$components/Alert/alert"
  import ErrorIcon from "$icons/ErrorIcon.svelte"
  import { FORM_SCHEMA, type FormInfo } from "$lib/formValidator"
  import alert from "$stores/alert"
  import { dbRecord } from "$stores/dbRecord"
  import { createForm } from "felte"
  import KMeansEndPoint from "../../api/kmeans"
  import { API_CALCULATE } from "../../const"
  import type { KMeans } from "../../types/kmeans"
  import Button from "../Button/Button.svelte"
  import type { FormComponent } from "./form"

  import { validator } from "@felte/validator-zod"

  type $$Props = FormComponent

  export let button: FormComponent["button"]

  let loading = false

  const { form, errors } = createForm({
    extend: validator({ schema: FORM_SCHEMA }),
    async onSubmit(values, context) {
      if (!context.form) return

      loading = true

      const { database, table, cluster, username, password, port, server } = values as FormInfo
      let response: KMeans | undefined = undefined

      console.log(values)

      try {
        response = await KMeansEndPoint.calculate<KMeans>(API_CALCULATE, database, table, cluster)
      } catch (error) {
        alert.send({
          text: "Server connection error",
          icon: ErrorIcon,
          design: "error",
        } as AlertComponent)
      }

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
