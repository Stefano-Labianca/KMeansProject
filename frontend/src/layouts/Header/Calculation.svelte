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

  <!-- TODO: Mettere come campi di input anche il nome del server, la porta, utente, password -->

  <Form method="post" {button} let:errors>
    <Input name="server" label="Server" placeholder="Ex. localhost" type="text" error={errors.server?.[0]} />
    <div class="mt-2" />

    <Input name="database" label="Database name" placeholder="Ex. MapDB" type="text" error={errors.database?.[0]} />
    <div class="mt-2" />

    <Input name="port" label="Port" placeholder="Ex. 3306" type="number" error={errors.port?.[0]} />
    <div class="mt-2" />

    <Input name="table" label="Table name" placeholder="Ex. playtennis" type="text" error={errors.table?.[0]} />
    <div class="mt-2" />

    <Input name="username" label="Username" placeholder="Ex. MapUser" type="text" error={errors.username?.[0]} />
    <div class="mt-2" />

    <Input name="password" label="Password" placeholder="Ex. map" type="text" error={errors.password?.[0]} />
    <div class="mt-2" />

    <Input name="cluster" label="Clusters amount" placeholder="Ex. 5" type="number" error={errors.cluster?.[0]} />

    <div class="mt-4" />
  </Form>
</div>
