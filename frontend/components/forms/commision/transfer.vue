<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="2"
		            label-size="sm"
		            :label="transfer_field_max_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.greater"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="2"
		            label-size="sm"
		            :label="transfer_field_min_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.less"></b-form-input>
		</b-form-group>
		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">取消</b-button>
			<b-button class="button-width-90" variant="primary" v-b-modal="'confirm'" type="submit">{{processName}}</b-button>
		</b-col>
	</b-form>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';

	@Component
	export default class Warehouse extends Vue {

		@Prop()
		formdata: Object;

		@Prop()
		processName: String;

		localData = {};

		transfer_field_max_lang = ""
		transfer_field_min_lang = ""

		created() {
			this.transfer_field_min_lang = this.$t('Auth.pages.commision.transfer_field_min').toString()
			this.transfer_field_max_lang = this.$t('Auth.pages.commision.transfer_field_max').toString()
		}

		submit(){
			this.$emit('submit', this.localData)
		}
		clickCancel(){
			this.$emit('clickCancel', false)
		}

		mounted() {
		  	this.localData = Object.assign({}, this.formdata)
		}

		@Watch('formdata')
		onChanged(val: string, oldVal: string){
			this.localData = Object.assign({}, val)
		}

	}
</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	form{
		input,select{
			border: 1px solid $black;
			font-size: $normalrem;
			font-weight: 600;
		}
		button{
			&.button-width{
					&-90{
						@include give-width(90px);
						font-size: $normalrem;
					}
				}
		}
	}
</style>