<script lang="ts">
  import type { ButtonComponent } from "$components/Button/button"
  import Form from "$components/Form/Form.svelte"
  import Input from "$components/Input/Input.svelte"
  import InfoIcon from "$icons/InfoIcon.svelte"

  const button: ButtonComponent = {
    text: "Click me",
    icon: InfoIcon,
    design: "primary",
  }
</script>

<Form method="post" {button} let:errors>
  <Input name="table" label="Table name" placeholder="Ex. playtennis" type="text" error={errors.table?.[0]} />
  <div class="mt-4" />

  <Input name="cluster" label="Clusters amount" placeholder="Ex. 5" type="number" error={errors.cluster?.[0]} />
  <div class="mt-4" />
</Form>
