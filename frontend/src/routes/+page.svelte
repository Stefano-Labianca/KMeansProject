<script>
  import Button from "$components/Button/Button.svelte"
  import Text from "$components/Text/Text.svelte"
  import ErrorIcon from "$icons/ErrorIcon.svelte"
  import LikeIcon from "$icons/LikeIcon.svelte"

  import Alerts from "$layouts/Alerts/Alerts/Alerts.svelte"
  import alerts from "$stores/alert"
</script>

<Text text="k-means project" role="paragraph" />
<Alerts />

<div class="flex justify-center my-32">
  <Button
    text="Success"
    design="primary"
    inverted
    fill
    onClick={() =>
      alerts.send({ icon: LikeIcon, text: "Success", design: "primary" })}
  />

  <div class="mr-32" />

  <Button
    text="Error"
    design="error"
    inverted
    fill
    onClick={() =>
      alerts.send({ icon: ErrorIcon, text: "Error", design: "error" })}
  />
</div>
