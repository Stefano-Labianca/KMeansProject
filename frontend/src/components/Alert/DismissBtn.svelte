<script lang="ts">
  import alerts from "$stores/alert"
  import CloseIcon from "../../assets/icons/CloseIcon.svelte"
  import Icon from "../Icon/Icon.svelte"
  import type { AlertComponent } from "./alert"

  export let id: AlertComponent["id"]

  /**
   * Rimuove una notifica in base all'id
   */
  const dismissAlert = () => {
    alerts.remove(id)
  }
</script>

<button type="button" class="DismissBtn" on:click={dismissAlert}>
  <Icon icon={CloseIcon} />
</button>

<style lang="postcss">
  .DismissBtn {
    @apply ml-auto;
  }
</style>
