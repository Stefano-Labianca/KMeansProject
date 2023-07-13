<script lang="ts">
  import Gliph from "$components/Gliph/Gliph.svelte"
  import Text from "$components/Text/Text.svelte"
  import type { EmptyStateComponent } from "./emptyState"

  export let gliph: EmptyStateComponent["gliph"]
  export let text: EmptyStateComponent["text"]
  export let subtitle: EmptyStateComponent["subtitle"]
</script>

<div class="EmptyState">
  <Gliph {gliph} />
  <Text role="title" {text} />
  <Text role="paragraph" design="secondary" text={subtitle} />
</div>

<style lang="postcss">
  .EmptyState {
    @apply flex;
    @apply items-center;
    @apply justify-center;
    @apply flex-col;
  }
</style>
