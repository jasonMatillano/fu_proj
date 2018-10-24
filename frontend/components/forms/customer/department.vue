<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="得意先部課CD"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.code" class="width-5"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="得意先部課名"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="電話番号"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.phone_number" class="width-11"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="内線"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.extension"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		        :label-cols="5"
		        label-size="sm"
		        label="終了"
		        label-for="input_sm"
		        class="my-1">
				<b-form-checkbox id="終了"
			                     value="終了"
			                     unchecked-value=" ">
			    </b-form-checkbox>
		</b-form-group>	

		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">キャンセル</b-button>
			<b-button class="button-width-90" variant="primary"  v-b-modal="'confirm'" type="submit">{{processName}}</b-button>
		</b-col>
	</b-form>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';

	@Component
	export default class Cgroup extends Vue {

		@Prop()
		formdata: Object;
		
		@Prop()
		processName: String;

		localData = {};

		submit(){
			this.$emit('submit',this.localData)
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
	@import '@/assets/scss/forms.scss';
</style>