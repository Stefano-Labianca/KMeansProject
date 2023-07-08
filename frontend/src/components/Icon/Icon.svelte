<script lang="ts">
  import type { IconComponent } from "./icon"

  export let icon: IconComponent["icon"]
  export let design: IconComponent["design"] = "default"
</script>

{#if icon}
  <div class="Icon {design}">
    <svelte:component this={icon} />
  </div>
{/if}

<style lang="postcss">
  .Icon {
    @apply h-5;
    @apply w-5;
  }

  /* Design */
  .default {
    @apply text-inherit;
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
