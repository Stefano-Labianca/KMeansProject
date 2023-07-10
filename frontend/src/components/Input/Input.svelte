<script lang="ts">
  import type { InputComponent } from "./input"

  export let type: InputComponent["type"]
  export let placeholder: InputComponent["placeholder"]
</script>

<input
  class="Input animated"
  name="kValue"
  {placeholder}
  autocomplete="off"
  required
  {type}
/>

<style lang="postcss">
  .Input {
    @apply bg-transparent;
    @apply border-subtitle;
    @apply border-default;
    @apply rounded-default;
    @apply pl-3;
    @apply w-full;
    @apply h-full;
    @apply text-medium;

    @apply focus:outline-none;
    @apply focus:border-primary;
  }

  .animated {
    @apply duration-default;
    @apply ease-default;
  }
</style>
