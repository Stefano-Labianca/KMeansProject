<script>
  import Entry from "$components/Entry/Entry.svelte"
  import Text from "$components/Text/Text.svelte"

  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import dayjs from "dayjs"
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="my-32">
  <Entry title="First Computation" date={dayjs().format("D MMMM YYYY")} />
</div>
