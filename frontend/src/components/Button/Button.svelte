<script lang="ts">
  import Icon from "../Icon/Icon.svelte"
  import Text from "../Text/Text.svelte"
  import Loading from "./Loading.svelte"

  import { isButtonFilled, spinnerModifier } from "$stores/spinner"
  import type { ButtonComponent } from "./button"

  type $$Props = ButtonComponent

  export let icon: ButtonComponent["icon"] = undefined
  export let text: ButtonComponent["text"] = ""
  export let design: ButtonComponent["design"] = "default"
  export let loading: ButtonComponent["loading"] = false
  export let inverted: ButtonComponent["inverted"] = false
  export let disabled: ButtonComponent["disabled"] = false
  export let fill: ButtonComponent["fill"] = false
  export let onClick: ButtonComponent["onClick"] = undefined

  $spinnerModifier = loading ? design : "default"
  $isButtonFilled = fill ? true : false

  let isDisabled: boolean
  $: isDisabled = loading || disabled || false
</script>

<button
  class:inverted
  class:clickable={!isDisabled}
  class:hoverable={!isDisabled}
  class:fill
  class:disabled={isDisabled}
  disabled={isDisabled}
  class="animated {design}"
  on:click={onClick}
  {...$$restProps}
>
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
    @apply gap-small;
    @apply p-default;
    @apply rounded-default;
    @apply overflow-hidden;
    @apply h-full;
    @apply w-full;
  }

  /* Design */
  .default {
    @apply bg-transparent;
    @apply text-title;
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
      @apply text-title;

      @apply after:bg-background;
    }
  }

  /* Inverted button */
  .inverted {
    @apply flex-row-reverse;
  }

  /* Disabled */
  .disabled {
    @apply cursor-default;
  }

  /* Clickable */
  .clickable {
    @apply cursor-pointer;

    @apply active:scale-[0.95];
  }

  .animated {
    @apply duration-default;
    @apply ease-default;

    @apply before:absolute;
    @apply before:duration-default;
    @apply before:ease-default;

    @apply after:absolute;
    @apply after:duration-default;
    @apply after:ease-default;
  }

  /* Hoverable */
  .hoverable {
    @apply relative;

    @apply after:content-[""];
    @apply after:absolute;
    @apply after:inset-0;
    @apply after:bg-current;
    @apply after:opacity-0;

    @apply hover:after:opacity-soft;
  }
</style>
