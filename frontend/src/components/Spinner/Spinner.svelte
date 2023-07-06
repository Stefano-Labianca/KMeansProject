<script lang="ts">
  import { get } from "svelte/store"
  import { isButtonFilled, spinnerModifier } from "../../stores/spinner"

  $: designClass = get(spinnerModifier)
  $: filledButton = get(isButtonFilled)
</script>

<div class="Spinner {designClass}" class:fill={filledButton} />

<style lang="postcss">
  .Spinner {
    @apply w-5;
    @apply h-5;
    @apply border-2;
    @apply rounded-full;
    @apply border-t-transparent;
    @apply border-r-transparent;
    @apply animate-spin;
  }

  /* Design spinner */
  .default {
    @apply border-b-title;
    @apply border-l-title;
  }

  .primary {
    @apply border-b-primary;
    @apply border-l-primary;
  }

  .secondary {
    @apply border-b-subtitle;
    @apply border-l-subtitle;
  }

  .error {
    @apply border-b-error;
    @apply border-l-error;
  }

  /* Filled */
  .fill {
    @apply border-b-background;
    @apply border-l-background;

    &.error {
      @apply border-b-title;
      @apply border-l-title;
    }
  }
</style>
