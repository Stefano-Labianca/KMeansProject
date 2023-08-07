<script lang="ts">
  import type { IInput } from "./input"

  export let label: IInput["label"]
  export let icon: IInput["icon"]
  export let placeholder: IInput["placeholder"]
  export let id: IInput["id"]
  export let optional: IInput["optional"]
  export let value: IInput["value"]
</script>

<input {id} class:label class:icon class="InputField" {placeholder} {...$$restProps} required={!optional} bind:value />

<style lang="postcss">
  .InputField {
    @apply bg-transparent;
    @apply transition;
    @apply p-3;
    @apply w-full;
    @apply rounded-default;
    @apply outline-none;

    @apply focus:ring;
    @apply focus:ring-primary;

    @apply placeholder:text-subtitle/strong;
    @apply placeholder:font-bold;
  }

  .label {
    @apply pt-8;
  }
  .icon {
    @apply pr-12;
  }
</style>
