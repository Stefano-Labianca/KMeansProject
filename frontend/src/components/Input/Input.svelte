<script lang="ts">
  import { nanoid } from "nanoid"
  import InputField from "./InputField.svelte"
  import InputIcon from "./InputIcon.svelte"
  import InputLabel from "./InputLabel.svelte"
  import type { IInput } from "./input"

  type $$Props = IInput

  export let label: IInput["label"] = ""
  export let icon: IInput["icon"] = undefined
  export let placeholder: IInput["placeholder"] = ""
  export let id: IInput["id"] = nanoid()
  export let optional: IInput["optional"] = false
  export let value: IInput["value"] = ""
</script>

<div class="Input">
  <InputLabel {id} {optional} {label} />
  <InputField {id} {optional} {placeholder} {icon} {label} {...$$restProps} bind:value />
  <InputIcon {icon} />
</div>

<style lang="postcss">
  .Input {
    @apply relative;
    @apply bg-overlay;
    @apply rounded-default;
    @apply w-full;
  }
</style>
