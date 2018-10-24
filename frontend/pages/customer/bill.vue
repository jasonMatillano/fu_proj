<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">請求先マスタ</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="4" md="12">
				<section class="field-input mb-2">
					<b-row class="p-0 m-0">
						<b-col xl="4" class="p-0">
							<b-form-input v-model="filter" placeholder="フィルタ" size="sm"/>
						</b-col>
					</b-row>
				</section>
				<section class="field-table">
					<b-table striped 
							bordered 
							small 
							:items="data" 
							:fields="field" 
							:filter="filter" 
							hover　
							class="m-0"
							@filtered="setActiveData"
							@row-dblclicked="dblClickTable">
							<template slot="end" slot-scope="row">
								{{row.value == true ? '終了'　: ' '}}
							</template>
					</b-table>
				</section>
			</b-col>
			<b-col xl="7">
				<transition name="fade">
					<section class="field-form field-bill" v-if="formShow">
						<b-card bg-variant="light">
							<b-col class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>請求先 ＞{{processName}}</span></h5>
							</b-col>
							<c-form :formdata="prevData" @submit="submit" @clickCancel="clickCancel" :processName="processName"></c-form>
						</b-card>
					</section>
				</transition>
			</b-col>
		</b-row>
		<c-modal @submit="confirmSubmit" id="confirm"></c-modal>
		<c_footer class="content-footer" @data="clickButton" @clickCancel="clickCancel"></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component,Watch, Vue, Prop} from 'nuxt-property-decorator'

	import c_footer from '@/components/footer.vue'
	import cForm from '@/components/forms/customer/bill'
	import util from '@/assets/js/util.js'
	import cModal from '@/components/modal'

	@Component({
		components:{
			c_footer,
			cForm,
			cModal,
		},
	})
	export default class Bill extends Vue{		
		formShow = false
		processName = null
		prevData = null
		filter = null
		submittedData = null
		fromBranch = false

		field = [
				{key: 'billing_code',label: '請求先CD',class:'table-col-width-70 text-center'},
				{key: 'billing_name_kana',label: '請求先ｶﾅ'},
				{key: 'billing_name',label: '略称名'},
				{key: 'end',label: '終了',class: 'table-col-width-50 text-center'},

		]
		data = [
				{billing_code: '00000',billing_name_kana:'ファクトリ',billing_name:'請求',billing_shortname:'ファクトリ',billing_postal_code: '408-0301',billing_address1: '東京都中央区八重洲一丁目5番3号',billing_address2: '',billing_address3: '',billing_acc_phoneno: '21235-512',billing_telno1: '21235-512',billing_telno2: '',billing_title: '敬称',billing_claims_group: '',total_invoice_printing_type: '',billing_printing_type: '',property_printing_type: '',deposit_method1_reference: '',deposit_method1_split_payment: '',deposit_method1: '',deposit_method1_payment_month_after: '',deposit_method1_plan_day: '',deposit_method1_div:'',deposit_method1_criteria_percentage: '',deposit_method1_div_standard_amount: '',deposit_method1_slip: '',deposit_method2_reference: '',deposit_method2_split_payment: '',deposit_method2: '',deposit_method2_payment_month_after: '',deposit_method2_plan_day: '',deposit_method2_div: '',deposit_method2_criteria_percentage: '',deposit_method2_div_standard_amount: '',deposit_method2_slip: '',end: true},
				{billing_code: '00001',billing_name_kana:'ファクトリ1',billing_name:'請求1',billing_shortname:'ファクトリ1',billing_postal_code: '408-0301',billing_address1: '東京都中央区八重洲一丁目5番3号',billing_address2: '',billing_address3: '',billing_acc_phoneno: '21235-512',billing_telno1: '21235-512',billing_telno2: '',billing_title: '敬称',billing_claims_group: '',total_invoice_printing_type: '',billing_printing_type: '',property_printing_type: '',deposit_method1_reference: '',deposit_method1_split_payment: '',deposit_method1: '',deposit_method1_payment_month_after: '',deposit_method1_plan_day: '',deposit_method1_div:'',deposit_method1_criteria_percentage: '',deposit_method1_div_standard_amount: '',deposit_method1_slip: '',deposit_method2_reference: '',deposit_method2_split_payment: '',deposit_method2: '',deposit_method2_payment_month_after: '',deposit_method2_plan_day: '',deposit_method2_div: '',deposit_method2_criteria_percentage: '',deposit_method2_div_standard_amount: '',deposit_method2_slip: '',end: true},
				{billing_code: '00002',billing_name_kana:'ファクトリ2',billing_name:'請求2',billing_shortname:'ファクトリ2',billing_postal_code: '408-0301',billing_address1: '東京都中央区八重洲一丁目5番3号',billing_address2: '',billing_address3: '',billing_acc_phoneno: '21235-512',billing_telno1: '21235-512',billing_telno2: '',billing_title: '敬称',billing_claims_group: '',total_invoice_printing_type: '',billing_printing_type: '',property_printing_type: '',deposit_method1_reference: '',deposit_method1_split_payment: '',deposit_method1: '',deposit_method1_payment_month_after: '',deposit_method1_plan_day: '',deposit_method1_div:'',deposit_method1_criteria_percentage: '',deposit_method1_div_standard_amount: '',deposit_method1_slip: '',deposit_method2_reference: '',deposit_method2_split_payment: '',deposit_method2: '',deposit_method2_payment_month_after: '',deposit_method2_plan_day: '',deposit_method2_div: '',deposit_method2_criteria_percentage: '',deposit_method2_div_standard_amount: '',deposit_method2_slip: '',end: true},
				{billing_code: '00003',billing_name_kana:'ファクトリ3',billing_name:'請求3',billing_shortname:'ファクトリ3',billing_postal_code: '408-0301',billing_address1: '東京都中央区八重洲一丁目5番3号',billing_address2: '',billing_address3: '',billing_acc_phoneno: '21235-512',billing_telno1: '21235-512',billing_telno2: '',billing_title: '敬称',billing_claims_group: '',total_invoice_printing_type: '',billing_printing_type: '',property_printing_type: '',deposit_method1_reference: '',deposit_method1_split_payment: '',deposit_method1: '',deposit_method1_payment_month_after: '',deposit_method1_plan_day: '',deposit_method1_div:'',deposit_method1_criteria_percentage: '',deposit_method1_div_standard_amount: '',deposit_method1_slip: '',deposit_method2_reference: '',deposit_method2_split_payment: '',deposit_method2: '',deposit_method2_payment_month_after: '',deposit_method2_plan_day: '',deposit_method2_div: '',deposit_method2_criteria_percentage: '',deposit_method2_div_standard_amount: '',deposit_method2_slip: '',end: true},
				{billing_code: '00004',billing_name_kana:'ファクトリ4',billing_name:'請求4',billing_shortname:'ファクトリ4',billing_postal_code: '408-0301',billing_address1: '東京都中央区八重洲一丁目5番3号',billing_address2: '',billing_address3: '',billing_acc_phoneno: '21235-512',billing_telno1: '21235-512',billing_telno2: '',billing_title: '敬称',billing_claims_group: '',total_invoice_printing_type: '',billing_printing_type: '',property_printing_type: '',deposit_method1_reference: '',deposit_method1_split_payment: '',deposit_method1: '',deposit_method1_payment_month_after: '',deposit_method1_plan_day: '',deposit_method1_div:'',deposit_method1_criteria_percentage: '',deposit_method1_div_standard_amount: '',deposit_method1_slip: '',deposit_method2_reference: '',deposit_method2_split_payment: '',deposit_method2: '',deposit_method2_payment_month_after: '',deposit_method2_plan_day: '',deposit_method2_div: '',deposit_method2_criteria_percentage: '',deposit_method2_div_standard_amount: '',deposit_method2_slip: '',end: true}

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
			if(this.fromBranch == true){
				this.fromBranch = false
				this["$router"].push({ name: 'customer-branch', params:{ fromBill: true, data: this.prevData} })
			}
			this.formShow = false
			this.processName = ''
			util.resetActive(this.prevData)
			this.prevData = {}
		}

		//To be change
		submit(value){
			this.submittedData = value
		}
		
		confirmSubmit(){
			if(this.fromBranch == true){
				this.fromBranch = false
				this["$router"].push({ name: 'customer-branch', params:{ fromBill: true, data: this.prevData} })
			}
		}
		setActiveData(filteredItem){
			if(this.filter != null){
				console.log(this.prevData	)
				this.formShow = true
				this.processName = "変更"
				util.resetActive(filteredItem)
				this.prevData = filteredItem
				util.setActive(this.prevData,this.processName)	
			}
			
		}
		mounted() {
			if(this["$router"].currentRoute.params.data != null){
				this.filter = this["$router"].currentRoute.params.data.bill_code
				this.prevData = this["$router"].currentRoute.params.data
				this.fromBranch = true
			}
		}

	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
</style>