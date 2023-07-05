<script lang="ts">
  import Icon from "../Icon/Icon.svelte"
  import Text from "../Text/Text.svelte"
  import Loading from "./Loading.svelte"

  import type { ButtonComponent } from "./button"

  export let icon: ButtonComponent["icon"] = undefined
  export let text: ButtonComponent["text"] = ""
  export let design: ButtonComponent["design"] = "default"
  export let loading: ButtonComponent["loading"] = false
  export let inverted: ButtonComponent["inverted"] = false
  export let disabled: ButtonComponent["disabled"] = false
  export let fill: ButtonComponent["fill"] = false
</script>

<!-- TODO: Hover effect, Disable, Click action -->

<button class:inverted class:fill class:disabled class={design}>
  {#if loading}
    <Loading />
  {:else}
    <Icon {icon} />
    <Text {text} />
  {/if}
</button>

<style lang="postcss">
  button {
    @apply flex;
    @apply items-center;
    @apply justify-center;
    @apply gap-2;
    @apply p-5;
    @apply rounded-default;
    @apply overflow-hidden;
  }

  /* Disabled */
  .disabled {
    @apply bg-subtitle/light;
    @apply text-subtitle;

    @apply after:bg-transparent;
  }

  /* Design */
  .default {
    @apply bg-transparent;
    @apply text-white;
    @apply font-medium;
  }

  .primary {
    @apply bg-primary/light;
    @apply text-primary;
    @apply font-medium;
  }

  .secondary {
    @apply bg-subtitle/light;
    @apply text-subtitle;
    @apply font-medium;
  }

  .error {
    @apply bg-error/light;
    @apply text-error;
    @apply font-medium;
  }

  /* Fill */
  .fill {
    &.default {
      @apply text-background;
      @apply bg-title;
    }

    &.primary {
      @apply text-background;
      @apply bg-primary;
    }

    &.secondary {
      @apply text-background;
      @apply bg-subtitle;
    }

    &.error {
      @apply bg-error;
      @apply text-white;
    }
  }

  /* Inverted button */
  .inverted {
    @apply flex-row-reverse;
  }
</style>
