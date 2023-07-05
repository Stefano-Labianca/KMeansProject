<script>
  import PlusIcon from "../assets/icons/PlusIcon.svelte"
  import Button from "../components/Button/Button.svelte"
  import Text from "../components/Text/Text.svelte"
</script>

<Text text="k-means project" role="paragraph" />

<div class="flex justify-evenly my-32">
  <Button text="Add" icon={PlusIcon} design="default" inverted />
  <Button text="Add" icon={PlusIcon} design="primary" inverted />
  <Button text="Add" icon={PlusIcon} design="secondary" inverted />
  <Button text="Add" icon={PlusIcon} design="error" inverted />
</div>

<div class="flex justify-evenly">
  <Button text="Add" icon={PlusIcon} design="default" inverted fill />
  <Button text="Add" icon={PlusIcon} design="primary" inverted fill />
  <Button text="Add" icon={PlusIcon} design="secondary" inverted fill />
  <Button text="Add" icon={PlusIcon} design="error" inverted fill />
</div>
