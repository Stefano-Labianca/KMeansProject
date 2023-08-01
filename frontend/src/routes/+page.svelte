<script lang="ts">
  import Button from "$components/Button/Button.svelte"
  import Text from "$components/Text/Text.svelte"
  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import CrudEndPoint from "../api/crud"

  async function caller() {
    console.log(await CrudEndPoint.read("/api/calculate?k=11111"))
  }
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<Button text="AA" design="primary" fill onClick={caller} />
