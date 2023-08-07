<script lang="ts">
  import Icon from "../Icon/Icon.svelte"
  import type { IInput } from "./input"

  export let icon: IInput["icon"]
</script>

{#if icon}
  <div class="InputIcon">
    <Icon {icon} design="secondary" />
  </div>
{/if}

<style lang="postcss">
  .InputIcon {
    @apply absolute;
    @apply text-gray;
    @apply text-sm;
    @apply top-0;
    @apply right-0;
    @apply p-3;
    @apply flex;
    @apply items-center;
    @apply h-full;
    @apply pointer-events-none;
  }
</style>
