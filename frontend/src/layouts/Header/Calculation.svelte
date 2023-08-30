<script lang="ts">
  import type { ButtonComponent } from "$components/Button/button"
  import Form from "$components/Form/Form.svelte"
  import Input from "$components/Input/Input.svelte"
  import Text from "$components/Text/Text.svelte"
  import PlusIcon from "$icons/PlusIcon.svelte"

  const button: ButtonComponent = {
    text: "Calculate",
    icon: PlusIcon,
    design: "primary",
  }
</script>

<div>
  <Text text="Your input" role="title" />
  <Form method="post" {button} let:errors>
    <Input name="table" label="Table name" placeholder="Ex. playtennis" type="text" error={errors.table?.[0]} />
    <div class="mt-4" />

    <Input name="cluster" label="Clusters amount" placeholder="Ex. 5" type="number" error={errors.cluster?.[0]} />
    <div class="mt-4" />
  </Form>
</div>
