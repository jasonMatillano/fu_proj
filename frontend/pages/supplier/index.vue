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
					<b-col class="p-0" sm="12" md="4">
						<b-form-input v-model="filter" :placeholder="filter_lang" size="sm"/>
					</b-col>
				</section>
				<section class="field-table">
					<b-table 
							striped 
							bordered 
							small 
							:items="data" 
							:fields="field" 
							:filter="filter" 
							hover
							class="m-0"
							@row-dblclicked="dblClickTable">
					</b-table>
				</section>
			</b-col>
			<b-col xl="5" md="12">
				<section class="field-form position-absolute">
					<transition name="fade">
						<b-card bg-variant="light" v-if="!formShow">
							<s_query></s_query>
						</b-card>
					</transition>
				</section>
				<section class="field-form position-absolute">
					<b-col xl="11">
						<transition name="fade">
							<b-card bg-variant="light" v-if="formShow">
								<b-col class="p-0">
									<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/></span>納入先　＞　{{processName}}</h5>
								</b-col>
								<c_form :formdata="prevData" :processName="processName" @clickCancel="clickCancel" @submit="submit"></c_form>
							</b-card>
						</transition>
					</b-col>
				</section>
			</b-col>
		</b-row>
		<c_modal @submit="confirmSubmit" id="confirm"></c_modal>
		<c_footer class="content-footer" @data="clickButton" @clickCancel="clickCancel"></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator'

	import c_footer from '@/components/footer.vue'
	import s_query from '@/components/forms/supplierquery'
	import c_form from '@/components/forms/supplier'
	import c_modal from '@/components/modal'
	import util from '@/assets/js/util.js'

	@Component({
		components:{
			c_footer,
			s_query,
			c_form,
			c_modal
		},
	})
	export default class Supplier extends Vue {
		filter = null
		formShow = false
		prevData = {}
		processName = ''
		submittedData = null
		
		// change lang 
		title_lang = ''
		filter_lang = ''

		customer_code_lang = ''
		customer_name_lang = ''
		supplier_code_lang = ''
		supplier_kana_lang = ''
		supplier_name_lang = ''
		phone_number_lang = ''
		
		created() {
			this.title_lang = this.$t("Auth.pages.supplier.title").toString()
			this.filter_lang = this.$t("Auth.pages.supplier.filter").toString()

			this.customer_code_lang = this.$t("Auth.pages.supplier.customer_code").toString()
			this.customer_name_lang = this.$t("Auth.pages.supplier.customer_name").toString()
			this.supplier_code_lang = this.$t("Auth.pages.supplier.supplier_code").toString()
			this.supplier_kana_lang = this.$t("Auth.pages.supplier.supplier_kana").toString()
			this.supplier_name_lang = this.$t("Auth.pages.supplier.supplier_name").toString()
			this.phone_number_lang = this.$t("Auth.pages.supplier.phone_number").toString()

			this.$set(this.field[0], "label", this.customer_code_lang)
			this.$set(this.field[1], "label", this.customer_name_lang)
			this.$set(this.field[2], "label", this.supplier_code_lang)
			this.$set(this.field[3], "label", this.supplier_kana_lang)
			this.$set(this.field[4], "label", this.supplier_name_lang)
			this.$set(this.field[5], "label", this.phone_number_lang)
		}

		field = [
			{key: 'customer_code',label:'得意先CD',class: 'table-col-width-65 text-center'},
			{key: 'customer_name', label: '得意先名'},
			{key: 'supplier_code', label: '納入先CD',class: 'table-col-width-65 text-right'},
			{key: 'supplier_kana', label: '納入先ｶﾅ'},
			{key: 'supplier_name', label: '納入先名'},
			{key: 'phone_number', label: '電話番号',class:'table-col-width-100 text-center'},
		]

		data = [
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー1',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100101',customer_name: 'XXX　―　ファクトリー2',supplier_code: '22',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5250'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー3',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー4',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー5',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
			{customer_code: '1100100',customer_name: 'XXX　―　ファクトリー',supplier_code: '99',supplier_kana: 'フジコーオオサカジムショ',supplier_name: 'フジコー大阪事務所',phone_number: '06-6972-5251'},
		]

		clickButton(item){
			if(item.index == '1'){
				//to do
			}else if(item.index == '3'){
				this.formShow = true
				this.processName = item.name
				util.resetActive(this.prevData)
				this.prevData = {}
			}else if(item.index == '2'){
				alert("まだです！")
				this.clickCancel()
			}
			
		}
		dblClickTable(item){
			this.formShow = true
			this.processName = "変更"
			util.resetActive(this.prevData)
			this.prevData = item
			util.setActive(this.prevData,this.processName)
		}

		//all called functions
		clickCancel(){
			util.resetActive(this.prevData)
			this.formShow = false
			this.processName = ''
			this.prevData = {}
		}

		//To be change
		submit(value){
			this.submittedData = value
		}
		confirmSubmit(){
			this.formShow = false
			if(this.processName == "変更"){
				this.$set(this.prevData,"customer_name",this.submittedData.customer_name)
				this.$set(this.prevData,"supplier_name",this.submittedData.supplier_name)
			}else{
				this.data.push(this.submittedData)
			}
			this.processName = ''
			util.resetActive(this.prevData)
		}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';

	
</style>