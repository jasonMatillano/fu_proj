<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
				<!-- <p> 
					currBank: {{currBank}} bankLen: {{bankItems.length}}<br>
					currBranch: {{currBranch}} branchLen: {{branchData.length}} <br>
					section_name: {{section_name}}, processName: {{processName}} <br>
					formData: {{formData}}
				</p>
				<p>
					modalPrShowing: {{modalPrShowing}} <br>
					modalType: {{modalType}} 
				</p> -->
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="4" @click="bankFirstClickDefault">
				<section class="mb-2 field-input">
					<b-col md="8" sm="12" class="p-0">
						<b-row>
							<b-col xl="8">
								<b-form-input v-model="filterBankSearch" :placeholder="filter_lang" size="sm"/>
							</b-col>
							<b-col xl="4">
								<b-form-group horizontal :label-cols="6" label-size="sm" :label="end_lang" label-for="input_sm" class="m-0">
									<b-form-checkbox id="end_page"
										v-model="filterBankIsEnd"
										@input="filterBankIsEndClick()"
									></b-form-checkbox>
								</b-form-group>
							</b-col>
						</b-row>
					</b-col>
				</section>
				<section class="field-table">
					<b-table striped
					         bordered
				         	 small
					         :items="bankItems"
					         :fields="bankFields"
					         hover
					         :filter="filterBankSearch"
					         @row-clicked="changeHighLightBanks"
							 @row-dblclicked="buttonProcess({name:edit_lang})"
					>
					<template slot="end" slot-scope="data">
					 	{{data.value == true ? '終了' : ' '}}
					</template>
					</b-table>
				</section>
			</b-col>
			<!-- Form Section-->
				<b-col xl="4">
					<transition name="fade">
						<section class="field-form" v-if="formShow">
							<b-row>
								<b-col :class="arrowPosition"><span><fa :icon="tableArrow" size="2x"></fa></span></b-col>
							</b-row>
							<b-card bg-variant="light">
								<c_form
									:section_name="section_name"
									:processName="processName"
									:formIsDisabled="formIsDisabled"
									:formData="formData"
									@clickCancel="closeForm"
									@submitForm="submitForm"
									@changeCD="formData.val_code = $event"
									@changeName="formData.val_name = $event"
									@changeNameKana="formData.val_kana = $event"
									@changeEndVal="formData.val_end = $event"
								></c_form>
							</b-card>
						</section>
					</transition>
				</b-col>
			<!-- /Form Section-->
			<b-col xl="4" @click="branchFirstClickDefault">
				<section class="mb-2 field-input">
					<b-col md="8" sm="12" class="p-0">
						<b-row>
							<b-col xl="8">
								<b-form-input v-model="filterBranchSearch" :placeholder="filter_lang" size="sm"/>
							</b-col>
							<b-col xl="4">
								<b-form-group horizontal :label-cols="6" label-size="sm" :label="end_lang" label-for="input_sm" class="m-0">
									<b-form-checkbox id="end_page2"
										v-model="filterBranchIsEnd"
										@input="filterBranchIsEndClick()"
									></b-form-checkbox>
								</b-form-group>
							</b-col>
						</b-row>
					</b-col>
				</section>
				<section class="field-table">
					<b-table striped
							bordered
							small
							:items="branchData"
							:fields="branchFields"
							hover
							:filter="filterBranchSearch"
							@row-clicked="changeHighLightBranchs"
							@row-dblclicked="buttonProcess({name:edit_lang})"
					>
					<template slot="end" slot-scope="data">
					 	{{data.value == true ? '終了' : ' '}}
					</template>
					</b-table>	
				</section>
			</b-col>
		</b-row>
		<c_modal_pr
			:modalPrShowing="modalPrShowing"
			:modalType="modalType"
			:section_name="section_name"
			@closeModalPr="closeModalPr"
		></c_modal_pr>
		<c_footer class="content-footer" 	
			:fnFunctions="fnFunctions"
			@fnClickOrKeyDown="fnClickOrKeyDown">
		</c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator'

	import c_footer from '@/components/footer_pr'
	import c_form from '@/components/forms/bank'
	import c_modal_pr from '@/components/modal_pr'

	@Component({	
		components:{
			c_footer,
			c_form,
			c_modal_pr,
		},
	})
	export default class Bank extends Vue {
		
			// page static values
			title_lang = ''
			filter_lang = ''
			end_lang = ''
			edit_lang = ''
			bank_name_lang = ''
			bank_kana_lang = ''
			branch_name_lang = ''
			branch_kana_lang = ''


			//  for product and subprod table
			bankFields = [
							{key: 'bank_code', label: 'CD',class:"text-center table-col-width-50",sortable: true},
							{key: 'bank_name', label: '', class:"w-50"},
							{key: 'bank_name_kana', label: ''},
							{key: 'end', label: '',class:'table-col-width-50 text-center'},
						]

			branchFields = [
							{key: 'branch_code', label: 'CD',class:"text-center table-col-width-50",sortable: true},
							{key: 'branch_name', label: '',class:"w-50"},
							{key: 'branch_name_kana', label: ''},
							{key: 'end', label: '',class:'table-col-width-50 text-center'},
						]

			bankItems = [
							{bank_code: '0000',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ﾐｽﾞﾎｺｰﾎﾟﾚｰﾄ',end: true},
							{bank_code: '0001',bank_name: 'みずほコーボレート銀行1',bank_name_kana:'ﾐｽﾞﾎｺｰﾎﾟﾚｰﾄ',end: true},
							{bank_code: '0002',bank_name: 'みずほコーボレート銀行2',bank_name_kana:'ﾐｽﾞﾎｺｰﾎﾟﾚｰﾄ',end: true},
							{bank_code: '0003',bank_name: 'みずほコーボレート銀行3',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0004',bank_name: 'みずほコーボレート銀行4',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0005',bank_name: 'みずほコーボレート銀行5',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0006',bank_name: 'みずほコーボレート銀行6',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0007',bank_name: 'みずほコーボレート銀行7',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0008',bank_name: 'みずほコーボレート銀行8',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0009',bank_name: 'みずほコーボレート銀行9',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0010',bank_name: 'みずほコーボレート銀行10',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0011',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0012',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0013',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0014',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0015',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0016',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0017',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0018',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0019',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0020',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0021',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0022',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0023',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0024',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0025',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0026',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
							{bank_code: '0027',bank_name: 'みずほコーボレート銀行',bank_name_kana:'ラクテン',end: true},
						]

			branchData = []

			// footer value holders
			F1_name = "" 
			F2_name = ""
			F3_name = ""  
			F4_name = ""  
			F5_name = ""  
			F6_name = ""  
			F7_name = ""  
			F8_name = ""  
			F9_name = ""  
			F10_name = ""  
			F11_name = ""  
			F12_name = ""  

			// footer attributes
			fnFunctions = [ // footer keys initial value
				{key:112, label: "F1", variant: "secondary", name: "", action: "", isdisabled: true },//<--F1-->
				{key:113, label: "F2", variant: "secondary", name: "", action: "", isdisabled: true },//<--F2-->
				{key:114, label: "F3", variant: "secondary", name: "", action: "", isdisabled: true },//<--F3-->
				{key:115, label: "F4", variant: "secondary", name: "", action: "", isdisabled: true },//<--F4-->
				{key:116, label: "F5", variant: "secondary", name: "", action: "", isdisabled: true },//<--F5-->
				{key:117, label: "F6", variant: "secondary", name: "", action: "", isdisabled: true },//<--F6-->
				{key:118, label: "F7", variant: "secondary", name: "", action: "", isdisabled: true },//<--F7-->
				{key:119, label: "F8", variant: "secondary", name: "", action: "", isdisabled: true },//<--F8-->
				{key:120, label: "F9", variant: "secondary", name: "", action: "", isdisabled: true },//<--F9-->
				{key:121, label: "F10", variant: "secondary", name: "", action: "", isdisabled: true },//<--F10-->
				{key:122, label: "F11", variant: "secondary", name: "", action: "", isdisabled: true },//<--F11-->
				{key:123, label: "F12", variant: "secondary", name: "", action: "", isdisabled: true },//<--F12-->
			]


			// tables search filters
			filterBankSearch = ""
			filterBranchSearch = ""
			
			// 終了/End filter
			filterBankIsEnd = false
			filterBranchIsEnd = false

			// value holders for last clicked row in prod/sub-rpod
			currBank = {bank_code: '',bank_name: '',bank_name_kana:'',end: true}
			currBranch = {branch_code: '',branch_name:'',branch_name_kana: '' ,end: true}
			
			// form value holders
			section_name = ""
			section_action = ""
			processName = ""
			processAction = ""
			formIsDisabled = false
			formData = {val_code: '',val_name: '',val_kana:'',val_end: false ,val_ind: 0}

			// arrows
			arrowPosition = ""
			tableArrow = ""

			created() {
				this.setLanguage()
				this.setTableFieldsLang()
				this.setFooterValueAndLanguage()
				this.setValueForFnKeys()
				this.assignVariantAndDisableStatusForFnKeys()
				this.setTableInitialValues()
			}
			
			setLanguage() {
				this.title_lang  		= this.$t("Auth.pages.bank.title").toString()
				this.filter_lang 		= this.$t("Auth.pages.bank.filter").toString()
				this.edit_lang 			= this.$t("Auth.pages.bank.edit").toString()
				this.bank_name_lang		= this.$t("Auth.pages.bank.bank_name").toString()
				this.bank_kana_lang 	= this.$t("Auth.pages.bank.bank_kana").toString()
				this.branch_name_lang 	= this.$t("Auth.pages.bank.branch_name").toString()
				this.branch_kana_lang 	= this.$t("Auth.pages.bank.branch_kana").toString()
				this.end_lang 			= this.$t("Auth.pages.bank.end").toString()
			}
			
			setTableFieldsLang() {
				this.$set(this.bankFields[1], "label", this.bank_name_lang)
				this.$set(this.bankFields[2], "label", this.bank_kana_lang)
				this.$set(this.bankFields[3], "label", this.end_lang)
				this.$set(this.branchFields[1], "label", this.branch_name_lang)
				this.$set(this.branchFields[2], "label", this.branch_kana_lang)
				this.$set(this.branchFields[3], "label", this.end_lang)
			}

			setFooterValueAndLanguage() {
				this.F1_name		= ""
				this.F2_name 		= this.$t("Auth.pages.product.cancel").toString()
				this.F3_name 		= this.$t("Auth.pages.product.delete").toString()
				this.F4_name 		= this.$t("Auth.pages.product.new").toString()
				this.F5_name		= ""
				this.F6_name 		= this.$t("Auth.pages.product.date").toString()
				this.F7_name		= ""
				this.F8_name 		= this.$t("Auth.pages.product.list").toString()
				this.F9_name		= ""
				this.F10_name		= ""
				this.F11_name		= ""
				this.F12_name 		= this.$t("Auth.pages.product.menu").toString()
			}

			setValueForFnKeys() {
				this.$set(this.fnFunctions[0], "name", this.F1_name)
				this.$set(this.fnFunctions[1], "name", this.F2_name)
				this.$set(this.fnFunctions[2], "name", this.F3_name)
			 	this.$set(this.fnFunctions[3], "name", this.F4_name)
				this.$set(this.fnFunctions[4], "name", this.F5_name)
				this.$set(this.fnFunctions[5], "name", this.F6_name)
				this.$set(this.fnFunctions[6], "name", this.F7_name)
				this.$set(this.fnFunctions[7], "name", this.F8_name)
				this.$set(this.fnFunctions[8], "name", this.F9_name)
				this.$set(this.fnFunctions[9], "name", this.F10_name)
				this.$set(this.fnFunctions[10], "name", this.F11_name)
				this.$set(this.fnFunctions[11], "name", this.F12_name)
			}

			assignVariantAndDisableStatusForFnKeys() {
				this.fnFunctions.forEach(element => {
					if(element.name!="") {
						this.$set(element, "variant", "success")
						this.$set(element, "isdisabled", false)
					} else {
						// do nothing
					}
				});
			}

			setTableInitialValues() {
				this.setBankIndex() 
				this.changeHighLightBanks(this.bankItems[0])
				this.changeBankBranch(0)
			}

			setBankIndex() {
				var i = 0;
				this.bankItems.forEach(element => {
					this.$set(element, "ind", i)
					i++
				});
			}

			changeHighLightBanks(record) {
				this.changeArrowToBank()
				this.chooseRowToHighLightInBank(record)
				this.populateCurrentBank(record)
				this.setVarForFormContentBank(record)
				this.changeBankBranch(record.bank_code)
				this.changeFormContent()
				
			}

			changeArrowToBank() {
				this.arrowPosition = "text-left"
				this.tableArrow = "arrow-alt-circle-left"
			}

			chooseRowToHighLightInBank(record) {
				this.bankItems.forEach(element => {
					if(record.bank_code == element.bank_code) {
						this.$set(element, "_rowVariant", "info")
					} else {
						this.$set(element, "_rowVariant", "")
					}
				});
			}

			populateCurrentBank(record) {
				this.currBank = {bank_code: '',bank_name: '',bank_name_kana:'',end: false}
				this.currBank = record
			}

			setVarForFormContentBank(record){
				this.section_name = this.bank_name_lang
				if(record == null) {
					this.formData.val_code = ""
					this.formData.val_name = ""
					this.formData.val_kana = ""
					this.formData.val_end = false
					this.formData.val_ind = 0

				} else {
					this.formData.val_code = record.bank_code
					this.formData.val_name = record.bank_name
					this.formData.val_kana = record.bank
					this.formData.val_end = record.end
					this.formData.val_ind = record.ind

				}
			}

			changeHighLightBranchs(record) {
				this.changeArrowToBranch()
				this.chooseRowToHighLightInBanches(record)
				this.populateCurrBranch(record)
				this.setVarForFormContentBranch(record)
				this.changeFormContent()
			}

			changeArrowToBranch() {
				this.arrowPosition = "text-right"
				this.tableArrow = "arrow-alt-circle-right"
			}

			chooseRowToHighLightInBanches(record) {
				this.branchData.forEach(element => {
					if(record.branch_code == element.branch_code) {
						this.$set(element, "_rowVariant", "info")
					} else {
						this.$set(element, "_rowVariant", "")
					}
				});	
			}

			populateCurrBranch(record) {
				this.currBranch = {branch_code: '',branch_name: '',branch_name_kana:'',end: false}
				this.currBranch = record
			}

			setVarForFormContentBranch(recordSub){
				this.section_name = this.branch_name_lang
				if(recordSub == null) {
					this.formData.val_code = ""
					this.formData.val_name = ""
					this.formData.val_kana = ""
					this.formData.val_end = false
					this.formData.val_ind = 0

				} else {
					this.formData.val_code = recordSub.branch_code
					this.formData.val_name = recordSub.branch_name
					this.formData.val_kana = recordSub.branch_name_kana
					this.formData.val_end = recordSub.end	
					this.formData.val_ind = recordSub.ind
				}
			}

			// prodFirstClick
			nClicksProd = 0
			bankFirstClickDefault() {
				if (this.nClicksProd==0){
					this.changeHighLightBanks(this.currBank)
				} else {
					// do nothing
				}
				this.nClicksSubProd = 0
				this.nClicksProd++
			}

			// subProdFirstClick
			nClicksSubProd = 0
			branchFirstClickDefault() {
				if (this.nClicksSubProd==0){
					this.changeHighLightBranchs(this.currBranch)
				} else {
					// do nothing
				}
				this.nClicksProd = 0
				this.nClicksSubProd++
			}

			// show form 
			formShow = false
			buttonProcess(value){
				this.formShow = true // toggle show
				this.processName = value.name
				this.changeFormContent()
			}

			// close form
			closeForm() {
				this.formShow = false
				this.processName = ''
			}

			changeFormContent () {
				switch(this.section_name) {
					case "銀行名":
					case "Bank":
						{
							switch(this.processName) {
								case "新規":
								case "New":
									this.formData.val_code = ""
									this.formData.val_name = ""
									this.formData.val_kana = ""
									this.formData.val_end = false
									this.formData.val_ind = 0
									this.formIsDisabled = false		
									break;
								case "削除":
								case "Delete":
									if(this.isBankEmpty() == true) {
										alert('Bank table is empty')
										this.formShow = false 
									} else {
										this.formData.val_code = this.currBank.bank_code
										this.formData.val_name = this.currBank.bank_name
										this.formData.val_kana = this.currBank.bank_name_kana
										this.formData.val_end = this.currBank.end
										this.formIsDisabled = true
									}	
									break;
								case "変更":
								case "Edit":
									this.formData.val_code = this.currBank.bank_code
									this.formData.val_name = this.currBank.bank_name
									this.formData.val_kana =  this.currBank.bank_name_kana
									this.formData.val_end = this.currBank.end
									this.formIsDisabled = false
									break;
								case "":
									// do nothing
								break;
								default:
									alert("incorrect process name, please check again")
									break;							
							}
						
						}
						break;
					///////////////////////////////////////
					case "支店名":
					case "Branch":
						{
							switch(this.processName) {
								case "新規":
								case "New":
									this.formData.val_code = ""
									this.formData.val_name = ""
									this.formData.val_kana = ""
									this.formData.val_end = false
									this.formData.val_ind = 0
									this.formIsDisabled = false		
									break;
								case "削除":
								case "Delete":
									if(this.isBranchEmpty() == true) {
										alert('Branch table is empty')
										this.formShow = false 
									} else {
										this.formData.val_code = this.currBranch.branch_code
										this.formData.val_name = this.currBranch.branch_name
										this.formData.val_kana = this.currBranch.branch_name_kana
										this.formData.val_end = this.currBranch.end
										this.formIsDisabled = true
									}
									break;
								case "変更":
								case "Edit":
									this.formData.val_code = this.currBranch.branch_code
									this.formData.val_name = this.currBranch.branch_name
									this.formData.val_kana = this.currBranch.branch_name_kana
									this.formData.val_end = this.currBranch.end
									this.formIsDisabled = false
									break;

								case "":
									// do nothing
									break;

								default:
									alert("incorrect process name, please check again")
									break;
							}
						}
						break;
					///////////////////////////////////////////////////////
					default:
						// do nothing
						break;
				}
			}

			//submit form 
			submitForm(val) {
				switch(this.section_name) {
					case "銀行名":
					case "Bank":
					{
						switch(val) {
							case "新規":
							case "New":
								this.bankItems.push(
									{	
										bank_code: this.formData.val_code,
										bank_name: this.formData.val_name ,
										bank_name_kana: this.formData.val_kana ,
										end: this.formData.val_end
									},
								)
								this.$set(this.bankItems[this.bankItems.length-1], "ind", this.bankItems.length-1)
								this.emptyFormData()
								break;
							case "削除":
							case "Delete":
								this.bankItems.splice(this.formData.val_ind,1)
								this.setBankIndex() // reset product index
								if(this.bankItems[0] == undefined) {
									// if product is empty do nothing
								} else {
									this.changeHighLightBanks(this.bankItems[0])
								}
								this.emptyFormData()
								break;
							case "変更":
							case "Edit":
								this.bankItems.splice(
									this.formData.val_ind,1,
										{ 	
											bank_code: 		this.formData.val_code, 
											bank_name: 		this.formData.val_name,
											bank_name_kana:	this.formData.val_kana ,
											end: 			this.formData.val_end
										}
								)
								this.$set(this.bankItems[this.formData.val_ind], "ind", this.formData.val_ind)
								this.emptyFormData()
								break;
							default:
								alert("Default from submit form");	
								break;
						}
					}
					break;
					/////////////////////////////////////////
					case "支店名":
					case "Branch":
					{
						switch(val) {
							case "新規":
							case "New":
								this.branchData.push(
									{	
										branch_code: 		this.formData.val_code, 
										branch_name: 		this.formData.val_name,	
										branch_name_kana: 	this.formData.val_kana,
										end: 				this.formData.val_end ,
										ind:				this.formData.val_ind
									}
								)
								this.$set(this.branchData[this.branchData.length-1], "ind", this.branchData.length-1)
								this.emptyFormData()
								break;
							case "削除":
							case "Delete":
								this.branchData.splice(this.formData.val_ind,1)
								this.setBranchIndex() // reset subprod index
								if(this.branchData[0] == undefined) {
									// if sub-product is empty do nothing
								} else {
									this.changeHighLightBranchs(this.branchData[0])
								}
								this.emptyFormData()
								break;
							case "変更":
							case "Edit":
								this.branchData.splice(
									this.formData.val_ind,1,
										{
											branch_code: 		this.formData.val_code, 
											branch_name: 		this.formData.val_name ,
											branch_name_kana: 	this.formData.val_kana ,
											end: 				this.formData.val_end ,
											ind:				this.formData.val_ind
										},
								)
								this.$set(this.branchData[this.formData.val_ind], "ind", this.formData.val_ind)
								this.emptyFormData()
								break;
							default:
								alert("Default from submit form");	
								break;
						}	
					}
						break;
					//////////////////////////////////////////////
					default:
						alert("this section does not exist")
						break;
				}
				this.closeForm()
			}

			emptyFormData() {
				this.formData.val_code = ""
				this.formData.val_name = ""
				this.formData.val_kana = ""
				this.formData.val_end = false
				this.formData.val_ind = 0
			}

			// check if prod Is Empty
			isBankEmpty() {
				if (this.bankItems.length == 0){
					return true;
				} 
				else {
					return false;
				}
			}

			// check if sub-prod Is Empty
			isBranchEmpty() {
				if (this.branchData.length == 0){
					return true;
				} 
				else {
					return false;
				}
			}

			changeBankBranch(val) {
				this.branchData = []
				switch(val) {
					case "0001":
						this.branchData.push(
								{branch_code: '0001',branch_name:'確定拠出年金支店',branch_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: true},
								{branch_code: '0002',branch_name:'確定拠出年金支店',branch_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: true},
								{branch_code: '0003',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
						)
						
						break;
					case "0002":
						this.branchData.push(
								{branch_code: '0004',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0005',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0006',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0007',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0008',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0010',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0020',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
						)
						break;
					case "0003":
						this.branchData.push(
								{branch_code: '0040',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0050',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0100',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0200',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0400',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0500',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0600',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0700',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '0800',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
						)
						break;
					case "0004":
						this.branchData.push(
								{branch_code: '2100',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '3100',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '4100',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '5100',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '6000',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '7800',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
						)
						break;
					case "":
						this.branchData.push()
						break;
					default:
						this.branchData.push(
								{branch_code: '1000',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
								{branch_code: '2000',branch_name:'確定拠出年金支店',branch_name_kana: 'シバ' ,end: true},
						)
						break;
				}
				this.setBranchIndex()
				this.currBranch = {branch_code: '' ,branch_name: '' ,branch_name_kana:'', end: false}
				this.currBranch = this.branchData[0]
				this.$set(this.branchData[0], "_rowVariant", "info")
			}

			setBranchIndex() {
				var i = 0;
				this.branchData.forEach(element => {
					this.$set(element, "ind", i)
					i++
				});
			}


			// Footer Fn key process
			fnClickOrKeyDown(val){
				this.processFooterValues(val)
			}

			processFooterValues(val) {
				switch(val.name) {
					// perform delete and create operation
					case "新規":
					case "New":
					case "削除":
					case "Delete":
						this.buttonProcess(val)
					break;
					
					// perform cancel operation
					case "取消":
					case "Cancel":
						this.closeForm()
					break;

					// perform date and list modals
					case "日付":
					case "Date":
					case "リスト":
					case "List":
						this.openModalPr(val)
					break;

					// to to main menu
					case "メニュ":
					case "Menu":
						this.goToMenu()
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

			// Open modal
			modalPrShowing = false
			modalType = ""
			openModalPr(val) {
				this.modalPrShowing = true
				this.modalType = val.name
			}

			// Close modal
			closeModalPr(val){
				this.modalPrShowing = false
			}

			goToMenu() {
				this["$router"].push('/mymenu')
			}

			// extras
			filterBankIsEndClick() {
				alert('filterBankIsEndClick')
			}
			filterBranchIsEndClick() {
				alert('filterBranchIsEndClick')
			}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
</style>