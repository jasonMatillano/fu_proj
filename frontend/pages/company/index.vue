<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="7" md="12">
				<section class="field-input mb-2">
					<b-col md="4" sm="12" class="p-0">
						<b-form-input v-model="filter" :placeholder="filter_lang" size="sm"/>
					</b-col>
				</section>
				<section class="field-table">
					<accountTable :data="data" :filter="filter" @dblClickTable="dblClickTable">
					</accountTable>
				</section>
			</b-col>
			<b-col xl="4" md="6" sm="12">
				<section class="field-form">
					<transition name="fade">
						<b-card bg-variant="light" v-if="formShow">
							<b-col sm="12" class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/></span>自社口座　＞　{{processName}}</h5>
							</b-col>
							<c_form :formdata="prevData" :processName="processName" @clickCancel="clickCancel" @submit="submit"></c_form>
						</b-card>
					</transition>
				</section>
			</b-col>
		</b-row>
		<c_modal 
			@submit="confirmSubmit" 
			id="confirm"
		></c_modal>
		<c_modal_pr
			:modalPrShowing="modalPrShowing"
			:modalType="modalType"
			:section_name="section_name"
			@closeModalPr="closeModalPr"
		></c_modal_pr>
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
	import c_form from '@/components/forms/company'
	import c_modal from '@/components/modal'
	import c_modal_pr from '@/components/modal_pr'
	import {UIEvent} from "@/assets/ts/UIEvent.ts"
	import {Keys} from "@/assets/ts/keys.ts"

	@Component({
		components:{
			c_footer,
			accountTable,
			c_form,
			c_modal,
			c_modal_pr
		},
	})
	export default class Account extends Vue{

		formShow = false
		prevData = {}
		processName = ''
		filter = ''
		submittedData = null

		// change lang 
		title_lang = ""
		filter_lang = ""

		// form value holders
		section_name = "自社口座"

		UIEvents:Array<UIEvent>=[ 
		      new UIEvent(Keys.F2,'取消',true),
		      new UIEvent(Keys.F3,'削除',true),
		      new UIEvent(Keys.F4,'行作成',true),
		      new UIEvent(Keys.F6,'日付',true),
		      new UIEvent(Keys.F8,'リスト',true),
		      new UIEvent(Keys.F12,'メニュ',true),
		]     

		created() {
			this.title_lang = this.$t("Auth.pages.company.title").toString()
			this.filter_lang = this.$t("Auth.pages.product.filter").toString()
		}

		data = [
			{no: '1', bank: {name: 'みずは銀行', code: '0001'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ1'},
			{no: '2', bank: {name: 'みずは銀行', code: '0002'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ2'},
			{no: '3', bank: {name: 'みずは銀行', code: '0003'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ3'},
			{no: '4', bank: {name: 'みずは銀行', code: '0004'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ4'},
			{no: '5', bank: {name: 'みずは銀行', code: '0005'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ5'},
			{no: '6', bank: {name: 'みずは銀行', code: '0006'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ6'},
			{no: '7', bank: {name: 'みずは銀行', code: '0007'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ7'},
			{no: '8', bank: {name: 'みずは銀行', code: '0008'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ8'},
			{no: '9', bank: {name: 'みずは銀行', code: '0009'}, branch:{name: '今里支店',code: '480'}, deposit_type: '1　普　通', account_number: '01378390', company_number: '9890010915',company_kana: 'ｶ)ﾌｼﾞｺｰ9'},
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

		clickButton(item){
			if(item.index == '1'){
				//to do
			}else if(item.index == '3'){
				this.formShow = true
				this.processName = item.name
				this.reset_color()
				this.prevData = {}
			}else if(item.index == '2'){
				this.clickCancel()
			}
			
		}

		dblClickTable(item){
			this.reset_color()
			this.formShow = true
			this.processName = "変更"
			this.prevData = item
			this.set_color(item)
		}

		//all called functions
		reset_color(){
			if(this.prevData !== null){
				Vue.set(this.prevData,"_rowVariant","")
			}
		}
		set_color(item){
			if(this.processName === "変更" || this.processName === ''){
				this.prevData = item
				Vue.set(item, "_rowVariant", "info");
			}
		}
		clickCancel(){
			this.reset_color()
			this.formShow = false
			this.processName = ''
			this.prevData = {}
		}

		//to be deleted
		submit(value){
			this.submittedData = value
		}

		confirmSubmit(){
			this.formShow = false
			if(this.processName == "変更"){
				this.$set(this.prevData,"deposit_type",this.submittedData.deposit_type)
				this.$set(this.prevData,"account_number",this.submittedData.account_number)
				this.$set(this.prevData,"company_number",this.submittedData.company_number)
				this.$set(this.prevData,"company_kana",this.submittedData.company_kana)
			}else{
				this.data.push(this.submittedData)
			}
			this.processName = ''
		}

		buttonProcess(val) {
			alert(JSON.stringify(val))
			this.processFooterValues(val)
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

		// modal_pr values
			modalPrShowing = false
			modalType= ""
			openModalPr(val) {
				alert("from openModalPr: " + val._eventName)
				this.modalPrShowing = true
				alert("from openModalPr, showing midal: " + this.modalPrShowing )
				this.modalType = val._eventName
			}

			closeModalPr(val){
				this.modalPrShowing = false
			}

			goToMenu() {
				this["$router"].push('/mymenu')
			}
	}
	
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
	
/* 
	div{
		&.card{
			&-body{
				@include give-padding(1rem);
			}
		}
		&.text-right{
			button{
				&.button-width{
					&-90{
						@include give-width(90px);
						font-size: 0.75rem;
					}
				}
			}
		}
		input,select{
			&.form-control{
				border: 1px solid black;
				&-sm{
					font-size: 0.75rem;
					font-weight: 600;
				}
				&.input-width{
					&-200{
						@include give-width(200px);
					}
					&-100{
						@include give-width(100px);
					}
					&-150{
						@include give-width(150px);
					}
				}
			}
		}

	} */

</style>