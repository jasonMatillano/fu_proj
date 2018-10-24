<template>
	<b-container fluid>
	   <nuxt/>
	</b-container> 
</template>
<script lang="ts">
  export default {
    data () {
      return {}
    }
  }
</script>
<style lang="scss" scoped>
	.container-fluid {
		padding: unset;
		font-family: Meiryo;
	}

	@media only screen and (max-width: 320px) {
	    .container-fluid {
			width: 320px;	
		}
	}
</style>
