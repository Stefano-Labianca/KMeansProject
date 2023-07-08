<script lang="ts">
  import Alert from "$components/Alert/Alert.svelte"
  import {
    TRANSITION_BASE,
    TRANSITION_Y_IN,
    TRANSITION_Y_OUT,
  } from "$lib/consts"

  import alerts from "$stores/alert"
  import { flip } from "svelte/animate"
  import { fly } from "svelte/transition"
</script>

<div class="Alerts">
  {#each $alerts as alert (alert)}
    <div
      animate:flip={TRANSITION_BASE}
      in:fly={TRANSITION_Y_IN}
      out:fly={TRANSITION_Y_OUT}
    >
      <Alert {...alert} />
    </div>
  {/each}
</div>

<style lang="postcss">
  .Alerts {
    @apply fixed;
    @apply top-0;
    @apply right-0;
    @apply w-full;
    @apply p-small;
    @apply grid;
    @apply gap-small;
    @apply z-50;

    @apply md:max-w-lg;
  }
</style>
