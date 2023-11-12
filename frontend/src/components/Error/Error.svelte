<script lang="ts">
  import { TRANSITION_BASE } from "$lib/animation"
  import { slide } from "svelte/transition"
  import Text from "../Text/Text.svelte"
  import type { ErrorComponent } from "./error"

  export let error: ErrorComponent["error"] = ""
</script>

{#if error}
  <div class="Error" transition:slide={TRANSITION_BASE}>
    <Text text={error} design="error" role="subtitle" />
  </div>
{/if}

<style lang="postcss">
  .Error {
    @apply mt-[0.5rem];
  }
</style>
