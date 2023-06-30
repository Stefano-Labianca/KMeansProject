<script lang="ts">
  import type { IconComponent } from "./icon"

  export let icon: IconComponent["icon"]
  export let design: IconComponent["design"]
</script>

<div class="Icon {design}">
  <svelte:component this={icon} />
</div>

<style lang="postcss">
  .Icon {
    @apply h-5;
    @apply w-5;
  }

  /* Design */
  .default {
    @apply text-title;
  }

  .primary {
    @apply text-primary;
  }

  .secondary {
    @apply text-subtitle;
  }

  .error {
    @apply text-error;
  }
</style>
