<script lang="ts">
  import { ALERT_TIMEOUT } from "$lib/consts"
  import { nanoid } from "nanoid"
  import Icon from "../Icon/Icon.svelte"
  import Text from "../Text/Text.svelte"
  import DismissBtn from "./DismissBtn.svelte"
  import type { AlertComponent } from "./alert"

  export let id: AlertComponent["id"] = nanoid()
  export let icon: AlertComponent["icon"] = undefined
  export let text: AlertComponent["text"]
  export let design: AlertComponent["design"] = "default"
</script>

<div class="Alert {design}" style="--timeout: {ALERT_TIMEOUT}ms">
  <Icon {icon} />
  <Text {text} />
  <DismissBtn {id} />
</div>

<style lang="postcss">
  @keyframes progress {
    from {
      @apply w-full;
    }

    to {
      @apply w-0;
    }
  }

  .Alert {
    @apply relative;
    @apply flex;
    @apply items-center;
    @apply gap-small;
    @apply p-default;
    @apply rounded-default;

    @apply after:content-[""];
    @apply after:absolute;
    @apply after:bottom-0;
    @apply after:left-0;
    @apply after:w-full;
    @apply after:h-1;
    @apply after:opacity-medium;
    @apply after:animate-[progress_var(--timeout)_linear];
  }

  /* Design */
  .primary {
    @apply bg-primary;
    @apply text-background;

    @apply after:bg-background;
  }

  .error {
    @apply bg-error;
    @apply text-background;

    @apply after:bg-background;
  }
</style>
