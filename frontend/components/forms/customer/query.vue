<template>
	<section class="query">
		<div>
			<h6>あいまい検索</h6>
		</div>
		<div class="pb-1">
		    <b-form inline>
				<label for="name" class="w-60">納入先名 </label>
				<b-input class="mb-2 mr-sm-2 mb-sm-0 w-120" id="name" size="sm"/>
					<b-form-radio-group :options="['and', 'or']"
					                  class="line-height-2">
					</b-form-radio-group>
				<b-input class="mb-2 mr-sm-2 mb-sm-0 w-120" id="name" size="sm"/>
		    </b-form>
		</div>
		<div class="pb-1">
		    <b-form inline>
				<label for="name" class="w-60">住所 </label>
				<b-input class="mb-2 mr-sm-2 mb-sm-0 w-120" id="name" size="sm"/>
				<b-form-radio-group class="line-height-2" :options="['and', 'or']" />
				<b-input class="mb-2 mr-sm-2 mb-sm-0 w-120" id="name" size="sm"/>
		    </b-form>
		</div>
		<div class="pb-1">
		    <b-form inline>
				<label for="name" class="w-60">電話番号 </label>
				<b-input class="mb-2 mr-sm-2 mb-sm-0 w-120" id="name" size="sm"/>
				<b-form-radio-group class="line-height-2" :options="['and', 'or']" />
				<b-input class="mb-2 mr-sm-2 mb-sm-0 w-120 line-height-2" id="name" size="sm"/>
		    </b-form>
		</div>
		<div>
			<b-button variant="success" size="sm" class="float-right mt-3">none</b-button>
		</div>
	</section>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';

	@Component
	export default class Sform extends Vue {

		

	}
</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	@import '@/assets/scss/forms.scss';
	
	.query{
		input{
			&.w-120{
				@include give-width(120px);
			}
		}
		label{
			&.w-60{
				@include give-width(60px);
				justify-content: left;
			}
		}
	}
</style>