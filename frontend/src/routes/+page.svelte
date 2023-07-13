<script>
  import History from "$components/History/History.svelte"
  import Text from "$components/Text/Text.svelte"

  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="my-32 w-1/4">
  <Text text="History" role="title" />
  <History />
</div>
