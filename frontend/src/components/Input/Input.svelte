<script lang="ts">
  import Error from "$components/Error/Error.svelte"
  import { nanoid } from "nanoid"
  import InputField from "./InputField.svelte"
  import InputIcon from "./InputIcon.svelte"
  import InputLabel from "./InputLabel.svelte"
  import type { InputComponent } from "./input"

  type $$Props = InputComponent

  export let label: InputComponent["label"] = ""
  export let icon: InputComponent["icon"] = undefined
  export let placeholder: InputComponent["placeholder"] = ""
  export let id: InputComponent["id"] = nanoid()
  export let optional: InputComponent["optional"] = false
  export let value: InputComponent["value"] = ""
  export let error: InputComponent["error"] = ""
</script>

<div class="Input">
  <InputLabel {id} {optional} {label} />
  <InputField {id} {optional} {placeholder} {icon} {label} {...$$restProps} bind:value />
  <InputIcon {icon} />
</div>

<Error {error} />

<style lang="postcss">
  .Input {
    @apply relative;
    @apply bg-overlay;
    @apply rounded-default;
    @apply w-full;
  }
</style>
