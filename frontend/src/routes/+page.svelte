<script>
  import Input from "$components/Input/Input.svelte"
  import Text from "$components/Text/Text.svelte"

  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="w-full flex my-12">
  <div class="flex items-center gap-medium">
    <Input type="number" placeholder="Inserire dei numeri" />
    <Input type="text" placeholder="Scrivi quello che vuoi" />
  </div>
</div>
