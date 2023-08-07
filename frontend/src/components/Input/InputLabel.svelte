<script lang="ts">
  import type { IInput } from "./input"

  export let label: IInput["label"]
  export let id: IInput["id"]
  export let optional: IInput["optional"]
</script>

{#if label}
  <label for={id} class="InputLabel">
    {label}
    {#if !optional}
      <span>*</span>
    {/if}
  </label>
{/if}

<style lang="postcss">
  .InputLabel {
    @apply absolute;
    @apply text-subtitle;
    @apply text-sm;
    @apply top-0;
    @apply left-0;
    @apply p-3;
    @apply pointer-events-none;
  }

  span {
    @apply text-error;
  }
</style>
