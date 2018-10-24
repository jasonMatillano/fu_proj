<template>
    <b-container fluid id="wg-master" class="px-0">
        <b-row class="content-title m-0 py-2">
            <b-col>
                <h5 class="m-0">{{title_lang}} </h5>
				<!-- <p> 
					currProd: {{currProd}} prodLen: {{productItems.length}}<br>
					currSubProd: {{currSubProd}} subProdLen: {{subProductItems.length}} <br>
					section_name: {{section_name}} <br>
					section_action: {{section_name}} <br> 
					processName: {{processName}} <br>
					processAction: {{processName}} <br> 
					formData => {{ formData }}				
				</p>
				<p>
					modalPrShowing: {{modalPrShowing}} <br>
					modalType: {{modalType}}
				</p> -->
            </b-col>
        </b-row>
        <b-row class="content-body m-0">
			<!-- Products Section-->
			<b-col xl="4" @click="prodFirstClickDefault()">
				<section class="mb-2 field-input">
					<b-col md="8" sm="12" class="p-0">
						<b-row>
							<b-col xl="8">
								<b-form-input 
									v-model="filterProductSearch" 
									:placeholder="filter_lang" 
									size="sm"/>
							</b-col>
							<b-col xl="4">
								<b-form-group 
									horizontal 
									:label-cols="6" 
									label-size="sm" 
									:label="end_lang" 
									label-for="input_sm" 
									class="m-0"
									>
									<b-form-checkbox 
										id="end_page"
										v-model="filterProdIsEnd"
										@input="endProdClick()"
									></b-form-checkbox>
								</b-form-group>
							</b-col>
						</b-row>
					</b-col>
				</section>
				<section class="field-table">
					<b-table 
						striped 
						bordered 
						small 
						:items="productItems" 
						:fields="productFields" 
						hover 
						:filter="filterProductSearch" 
						@row-clicked="changeHighLightProducts"
						@row-dblclicked="buttonProcess({name:edit_lang})"
					>
						<template slot="end" slot-scope="data">
							{{data.value == true ? '終了' : ' '}}
						</template>
					</b-table>
				</section>
			</b-col>
			<!-- /Products Section-->

			<!-- Form Section-->
				<b-col xl="4">
					<transition name="fade">
						<section class="field-form"  v-if="formShow">
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
									@changeEndVal="formData.val_end = $event"
								></c_form>
							</b-card>
						</section>
					</transition>
				</b-col>
			<!-- /Form Section-->

			<!-- SubProducts Section-->
			<b-col xl="4" @click="subProdFirstClickDefault()">
				<section class="mb-2 field-input">
					<b-col md="8" sm="12" class="p-0">
						<b-row>
							<b-col xl="8">
								<b-form-input 
									v-model="filterSubProdSearch" 
									:placeholder="filter_lang" 
									size="sm"
									/>
							</b-col>
							<b-col xl="4">
								<b-form-group 
									horizontal
									:label-cols="6"
									label-size="sm"
									label-for="input_sm"
									class="m-0"
									:label="end_lang"
									>
									<b-form-checkbox 
										id="end_page_2"
										v-model="filterSubProdIsEnd">
									</b-form-checkbox>
								</b-form-group>
							</b-col>
						</b-row>
					</b-col>
				</section>
				<section class="field-table">
					<b-table 
					striped 
					bordered 
					small 
					:fields="subProductFields" 
					:items="subProductItems" 
					hover 
					:filter="filterSubProdSearch" 
					@row-clicked="changeHighLightSubProducts"
					@row-dblclicked="buttonProcess({name:edit_lang})"
					>
					<template slot="end" slot-scope="data">
					 	{{data.value == true ? '終了' : ' '}}
					</template>
					</b-table>
				</section>
			</b-col>
			<!-- /SubProducts Section-->
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

	import c_form from '@/components/forms/product'
	import c_modal_pr from '@/components/modal_pr'
	import c_footer from '@/components/footer_pr'

	@Component({
		components:{
			c_form,
			c_modal_pr,
			c_footer,
		},
	})

	export default class Product extends Vue {


			// page static values
			title_lang = ""
			filter_lang = ""
			end_lang = ""
			edit_lang = ""
			prod_lang = ""
			subprod_lang = ""

			// parameters for product and subprod table
			productFields = [
							{key: 'prod_code', label: 'CD', class:"text-center table-col-width-50",sortable: true},
							{key: 'prod_name', label: '', class:"w-50"},
							{key: 'end', label: '', class:'table-col-width-50 text-center'},
			]

			subProductFields = [
							{key: 'subProd_code', label: 'CD',class:"text-center table-col-width-50",sortable: true},
							{key: 'subProd_name', label: '',class:"w-50"},
							{key: 'end', label: '', class:'table-col-width-50 text-center'},
			]

			productItems = [
							{prod_code: '0001',prod_name: 'バルブ'			,prod_name_kana:'ﾐｽﾞﾎｺｰﾎﾟﾚｰﾄ',end: true }, // bulb
							{prod_code: '0002',prod_name: '扇風機'			,prod_name_kana:'ﾐｽﾞﾎｺｰﾎﾟﾚｰﾄ',end: false}, // electric fan
							{prod_code: '0003',prod_name: 'しょっきあらいき' ,prod_name_kana:'ラクテン'	,end: true}, // dish washer
							{prod_code: '0004',prod_name: 'オーブン'		,prod_name_kana:'ラクテン'	,end: true}, // oven
							{prod_code: '0005',prod_name: 'ストーブ'		,prod_name_kana:'ラクテン'	,end: false}, // stove
							{prod_code: '0006',prod_name: 'やかん'			,prod_name_kana:'ラクテン'	,end: false}, // kettle
							{prod_code: '0007',prod_name: 'トースター'		,prod_name_kana:'ラクテン'	,end: true}, // toaster
							{prod_code: '0008',prod_name: 'れいぞうこ'		,prod_name_kana:'ラクテン'	,end: false}, // fridge
							{prod_code: '0009',prod_name: 'れいとうしつ'	,prod_name_kana:'ラクテン'	,end: true}, // freezer
							{prod_code: '0010',prod_name: 'でんしレンジ'	,prod_name_kana:'ラクテン'	,end: true}, // microwave
							{prod_code: '0011',prod_name: 'ミキサー'		,prod_name_kana:'ラクテン'	,end: false}, // mixer
							{prod_code: '0012',prod_name: 'めざましどけい'	,prod_name_kana:'ラクテン'	,end: true}, // alarn clock
							{prod_code: '0013',prod_name: 'せんたくき'		,prod_name_kana:'ラクテン'	,end: false}, // washing machine
							{prod_code: '0014',prod_name: 'ステレオ'		,prod_name_kana:'ラクテン'	,end: false}, // stereo
							{prod_code: '0015',prod_name: 'コンピューター'	,prod_name_kana:'ラクテン'	,end: false}, // computer
							{prod_code: '0016',prod_name: 'でんわ'			,prod_name_kana:'ラクテン'	,end: true}, // phone
							{prod_code: '0017',prod_name: 'アイロン'		,prod_name_kana:'ラクテン'	,end: true}, // aircon
			]

			subProductItems = []

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
			filterProductSearch = ""
			filterSubProdSearch = ""
			
			// 終了/End filter
			filterProdIsEnd = false
			filterSubProdIsEnd = false

			// value holders for last clicked row in prod/sub-rpod
			currProd 	= {prod_code: '', prod_name: '', prod_name_kana:'', end: true }
			currSubProd = {subProd_code: '',subProd_name:'',subProd_name_kana: '' ,end: true}

			// form value holders
			section_name = ""
			formIsDisabled = false
			formData = {val_code: '', val_name: '', val_kana:'', val_end: false , val_ind: 0}

			// arrows
			arrowPosition = ""
			tableArrow = ""

			created() {
				this.setPageLanguage()
				this.setTableFieldsLang()
				this.setFooterValueAndLanguage()
				this.setValueForFnKeys()
				this.assignVariantAndDisableStatusForFnKeys()
				this.setTableInitialValues()
			}
			
			setPageLanguage() {
				this.title_lang 	= this.$t("Auth.pages.product.title").toString()
				this.filter_lang 	= this.$t("Auth.pages.product.filter").toString()
				this.edit_lang 		= this.$t("Auth.pages.product.edit").toString()
				this.prod_lang 		= this.$t("Auth.pages.product.prod").toString()
				this.subprod_lang 	= this.$t("Auth.pages.product.subprod").toString()
				this.end_lang 		= this.$t("Auth.pages.product.end").toString()
			}
			
			setTableFieldsLang() {
				this.$set(this.productFields[1], "label", this.prod_lang)
				this.$set(this.productFields[2], "label", this.end_lang)
				this.$set(this.subProductFields[1], "label", this.subprod_lang)	
				this.$set(this.subProductFields[2], "label", this.end_lang)
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

			assignVariantAndDisableStatusForFnKeys(){	
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
				this.setProdIndex() 
				this.changeProdSubItems(0)
				this.changeHighLightProducts(this.productItems[0])// default row highlight for products	
			}

			setProdIndex() {
				var i = 0;
				this.productItems.forEach(element => {
					this.$set(this.productItems[i], "ind", i)
					i++
				});
			}

			changeHighLightProducts(record) {
				this.changeArrowToProd()
				this.chooseRowToHighLightInProd(record)
				this.populateCurrentProd(record)
				this.setVarForFormContentProd(record)
				this.changeFormContent()
				this.changeProdSubItems(record.prod_code)// change sub-items
			}

			changeArrowToProd() {
				this.arrowPosition = "text-left"
				this.tableArrow = "arrow-alt-circle-left"
			}

			chooseRowToHighLightInProd(record) {
				this.productItems.forEach(element => {
					if(record.prod_code == element.prod_code) {
						this.$set(element, "_rowVariant", "info")
					} else {
						this.$set(element, "_rowVariant", "")

					}
				});
			}

			populateCurrentProd(record) {
				this.currProd =  {prod_code: '', prod_name: '', prod_name_kana:'', end: true }
				this.currProd = record
			}

			setVarForFormContentProd(record){
				this.section_name = this.prod_lang
				this.formData.val_code = record.prod_code
				this.formData.val_name = record.prod_name
				// this.formData.val_kana = record.prod_name_kana
				this.formData.val_ind = record.ind
				this.formData.val_end = record.end
			}

			changeHighLightSubProducts(record) {
				this.changeArrowToSubProd()
				this.chooseRowToHighLightInSubProd(record)
				this.populateCurrSubProd(record)
				this.setVarForFormContentSubProd(record)
				this.changeFormContent()
			}

			changeArrowToSubProd() {
				this.arrowPosition = "text-right"
				this.tableArrow = "arrow-alt-circle-right"
			}

			chooseRowToHighLightInSubProd(record) {
				this.subProductItems.forEach(element => {
					if(record.subProd_code == element.subProd_code) {
						this.$set(element, "_rowVariant", "info")
					} else {
						this.$set(element, "_rowVariant", "")
					}
				});	
			}

			populateCurrSubProd(record) {
				this.currSubProd = null
				this.currSubProd = record
			}

			setVarForFormContentSubProd(recordSub){
				this.section_name = this.subprod_lang
				this.formData.val_code = recordSub.subProd_code
				this.formData.val_name = recordSub.subProd_name
				this.formData.val_kana = recordSub.subProd_name_kana
				this.formData.val_ind = recordSub.ind
				this.formData.val_end = recordSub.end
			}

			// prodFirstClick
			isFirstProd = 0
			prodFirstClickDefault() {
				if (this.isFirstProd==0){
					this.changeHighLightProducts(this.currProd)
				} else {
					// do nothing
				}
				this.isFirstSub = 0
				this.isFirstProd++
			}

			// subProdFirstClick
			isFirstSub = 0
			subProdFirstClickDefault() {
				if (this.isFirstSub==0){
					this.changeHighLightSubProducts(this.currSubProd)
				} else {
					// do nothing
				}
				this.isFirstProd = 0
				this.isFirstSub++
			}

			// Fn key process
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

			// show form 
			processName = ''
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
					case "大分類名":
					case "Product":
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
									if(this.isProdEmpty() == true) {
										alert('Prod is empty')
										this.formShow = false 
									} else {
										this.formData.val_code = this.currProd.prod_code
										this.formData.val_name = this.currProd.prod_name
										this.formData.val_kana = this.currProd.prod_name_kana
										this.formData.val_end = this.currProd.end
										this.formIsDisabled = true
									}	
									break;
								case "変更":
								case "Edit":
									this.formData.val_code = this.currProd.prod_code
									this.formData.val_name = this.currProd.prod_name
									this.formData.val_kana =  this.currProd.prod_name_kana
									this.formData.val_end = this.currProd.end
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
					case "中分類名":
					case "Class":
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
									if(this.isSubProdEmpty() == true) {
										alert('SubProd is empty')
										this.formShow = false 
									} else {
										this.formData.val_code = this.currSubProd.subProd_code
										this.formData.val_name = this.currSubProd.subProd_name
										this.formData.val_kana = this.currSubProd.subProd_name_kana
										this.formData.val_end = this.currSubProd.end
										this.formIsDisabled = true
									}
									break;
								case "変更":
								case "Edit":
									this.formData.val_code = this.currSubProd.subProd_code
									this.formData.val_name = this.currSubProd.subProd_name
									this.formData.val_kana = this.currSubProd.subProd_name_kana
									this.formData.val_end = this.currSubProd.end
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
					default:
						// do nothing
						break;
				}
			}

			// check if prod Is Empty
			isProdEmpty() {
				if (this.productItems.length == 0){
					return true;
				} 
				else {
					return false;
				}
			}

			// check if sub-prod Is Empty
			isSubProdEmpty() {
				if (this.subProductItems.length == 0){
					return true;
				} 
				else {
					return false;
				}
			}

			//submit form 
			submitForm(val) {
				alert(JSON.stringify(this.section_name))
				alert(JSON.stringify(val))
				switch(this.section_name) {
					case "大分類名":
					case "Product":
					{
						switch(val) {
							case "新規":
							case "New":
								this.productItems.push(
									{
										prod_code: 		this.formData.val_code,
										prod_name: 		this.formData.val_name ,
										prod_name_kana: this.formData.val_kana ,
										end: 			this.formData.val_end
									},
								)
								this.$set(this.productItems[this.productItems.length-1], "ind", this.productItems.length-1)
								this.emptyFormData()
								break;
							case "削除":
							case "Delete":
								this.productItems.splice(this.formData.val_ind,1)
								this.setProdIndex() // reset product index
								if(this.productItems[0] == undefined) {
									// if product is empty do nothing
								} else {
									this.changeHighLightProducts(this.productItems[0])
								}
								this.emptyFormData()
								break;
							case "変更":
							case "Edit":
								alert(JSON.stringify(this.formData))
								this.productItems.splice(
									this.formData.val_ind,1,
										{ 
											prod_code: 		this.formData.val_code, 
											prod_name: 		this.formData.val_name,
											prod_name_kana:	this.formData.val_kana,
											end: 			this.formData.val_end
										}
								)
								this.$set(this.productItems[this.formData.val_ind], "ind", this.formData.val_ind)
								this.emptyFormData()
								break;
							default:
								alert("Default from submit form");	
								break;
						}
					}
					break;
					/////////////////////////////////////////
					case "中分類名":
					case "Class":
					{
						switch(val) {
							case "新規":
							case "New":
								this.subProductItems.push(
									{	
										subProd_code: 		this.formData.val_code, 
										subProd_name: 		this.formData.val_name,
										subProd_name_kana: 	this.formData.val_kana,
										end: 				this.formData.val_end,
										ind:				this.formData.val_ind
									}
								)
								this.$set(this.subProductItems[this.subProductItems.length-1], "ind", this.subProductItems.length-1)
								this.emptyFormData()
								break;
							case "削除":
							case "Delete":
								this.subProductItems.splice(this.formData.val_ind,1)
								this.setSubProdIndex() // reset subprod index
								if(this.subProductItems[0] == undefined) {
									// if sub-product is empty do nothing
								} else {
									this.changeHighLightSubProducts(this.subProductItems[0])
								}
								this.emptyFormData()
								break;
							case "変更":
							case "Edit":
								this.subProductItems.splice(
									this.formData.val_ind,1,
										{
											subProd_code: 		this.formData.val_code, 
											subProd_name: 		this.formData.val_name ,
											subProd_name_kana: 	this.formData.val_kana ,
											end: 				this.formData.val_end ,
											ind:				this.formData.val_ind
										}
								)
								this.$set(this.productItems[this.formData.val_ind], "ind", this.formData.val_ind)
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
				this.formData.val_end = true
				this.formData.val_ind = 0
			}
			
			// change sub items when prod row-click
			changeProdSubItems(val) {
				this.subProductItems = []
				switch(val) {
					case "0001":
						this.subProductItems.push(
							{subProd_code: '0001',subProd_name:'あ定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: false},
							{subProd_code: '0002',subProd_name:'た定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: true},
							{subProd_code: '0003',subProd_name:'が定拠出年金支店',subProd_name_kana: 'シバ' ,end: false},
							{subProd_code: '0004',subProd_name:'か定拠出年金支店',subProd_name_kana: 'シバ' ,end: false},
						)
						
						break;
					case "0002":
						this.subProductItems.push(
							{subProd_code: '0001',subProd_name:'あ定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: true},
							{subProd_code: '0002',subProd_name:'た定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: false},
							{subProd_code: '0003',subProd_name:'が定拠出年金支店',subProd_name_kana: 'シバ' ,end: true},
							{subProd_code: '0004',subProd_name:'か定拠出年金支店',subProd_name_kana: 'シバ' ,end: true},
							{subProd_code: '0005',subProd_name:'ま定拠出年金支店',subProd_name_kana: 'シバ' ,end: false},
							{subProd_code: '0006',subProd_name:'な定拠出年金支店',subProd_name_kana: 'シバ' ,end: true},
						)
						break;
					case "0003":
						this.subProductItems.push(
							{subProd_code: '0001',subProd_name:'あ定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: false},
							{subProd_code: '0002',subProd_name:'た定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: true},
							{subProd_code: '0003',subProd_name:'が定拠出年金支店',subProd_name_kana: 'シバ' ,end: false},
							{subProd_code: '0004',subProd_name:'か定拠出年金支店',subProd_name_kana: 'シバ' ,end: false},
							{subProd_code: '0005',subProd_name:'ま定拠出年金支店',subProd_name_kana: 'シバ' ,end: true},
							{subProd_code: '0006',subProd_name:'な定拠出年金支店',subProd_name_kana: 'シバ' ,end: false},
							{subProd_code: '0007',subProd_name:'は定拠出年金支店',subProd_name_kana: 'シバ' ,end: true},
						)
						break;
					case "0004":
						this.subProductItems.push(
							{subProd_code: '0001',subProd_name:'あ定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: true},
							{subProd_code: '0002',subProd_name:'た定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: false},
							{subProd_code: '0003',subProd_name:'が定拠出年金支店',subProd_name_kana: 'シバ' ,end: true},
							{subProd_code: '0004',subProd_name:'か定拠出年金支店',subProd_name_kana: 'シバ' ,end: false},
							{subProd_code: '0005',subProd_name:'ま定拠出年金支店',subProd_name_kana: 'シバ' ,end: false},
							{subProd_code: '0006',subProd_name:'な定拠出年金支店',subProd_name_kana: 'シバ' ,end: false},
							{subProd_code: '0007',subProd_name:'は定拠出年金支店',subProd_name_kana: 'シバ' ,end: true},
							{subProd_code: '0008',subProd_name:'確ほ拠出年金支店',subProd_name_kana: 'シバ' ,end: true},
						)
						break;
					case "":
						this.subProductItems.push()
						break;
					default:
						this.subProductItems.push(
							{subProd_code: '0001',subProd_name:'あ定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: true},
							{subProd_code: '0002',subProd_name:'た定拠出年金支店',subProd_name_kana: 'ｶｸﾃｲｷｮｼｭﾂﾈﾝｷﾝ' ,end: true},
						)
						break;
				}
				this.setSubProdIndex()
				this.currSubProd = null
				this.currSubProd = this.subProductItems[0]
				this.$set(this.subProductItems[0], "_rowVariant", "info")
			}

			setSubProdIndex() {
				var i = 0;
				this.subProductItems.forEach(element => {
					this.$set(this.subProductItems[i], "ind", i)
					i++
				});
			}

			// modal_pr values and methods
			modalPrShowing = false
			modalType= ""
			openModalPr(val) {
				this.modalPrShowing = true
				this.modalType = val.name
			}

			closeModalPr(val){
				this.modalPrShowing = false
			}

			// redirect to menu
			goToMenu() {
				this["$router"].push('/mymenu')
			}

			// endProdClick
			endProdClick() {
				alert("checkbox cliked")
			}
			
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
</style>