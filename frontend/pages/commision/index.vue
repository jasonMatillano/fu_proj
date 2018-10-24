<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="6">
				<section class="field-input mb-2">
					<b-col class="p-0" md="6" sm="12">
						<b-form-input v-model="filter" :placeholder="filter_lang" size="sm"/>
					</b-col>
				</section>
				<section class="field-table">
					<accountTable 
						:data="data" 
						:filter="filter"
						@clickTable="clickTableAcc"
					></accountTable>
				</section>
			</b-col>
			<b-col xl="2">
				<section class="mt-3 field-label">
					<label class="m-0">［　{{transfer_amount_lang}}　］</label>
				</section>
				<section class="field-table table-half">
					<b-table striped
					         bordered
				         	 small
					         :items="transferData"
					         :fields="transferField"
					         hover
					         tbody-class="table-half"
					         @row-dblclicked="dblClickTableTrans">
					         <template slot="to" slot-scope="data">～</template>
					</b-table>
				</section>
				<section class="mt-2 field-label">
					<label class="m-0">［　{{fee_lang}}　］</label>
				</section>
				<section class="field-table table-half">
					<b-table striped
					         bordered
				         	 small
					         :items="feeData"
					         :fields="feeField"
					         hover
					         tbody-class="table-half"
					         @row-dblclicked="dblClickTableFee">
					</b-table>	
				</section>
			</b-col>
			<b-col  xl="4" md="12">
				<b-row>
					<b-col sm="10">
						<section class="field-form">

							<transition name="fade">
								<b-col v-if="formShowTransfer" class="position-absolute">
									<b-card bg-variant="light">
										<b-col>
											<b-form-group>
												<b-row class="m-0 pb-1">
													<b-col sm="12" class="p-0">
														<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/></span> {{tableName}}　＞　{{processName}}</h5>
													</b-col>
											    </b-row>
											    <c-form-transfer 
													:formdata="prevData" 
													@submit="submit" 
													@clickCancel="clickCancel" 
													:processName="processName"
												></c-form-transfer>
											</b-form-group>
										</b-col>	
									</b-card>
								</b-col>
							</transition>
						
							<transition name="fade">
								<b-col v-if="formShowFee" class="position-absolute">
									<b-card bg-variant="light">
										<b-col>
											<b-form-group>
												<b-row class="m-0 pb-1">
													<b-col sm="12" class="p-0">
														<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/></span> {{tableName}}　＞　{{processName}}</h5>
													</b-col>
											    </b-row>
											    <c-form-fee 
													:formdata="prevData" 
													@submit="submit" 
													@clickCancel="clickCancel" 
													:processName="processName"
												></c-form-fee>
											</b-form-group>
										</b-col>	
									</b-card>
								</b-col>
							</transition>

						</section>		
					</b-col>
				</b-row>
				
			</b-col>
		</b-row>
		<c_footer class="content-footer" 
			@eventTriggered="buttonProcess"
			:events="UIEvents"
		></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator'

	import c_footer from '@/components/footer.vue'
	import accountTable from '@/components/table/account'

	import cFormTransfer from '@/components/forms/commision/transfer'
	import cFormFee from '@/components/forms/commision/fee'

	import {UIEvent} from "@/assets/ts/UIEvent.ts"
	import {Keys} from "@/assets/ts/keys.ts"

	@Component({
		components:{
			c_footer,
			accountTable,
			cFormTransfer,
			cFormFee
		},
	})

	export default class Account extends Vue {

		registerShow = false
		processName = ''
		filter = ''
		feeData = []
		transferData = []
		prevData = []
		formShowTransfer = false
		formShowFee = false
		tableName = ''

		// change language
		title_lang = ''
		filter_lang = ''
		transfer_amount_lang = ''
		fee_lang = ''
		fee_field_name_lang = ''
		fee_field_window_lang = ''
		fee_field_machine_lang = ''
		transfer_field_min_lang = ''
		transfer_field_max_lang = ''

		// form headings
		transfer_form_heading = ""
		fee_form_heading = ""

		UIEvents:Array<UIEvent> = [ 
			new UIEvent(Keys.F2,'取消',true),
			new UIEvent(Keys.F3,'削除',true),
			new UIEvent(Keys.F4,'行作成',true),
			new UIEvent(Keys.F6,'日付',true),
			// new UIEvent(Keys.F8,'リスト',true),
			new UIEvent(Keys.F12,'メニュ',true),
		]

		feeField = [
			{key: 'name',label: '',class:'table-col-width-75'},
			{key: 'window',label: ''},
			{key: 'auto',label: '',class: 'text-right'},
		]

		feeItem = [
			[
				{name: '同一本支店',window: '',auto: '0'},
				{name: '当行本支店',window: '',auto: '111'},
				{name: '他行文書',window: '',auto: '222'},
				{name: '他行電信',window: '',auto: '333'},
			],
			[
				{name: '同一本支店',window: '',auto: '105'},
				{name: '当行本支店',window: '',auto: '210'},
				{name: '他行文書',window: '',auto: ''},
				{name: '他行電信',window: '',auto: '525'},
			],
			[
				{name: '同一本支店',window: '',auto: '5'},
				{name: '当行本支店',window: '',auto: '25'},
				{name: '他行文書',window: '',auto: '105'},
				{name: '他行電信',window: '',auto: '600'},
			]
		]

		transferField = [
			{key: 'greater',label: '',class: 'text-right border-right-0',sortable: true},
			{key: 'to',label: '~',class: 'text-center table-col-width-50 border-right-0 border-left-0'},
			{key: 'less',label: '未満',class: 'text-right border-left-0',sortable: true}
		]

		transferItem = [
			[
				{greater: '0',less: '15000'},
				{greater: '15000',less: '30000'},
			],
			[
				{greater: '0',less: '15000'},
				{greater: '15000',less: '30000'},
				{greater: '30000',less: ''},
			],
			[
				{greater: '0',less: '2000'},
				{greater: '2000',less: '3000'},
				{greater: '3000',less: '3500'},
				{greater: '3500',less: ''},
			],
		]

		data = [
			{no: '1', bank: {name: 'みずは銀行', code: '0001'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ',transfer:{greater: '0',less: '15000'}},
			{no: '2', bank: {name: 'みずは銀行', code: '0002'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '3', bank: {name: 'みずは銀行', code: '0003'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '4', bank: {name: 'みずは銀行', code: '0004'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '5', bank: {name: 'みずは銀行', code: '0005'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '6', bank: {name: 'みずは銀行', code: '0006'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '7', bank: {name: 'みずは銀行', code: '0007'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '8', bank: {name: 'みずは銀行', code: '0008'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '9', bank: {name: 'みずは銀行', code: '0009'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '10', bank: {name: 'みずは銀行', code: '0010'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '11', bank: {name: 'みずは銀行', code: '0011'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '12', bank: {name: 'みずは銀行', code: '0012'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '13', bank: {name: 'みずは銀行', code: '0013'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '14', bank: {name: 'みずは銀行', code: '0014'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '15', bank: {name: 'みずは銀行', code: '0015'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '16', bank: {name: 'みずは銀行', code: '0016'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '17', bank: {name: 'みずは銀行', code: '0017'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '18', bank: {name: 'みずは銀行', code: '0018'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '19', bank: {name: 'みずは銀行', code: '0019'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '20', bank: {name: 'みずは銀行', code: '0020'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '21', bank: {name: 'みずは銀行', code: '0021'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '22', bank: {name: 'みずは銀行', code: '0022'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '23', bank: {name: 'みずは銀行', code: '0023'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '24', bank: {name: 'みずは銀行', code: '0024'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '25', bank: {name: 'みずは銀行', code: '0025'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
			{no: '26', bank: {name: 'みずは銀行', code: '0026'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ'},
		]

		created() {
			this.title_lang = this.$t('Auth.pages.commision.title').toString()
			this.filter_lang = this.$t('Auth.pages.commision.filter').toString()
			this.transfer_amount_lang = this.$t('Auth.pages.commision.transfer_amount').toString()
			this.fee_lang = this.$t('Auth.pages.commision.fee').toString()

			this.transfer_form_heading = this.$t('Auth.pages.commision.transfer_form_heading').toString()
			this.fee_form_heading = this.$t('Auth.pages.commision.fee_form_heading').toString()

			this.fee_field_name_lang = this.$t('Auth.pages.commision.fee_field_name').toString()
			this.$set(this.feeField[0], "label", this.fee_field_name_lang)
			this.fee_field_window_lang = this.$t('Auth.pages.commision.fee_field_window').toString()
			this.$set(this.feeField[1], "label", this.fee_field_window_lang)
			this.fee_field_machine_lang = this.$t('Auth.pages.commision.fee_field_machine').toString()
			this.$set(this.feeField[2], "label", this.fee_field_machine_lang)

			this.transfer_field_min_lang = this.$t('Auth.pages.commision.transfer_field_min').toString()
			this.$set(this.transferField[0], "label", this.transfer_field_min_lang)
			this.transfer_field_max_lang = this.$t('Auth.pages.commision.transfer_field_max').toString()
			this.$set(this.transferField[2], "label", this.transfer_field_max_lang)
		}


		clickButton(item){
			this.processName = item.name
			this.reset()
			this.prevData = []
		}

		dblClickTableTrans(item){
			this.tableName = this.transfer_form_heading
			this.formShowFee = false
			this.formShowTransfer = true
			this.reset()
			this.processName = "変更"
			this.prevData = item
			this.set(item)
		}

		dblClickTableFee(item){
			this.tableName = this.fee_form_heading
			this.formShowTransfer = false
			this.formShowFee = true
			this.reset()
			this.processName = "変更"
			this.prevData = item
			this.set(item)
		}

		clickTableAcc(item){
			this.reset()
			this.prevData = item
			this.set(item)
			this.feeData = this.feeItem[item.no - 1]
			this.transferData = this.transferItem[item.no - 1]
		}

		set(item){
			if(this.processName === "変更" || this.processName === ''){
				this.prevData = item
				Vue.set(item, "_rowVariant", "info");
			}
		}
		
		reset(){
			if(this.prevData !== null){
				Vue.set(this.prevData,"_rowVariant","")
			}
		}

		clickCancel(){
			this.reset()
			this.processName = ''
			this.prevData = []
			this.formShowTransfer = false
			this.formShowFee = false
		}

		buttonProcess(val) {
			alert(JSON.stringify(val))
			//this.processFooterValues(val)
		}

		processFooterValues(val) {
			switch(val._eventName) {
				// perform delete and create operation
				case "新規":
				case "New":
				case "削除":
				case "Delete":
					//this.buttonProcess(val)
				break;
				
				// perform cancel operation
				case "取消":
				case "Cancel":
					//this.closeForm()
				break;

				// perform date and list modals
				case "日付":
				case "Date":
				case "リスト":
				case "List":
					//this.openModalPr(val)
				break;

				// to to main menu
				case "メニュ":
				case "Menu":	
					//this.goToMenu()
				break;

				// if value is blank
				case "":
					// do nothing
				break;

				// default 
				default:
					alert("Default from processFooterValues");	
				break;
			}		
		}
	}
	
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss'
</style>