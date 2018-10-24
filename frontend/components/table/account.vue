<template>
	<div>
		<b-table 
				striped 
				bordered 
				small 
				:items="data" 
				:fields="field" 
				:filter="filter"
				hover
				class="m-0"
				@row-dblclicked="dblClickTable"
		        @row-clicked="clickTable">
				<template slot="bank" slot-scope="data">
					{{data.value.code}} {{data.value.name}}
				</template>
				<template slot="branch" slot-scope="data">
					{{data.value.code}} {{data.value.name}}
				</template>
		</b-table>
	</div>
</template>
<script lang="ts">
	import { Component,  Prop, Vue } from 'nuxt-property-decorator'

	@Component
	export default class Account extends Vue {

		@Prop()
		data: Object;

		@Prop()
		filter: String;

		// change lang
		main_field_bank_lang = ""
		main_field_branch_lang = ""
		main_field_deposit_type_lang = ""
		main_field_accnt_num_lang = ""
		main_field_comp_num_lang = ""
		main_field_comp_kana_lang = ""

		created() {
			this.main_field_bank_lang = this.$t("Auth.components.table.account.main_field_bank").toString()
			this.$set(this.field[1], "label", this.main_field_bank_lang)
			this.main_field_branch_lang = this.$t("Auth.components.table.account.main_field_branch").toString()
			this.$set(this.field[2], "label", this.main_field_branch_lang)
			this.main_field_deposit_type_lang = this.$t("Auth.components.table.account.main_field_deposit_type").toString()
			this.$set(this.field[3], "label", this.main_field_deposit_type_lang)
			this.main_field_accnt_num_lang = this.$t("Auth.components.table.account.main_field_accnt_num").toString()
			this.$set(this.field[4], "label", this.main_field_accnt_num_lang)
			this.main_field_comp_num_lang = this.$t("Auth.components.table.account.main_field_comp_num").toString()
			this.$set(this.field[5], "label", this.main_field_comp_num_lang)
			this.main_field_comp_kana_lang = this.$t("Auth.components.table.account.main_field_comp_kana").toString()
			this.$set(this.field[6], "label", this.main_field_comp_kana_lang)
		}

		field = [
			{key: 'no',label: 'No',class:'text-center table-col-width-50',sortable: true},
			{key: 'bank',label: '',sortable: true},
			{key: 'branch',label: ''},
			{key: 'deposit_type',label: '',class:'table-col-width-90',sortable: true},
			{key: 'account_number',label: '',class:'text-center table-col-width-80',sortable: true},
			{key: 'company_number',label: '',class:'text-center table-col-width-90',sortable: true},
			{key: 'company_kana', label: '',sortable: true}
		]

		dblClickTable(value){
			this.$emit('dblClickTable', value)
		}

		clickTable(value){
			this.$emit('clickTable', value)
		}

	}
</script>