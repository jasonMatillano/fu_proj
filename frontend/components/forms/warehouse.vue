<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="倉庫CD"
		            label-for="input_sm"
		            class="mb-1">
			<b-col>
            	<b-row>
            		<b-form-input size="sm" v-model="localData.code" class="width-5"></b-form-input>
					<b-form-group horizontal
					            :label-cols="6"
					            label-size="sm"
					            label="終了"
					            label-for="input_sm"
					            class="m-0 ml-5"
					            v-if="localData['id'] != null">
						<b-form-checkbox id="isResigned"
					                     v-model="localData.isResigned">
					    </b-form-checkbox>
					</b-form-group>
            	</b-row>
            </b-col>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="倉庫名"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					:label-cols="4"
					label-size="sm"
					label="倉庫カナ"
					label-for="input_sm"
					class="mb-1">
			<b-form-input size="sm" v-model="localData.kana"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					:label-cols="4"
					label-size="sm"
					label="倉庫略称"
					label-for="input_sm"
					class="mb-1">
			<b-form-input size="sm" v-model="localData.shortname"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					:label-cols="4"
					label-size="sm"
					label="〒"
					label-for="input_sm"
					class="mb-1">
			<b-form-input size="sm" class="width-10" v-model="localData.postal_code"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					:label-cols="4"
					label-size="sm"
					label="住所"
					label-for="input_sm"
					class="mb-1">
			<b-form-input size="sm" class="mb-1" v-model="localData.addr_one"></b-form-input>
			<b-form-input size="sm" v-model="localData.addr_two"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					:label-cols="4"
					label-size="sm"
					label="電話番号"
					label-for="input_sm"
					class="mb-1">
			<b-form-input size="sm" class="width-10" v-model="localData.phone_num"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					:label-cols="4"
					label-size="sm"
					label="FAX番号"
					label-for="input_sm"
					class="mb-1">
			<b-form-input size="sm" class="width-10" v-model="localData.fax_num"></b-form-input>
		</b-form-group>
		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">キャンセル</b-button>
			<b-button class="button-width-90" variant="primary" v-b-modal="'confirm'" type="submit">{{localData["id"] != null ? "変更" : "新規登録"}}</b-button>
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
			this.$emit('submit', {data: this.localData, success: true})
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