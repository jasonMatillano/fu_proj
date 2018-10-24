<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="支払区分"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.payment_class.name" v-if="localData.payment_class != null"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="補助区分"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.support_class" class="input-width-50"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="仕訳名"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="借方　主科目"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.debit.name" v-if="localData.debit != null"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="貸方　主科目"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.credit.name"  v-if="localData.debit != null"></b-form-input>
		</b-form-group>
		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">キャンセル</b-button>
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

		submit(){
			this.$emit('submit', {data: this.localData,"type" : "payment"})
		}
		clickCancel(){
			this.$emit('clickCancel', false)
		}

		mounted() {
		  	this.localData = Object.assign({}, this.formdata)
		  	console.log(this.localData)
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