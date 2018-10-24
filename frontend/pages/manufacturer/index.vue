<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
				<!-- <p>
				currData: {{currData}} <br>
				section_name: {{section_name}} <br>
				formShow: {{formShow}} <br>
				processName: {{processName}} <br>
				processAction: {{processAction}} <br>
				formIsDisabled: {{formIsDisabled}} <br>
				formData: {{formData}}<br>
				</p> -->
				<!-- <p>
					<pre>
						{{data}}
					</pre>
				</p> -->
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="3" md="6" sm="12">
				<section class="mb-2 field-input">
					<b-row>
						<b-col xl="8">
							<b-form-input v-model="filter" :placeholder="filter_lang" size="sm"/>
						</b-col>
						<b-col xl="3">
							<b-form-group horizontal
							            :label-cols="6"
							            label-size="sm"
							            :label="end_lang"
							            label-for="input_sm"
							            class="m-0">
								<b-form-checkbox id="end_page"
												 v-model="filter2">
							    </b-form-checkbox>
							</b-form-group>
						</b-col>
					</b-row>
				</section>
				<section class="field-table">
					<b-table striped
					         bordered
				         	 small
					         :items="data"
					         :fields="field"
					         hover
					         :filter="filter"
							 @row-clicked="changeHighLightData"
					         @row-dblclicked="processEventValues({_eventName:'edit',success:'true'})">
					         <template slot="end" slot-scope="data">
					         	{{data.value == true ? '終了' : ' '}}
					         </template>
					</b-table>	
				</section>
			</b-col>
			<b-col xl="3" md="6" sm="12">
				<section class="field-form">
					<transition name="fade">
							<b-card bg-variant="light" v-if="formShow">
								<b-col class="p-0">
									<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/> {{name_lang}} ＞ {{processName}}</span></h5>
								</b-col>
								<c_form 
									:formdata="formData" 
									:formIsDisabled="formIsDisabled"
									:processName="processName" 
									@clickCancel="closeForm" 
									@submit="submit"
									@changeCD="formData.code = $event"
									@changeName="formData.name = $event"
									@changeEndVal="formData.end = $event"
								></c_form>
							</b-card>
					</transition>
				</section>
			</b-col>
		</b-row>
		<c_modal
			id="confirm"
			:processName="processName" 
			:processAction="processAction"
			@submit="submitForm" 
		></c_modal>
		<c_modal_pr
			:modalPrShowing="modalPrShowing"
			:modalType="modalType"
			:section_name="section_name"
			@closeModalPr="closeModalPr"
		></c_modal_pr>
		<c_footer class="content-footer" 
			@eventTriggered="buttonprocess" 
			:events="UIEvents"
		></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator'

	import c_form from '@/components/forms/manufacturer'
	import c_modal from '@/components/modal'
	import util from '@/assets/js/util.js'
	import c_modal_pr from '@/components/modal_pr'

	import {UIEvent} from "@/assets/ts/UIEvent.ts"
	import {Keys} from "@/assets/ts/keys.ts"
	import c_footer from '@/components/footer.vue'
	
	@Component({
		components:{
			c_footer,
			c_form,
			c_modal,
			c_modal_pr,
		},
	})
	export default class Manufacturer extends Vue {

			formShow = false
			filter = null
			filter2 = false
			submittedData = null

			// value holders for last clicked row in prod/sub-rpod
			currData = {code: '', name: '', ind: 0, end: true }

			// change lang
			title_lang = ""
			filter_lang = ""
			end_lang = ""
			name_lang = ""
			edit_lang = ""

			// form value holders
			section_name = ""
			section_action = ""
			processName = ""
			processAction = ""
			formIsDisabled = false
			formData = {code: '', name: '', ind: 0, end: true }

			// test
			manufacturer = null

			// footer key values
			UIEvents:Array<UIEvent> = [ 
				new UIEvent(Keys.F2,'取消',true),
				new UIEvent(Keys.F3,'削除',true),
				new UIEvent(Keys.F4,'新規登録',true),
				new UIEvent(Keys.F6,'日付',true),
				new UIEvent(Keys.F8,'リスト',true),
				new UIEvent(Keys.F12,'メニュ',true),
			]

			// datas will be from master maintenance
			fnFunctions = [ 
				{key: 113, name:'',	action:'cancel'}, // F2 
				{key: 115, name:'',	action:'register'}, // F4 
				{key: 117, name:'',	action:'showDate'}, // F6 
				{key: 119, name:'', action:'showList'}, // F8 
				{key: 123, name:'', action:'goToMenu'}, // F12 
			]

			field = [
				{key: 'code', label: 'CD',class: 'text-center table-col-width-50',sortable : 'true'},
				{key: 'name', label: 'メーカー名',sortable : 'true'},
				{key: 'end', label: '終了',class:'table-col-width-50 text-center'},
			]

			data = [
				{code: '01',name: 'DAIKO',end: true},
				{code: '02',name: 'ENDO',end: true},
				{code: '03',name: 'PANASONIC',end: false},
				{code: '04',name: 'MAXRAY',end: false},
				{code: '05',name: 'KOIZUMI',end: false},
				{code: '06',name: 'YAMAGIWA',end: false},
				{code: '07',name: 'YAMADA',end: false},
				{code: '08',name: 'USHIO',end: false},
				{code: '09',name: 'TOSHIBA',end: false},
				{code: '10',name: 'IAWASAKI',end: false},
				{code: '11',name: 'ODELIC',end: false},
				{code: '12',name: 'OSRAM',end: false},
				{code: '13',name: 'HITACHI',end: false},
				{code: '14',name: 'SANYO',end: false},
				{code: '15',name: 'NEC',end: false},
				{code: '16',name: 'TOKI',end: false},
				{code: '17',name: 'TOZAI',end: false},
				{code: '18',name: 'PHOENIX',end: false},
				{code: '19',name: 'SHARP',end: false},
				{code: '20',name: 'PHILIPS',end: false},
				{code: '21',name: 'BOSE',end: false},
			]

			created() {
				this.manufacturer = this.$t("Auth.pages.manufacturer")
				this.setPageLanguage()
				this.setTableLanguage()
				this.setFooterValueAndLanguage()
				this.setTableInitialValues()
			}

			mounted() {
				console.log((this.manufacturer))
			}

			setPageLanguage() { // values can be found on fujiko-sm/frontend/locales/ en.json-ja.json
				this.title_lang = this.manufacturer.title
				this.filter_lang = this.manufacturer.filter
				this.end_lang = this.manufacturer.end
				this.name_lang = this.manufacturer.name
				this.edit_lang = this.manufacturer.edit
			}

			setTableLanguage() {
				this.section_name = this.name_lang
				this.$set(this.field[1], "label", this.name_lang)
				this.$set(this.field[2], "label", this.end_lang)
			}

			setFooterValueAndLanguage() { // values can be found on fujiko-sm/frontend/locales/ en.json-ja.json
				this.$set(this.fnFunctions[0], "name", this.$t("Auth.pages.manufacturer.cancel").toString())
				this.$set(this.fnFunctions[1], "name", this.$t("Auth.pages.manufacturer.register").toString())
				this.$set(this.fnFunctions[2], "name", this.$t("Auth.pages.manufacturer.date").toString())
				this.$set(this.fnFunctions[3], "name", this.$t("Auth.pages.manufacturer.list").toString())
				this.$set(this.fnFunctions[4], "name", this.$t("Auth.pages.manufacturer.menu").toString())
			}

			setTableInitialValues() {
				this.setMakerIndex() 
				this.changeHighLightData(this.data[0])// default row highlight for products	
			}

			setMakerIndex() {
				var i = 0;
				this.data.forEach(element => {
					this.$set(this.data[i], "ind", i)
					i++
				});
			}

			changeHighLightData(record) {
				this.chooseRowToHighLightInData(record)
				this.populateCurrData(record)
				this.setFormContent(record)
			}

			chooseRowToHighLightInData(record) {
				this.data.forEach(element => {
					if(record.code == element.code) {
						this.$set(element, "_rowVariant", "info")
					} else {
						this.$set(element, "_rowVariant", "")

					}
				});
			}

			populateCurrData(record) {
				this.currData = record
			}
			
			setFormContent(record) {
				this.formData.code = record.code
				this.formData.name = record.name
				this.formData.ind = record.ind
				this.formData.end = record.end
			}

			buttonprocess(obj) {
				// alert(JSON.stringify(obj))
				this.processEventValues(obj) 
			}

			//Will call another footer functions
			processEventValues(obj) {
				// alert(JSON.stringify(obj._eventName))
				switch(obj._eventName) {
					case "cancel":
					case "取消":
						this.closeForm()
						break;
					case "edit":
						this.processName = this.edit_lang
						this.processAction = "edit"
						this.formIsDisabled = false
						this.formShow = true
						break;
					case "register":
					case "新規登録":
						this.processName = this.fnFunctions[1].name
						this.processAction = this.fnFunctions[1].action
						this.clearFormData()
						this.formIsDisabled = false
						this.formShow = true
						break;
					case "showDate":
					case "日付":
						this.openModalPr(this.fnFunctions[2])
						break;					
					case "showList":
					case "リスト":
						this.openModalPr(this.fnFunctions[3])
						break;
					case "goToMenu":
					case "メニュ":
						this.goToMenu()
						break;
					case "":
						// do nothing
						break;
					default:
						alert("Default from processEventValues");	
						break;
				}		
			}

			clearFormData() {
				this.formData.code = ""
				this.formData.name = ""
				this.formData.ind = 0
				this.formData.end = false
			}

			closeForm() {
				this.formShow = false
				this.formIsDisabled = false
			}

			//To be change
			submit(value){
				this.submittedData = value
			}

			confirmSubmit(){
				this.formShow = false
				if(this.processName == "変更"){
					this.$set(this.formData,"code",this.submittedData.code)
					this.$set(this.formData,"name",this.submittedData.name)
					this.$set(this.formData,"end",this.submittedData.end)
				}else{
					this.data.push(this.submittedData)
				}
				this.processName = ''
			}

			isDataEmpty() {
				if (this.data.length == 0){
					return true;
				} 
				else {
					return false;
				}
			}
			
			//submit form 
			submitForm(val) {
				// alert("subit val before process: " + JSON.stringify(val))
				switch(val) {
					case "register":
						this.data.push(
							{
								code: this.formData.code,
								name: this.formData.name,
								end: this.formData.end
							},
						)
						this.$set(this.data[this.data.length-1], "ind", this.data.length-1)
						this.clearFormData()
						break;
					case "edit":
						this.data.splice(
							this.formData.ind,1,
								{ 
									code: this.formData.code, 
									name: this.formData.name,
									end: this.formData.end
								}
						)
						this.$set(this.data[this.formData.ind], "ind", this.formData.ind)
						this.clearFormData()
						break;
					default:
						alert("Default from submit form");	
						break;
				}
				this.closeForm()
			}

			// modal_pr values
			modalPrShowing = false
			modalType= ""
			openModalPr(val) {
				this.modalPrShowing = true
				this.modalType = val.name
			}

			closeModalPr(val) {
				this.modalPrShowing = false
			}

			goToMenu() {
				this["$router"].push('/mymenu')
			}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
	
</style>
<style lang="scss">
	.custom-control{
		min-height: 0.58rem;
	}
</style>