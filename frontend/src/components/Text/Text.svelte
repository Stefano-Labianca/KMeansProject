<script lang="ts">
  import type { TextComponent } from "./text"

  export let text: TextComponent["text"]
  export let role: TextComponent["role"]
  export let design: TextComponent["design"] = "default"
</script>

<div class="{role} {design}">
  {text}
</div>

<style lang="postcss">
  /* Roles */
  .title {
    @apply text-large;
    @apply font-bold;
  }

  .paragraph {
    @apply text-medium;
    @apply font-normal;
  }

  .subtitle {
    @apply font-light;
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
    @apply font-medium;
  }
</style>
