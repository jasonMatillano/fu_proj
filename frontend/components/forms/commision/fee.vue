<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="3"
		            label-size="sm"
		            :label="fee_field_name_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="3"
		            label-size="sm"
		            :label="fee_field_window_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.window"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="3"
		            label-size="sm"
		            :label="fee_field_machine_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.auto"></b-form-input>
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

		fee_field_name_lang = ""
		fee_field_window_lang = ""
		fee_field_machine_lang = ""

		created() {
			this.fee_field_name_lang = this.$t('Auth.pages.commision.fee_field_name').toString()
			this.fee_field_window_lang = this.$t('Auth.pages.commision.fee_field_window').toString()
			this.fee_field_machine_lang = this.$t('Auth.pages.commision.fee_field_machine').toString()
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