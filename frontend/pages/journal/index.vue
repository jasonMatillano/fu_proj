<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="6" md="12">
				<section class="field-input mb-1">
					<b-col sm="12" class="p-0">
						<b-row>
							<b-col xl="3" md="4" class="mb-1">
								<b-form-input v-model="paymentFilter" 
											:placeholder="filter_lang" 
											size="sm"  
											v-if="selected == 1"
											id="payment"/>
								<b-form-input v-model="depositFilter" 
											:placeholder="filter_lang" 
											size="sm" 
											v-if="selected == 2"
											id="deposit"/>
							</b-col>
							<b-col xl="3" md="4" class="mb-1">
								<b-form-select v-model="selected"
				                     :options="{ '1': payment1_lang,'2': payment2_lang}"
				                     size="sm"
				                     class="py-1"
				                     @change="clickCancel">
				    			</b-form-select>
							</b-col>
						</b-row>
					</b-col>
				</section>
				<section class="field-table">
					<b-table striped
					         bordered
				         	 small
					         :items="paymentItem"
					         :fields="paymentField"
					         hover
					         :filter="paymentFilter"
					         @row-dblclicked="dblClickTablePayment" 
					         v-if="selected == 1">
					         <template slot="payment_class" slot-scope="data">
					         	{{data.item.payment_class.code}} {{data.item.payment_class.name}}
					         </template>
					         <template slot="debit" slot-scope="data">
					         	{{data.item.debit.code}} {{data.item.debit.name}}
					         </template>
					         <template slot="credit" slot-scope="data">
					         	{{data.item.credit.code}} {{data.item.credit.name}}
					         </template>
					</b-table>
					<b-table striped
					         bordered
				         	 small
					         :items="depositItem"
					         :fields="depositField"
					         hover
					         :filter="depositFilter"
					         @row-dblclicked="dblClickTableDeposit"
					         v-if="selected == 2">
					         <template slot="deposit_class" slot-scope="data">
					         	{{data.item.deposit_class.code}} {{data.item.deposit_class.name}}
					         </template>
					         <template slot="debit" slot-scope="data">
					         	{{data.item.debit.code}} {{data.item.debit.name}}
					         </template>
					         <template slot="credit" slot-scope="data">
					         	{{data.item.credit.code}} {{data.item.credit.name}}
					         </template>
					</b-table>
				</section>
			</b-col>
			<b-col xl="3" md="12">
				<transition name="fade">
					<section class="field-form" v-if="formShow">
						<b-card bg-variant="light" v-if="selected == 1">
							<b-col sm="12" class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>支払 ＞　{{processName}}</span></h5>
							</b-col>
							<payment_form :formdata="prevData" :processName="processName" @clickCancel="clickCancel" @submit="submit"></payment_form>
						</b-card>
						<b-card bg-variant="light" v-if="selected == 2">
							<b-col sm="12" class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>入金 ＞　{{processName}}</span></h5>
							</b-col>
							<deposit_form :formdata="prevData" :processName="processName" @clickCancel="clickCancel" @submit="submit"></deposit_form>
						</b-card>
					</section>
				</transition>
			</b-col>
		</b-row>
		<c_modal @submit="confirmSubmit" id="confirm"></c_modal>
		<c_footer class="content-footer" @data="clickButton" @clickCancel="clickCancel"></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator';

	import c_footer from '@/components/footer.vue';
	import payment_form from '@/components/forms/payment';
	import deposit_form from '@/components/forms/deposit';
	import c_modal from '@/components/modal'
	@Component({
		components:{
			c_footer,
			payment_form,
			deposit_form,
			c_modal
		},
	})
	export default class Journal extends Vue {

		selected = 1
		depositFilter = ''
		paymentFilter = ''
		processName = ''
		prevData = {}
		type = null
		formShow = false
		submittedData = null
		defDataPayment = {payment_class: {code: '',name: ''},support_class: '',name: '',debit: {code: '',name: ''}, credit:{code: '',name:''}}
		defDataDeposit = {deposit_class: {code: '',name: ''},support_class: '',name: '',debit: {code: '',name: ''}, credit:{code: '',name:''}}

		// change lang
		title_lang = ""
		filter_lang = ""
		payment1_lang = ""
		payment2_lang = ""
	
		payment_class_lang = ""
		deposit_class_lang = ""
		
		support_class_lang = "" 
		name_lang = ""
		debit_lang = ""
		credit_lang = ""

		created() {
			this.title_lang = this.$t("Auth.pages.journal.title").toString()
			this.filter_lang = this.$t("Auth.pages.journal.filter").toString()
			this.payment1_lang = this.$t("Auth.pages.journal.payment1").toString()
			this.payment2_lang = this.$t("Auth.pages.journal.payment2").toString()

			this.payment_class_lang = this.$t("Auth.pages.journal.payment_class").toString()
			this.deposit_class_lang = this.$t("Auth.pages.journal.deposit_class").toString()
			this.support_class_lang = this.$t("Auth.pages.journal.support_class").toString()
			this.name_lang = this.$t("Auth.pages.journal.name").toString()
			this.debit_lang = this.$t("Auth.pages.journal.debit").toString()
			this.credit_lang = this.$t("Auth.pages.journal.credit").toString()

			this.$set(this.paymentField[0], "label", this.payment_class_lang)
			this.$set(this.paymentField[1], "label", this.support_class_lang)
			this.$set(this.paymentField[2], "label", this.name_lang)
			this.$set(this.paymentField[3], "label", this.debit_lang)
			this.$set(this.paymentField[4], "label", this.credit_lang)

			this.$set(this.depositField[0], "label", this.deposit_class_lang)
			this.$set(this.depositField[1], "label", this.support_class_lang)
			this.$set(this.depositField[2], "label", this.name_lang)
			this.$set(this.depositField[3], "label", this.debit_lang)
			this.$set(this.depositField[4], "label", this.credit_lang)
		}

		paymentField = [
			{key:'payment_class',label:'支払区分',class: 'table-col-width-20'},
			{key: 'support_class',label: '補助区分',class: 'table-col-width-70 text-right'},
			{key: 'name',label: '仕訳名'},
			{key: 'debit',label: '借方　主科目'},
			{key: 'credit',label: '貸方　主科目'}
		]
		depositField = [
			{key:'deposit_class',label:'入金区分',class: 'table-col-width-20'},
			{key: 'support_class',label: '補助区分',class: 'table-col-width-70 text-right'},
			{key: 'name',label: '仕訳名'},
			{key: 'debit',label: '借方　主科目'},
			{key: 'credit',label: '貸方　主科目'}
		]
		
		paymentItem = [
			{payment_class: {code: '1',name: 'テスト支払'},support_class: '1',name: 'テスト支払',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{payment_class: {code: '2',name: 'テスト支払'},support_class: '2',name: 'テスト支払',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{payment_class: {code: '3',name: 'テスト支払'},support_class: '3',name: 'テスト支払',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{payment_class: {code: '4',name: 'テスト支払'},support_class: '4',name: 'テスト支払',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{payment_class: {code: '5',name: 'テスト支払'},support_class: '5',name: 'テスト支払',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{payment_class: {code: '6',name: 'テスト支払'},support_class: '6',name: 'テスト支払',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{payment_class: {code: '7',name: 'テスト支払'},support_class: '7',name: 'テスト支払',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{payment_class: {code: '8',name: 'テスト支払'},support_class: '8',name: 'テスト支払',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{payment_class: {code: '9',name: 'テスト支払'},support_class: '9',name: 'テスト支払',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
		]
		depositItem = [
			{deposit_class: {code: '1',name: 'テスト入金'},support_class: '1',name: 'テスト入金',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{deposit_class: {code: '2',name: 'テスト入金'},support_class: '2',name: 'テスト入金',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{deposit_class: {code: '3',name: 'テスト入金'},support_class: '3',name: 'テスト入金',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{deposit_class: {code: '4',name: 'テスト入金'},support_class: '4',name: 'テスト入金',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{deposit_class: {code: '5',name: 'テスト入金'},support_class: '5',name: 'テスト入金',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{deposit_class: {code: '6',name: 'テスト入金'},support_class: '6',name: 'テスト入金',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{deposit_class: {code: '7',name: 'テスト入金'},support_class: '7',name: 'テスト入金',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{deposit_class: {code: '8',name: 'テスト入金'},support_class: '8',name: 'テスト入金',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
			{deposit_class: {code: '9',name: 'テスト入金'},support_class: '9',name: 'テスト入金',debit: {code: '310',name: 'テスト借方'}, credit:{code: '100',name:'テスト貸方'}},
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

			if(this.selected == 1){
				this.prevData = this.defDataPayment
			}else{
				this.prevData = this.defDataDeposit
			}
			
		}

		dblClickTablePayment(item){
			this.reset_color()
			this.formShow = true
			this.processName = "変更"
			this.prevData = item
			this.set_color(item)
		}

		dblClickTableDeposit(item){
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
			this.submittedData = value.data
			this.type = value.type
		}
		confirmSubmit(){
			this.formShow = false
			if(this.processName == "変更"){
				if(this.type == "payment"){
					this.$set(this.prevData,"support_class",this.submittedData.support_class)
					this.$set(this.prevData,"name",this.submittedData.name)
				}else if(this.type == "deposit"){
					this.$set(this.prevData,"support_class",this.submittedData.support_class)
					this.$set(this.prevData,"name",this.submittedData.name)
				}
			}else{
				if(this.type == "payment"){
					this.paymentItem.push(this.submittedData)
				}else if(this.type == "deposit"){
					this.depositItem.push(this.submittedData)
				}
			}
			this.processName = ''
		}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
</style>