<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            :label="main_field_bank_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-select
					:options="{ '1': '東京銀行', '2': '北海道銀行', '3': '札幌銀行' }"
					size="sm" id="bank"
					class="input-width-150"/>
		</b-form-group>
	    <b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            :label="main_field_branch_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-select
					:options="{ '1': '東京支店', '2': '北海道支店', '3': '札幌支店' }"
					size="sm" id="branch"
					class="input-width-150"/>
		</b-form-group>
	    <b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            :label="main_field_deposit_type_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" class="input-width-100" v-model="localData.deposit_type"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            :label="main_field_accnt_num_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" class="input-width-100" v-model="localData.account_number"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            :label="main_field_comp_num_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" class="input-width-100" v-model="localData.company_number"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            :label="main_field_comp_kana_lang"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" class="input-width-100" v-model="localData.company_kana"></b-form-input>
		</b-form-group>
		<b-col class="text-right p-0">
			<b-button class="button-width-90 m-2" variant="danger" @click="clickCancel">取消</b-button>
			<b-button class="button-width-90 my-2 ml-2" variant="primary" v-b-modal="'confirm'" type="submit">{{processName}}</b-button>
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

		// change lang
		main_field_bank_lang = ""
		main_field_branch_lang = ""
		main_field_deposit_type_lang = ""
		main_field_accnt_num_lang = ""
		main_field_comp_num_lang = ""
		main_field_comp_kana_lang = ""

		created() {
			this.main_field_bank_lang = this.$t("Auth.components.table.account.main_field_bank").toString()
			this.main_field_branch_lang = this.$t("Auth.components.table.account.main_field_branch").toString()
			this.main_field_deposit_type_lang = this.$t("Auth.components.table.account.main_field_deposit_type").toString()
			this.main_field_accnt_num_lang = this.$t("Auth.components.table.account.main_field_accnt_num").toString()
			this.main_field_comp_num_lang = this.$t("Auth.components.table.account.main_field_comp_num").toString()
			this.main_field_comp_kana_lang = this.$t("Auth.components.table.account.main_field_comp_kana").toString()
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