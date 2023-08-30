<script lang="ts">
  export const loading: boolean = false
</script>

<div class="Spinner" class:fill={loading} />

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

  /* Filled */
  .fill {
    @apply border-b-background;
    @apply border-l-background;
  }
</style>
