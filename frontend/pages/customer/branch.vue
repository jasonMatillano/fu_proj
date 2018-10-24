<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">得意先拠点マスタメンテナンス</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col :xl="size" md="12">
				<section class="field-input mb-2">
					<b-row class="p-0 m-0">
						<b-col :xl="fsize" class="p-0">
							<b-form-input v-model="filter" placeholder="フィルタ" size="sm"/>
						</b-col>
						<b-col :xl="space" class="p-0">
							
						</b-col>
						<b-col :xl="fsize" class="p-0">
							<b-form-input @input="getContactId" v-model="filter2" placeholder="主担当者ID" size="sm"/>
						</b-col>
						<b-col :xl="fsize" align-self="center" id="status">
							<b-form-checkbox id="selectChecked1" @change="selectChecked1">終了</b-form-checkbox>
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
							@row-clicked="clickTable"
							@row-dblclicked="dblClickTable">
							<template slot="end" slot-scope="data">
							    <span v-if="data.value">終了</span>
					         </template>
							<!-- <template slot="action" slot-scope="row">
								<b-row class="p-0 m-0">
									<b-col sm="12" class="text-center">
							        	<b-button variant="info" size="sm" @click.stop="propItem(row.item)" class="mr-1">
								          設定
								        </b-button>
									</b-col>
								</b-row>
							</template> -->
					</b-table>
				</section>
			</b-col>
			<b-col xl="4" md="7">
				<!-- <transition name="fade-fast">
					<section class="field-form" v-if="!formShow">
						<b-card bg-variant="light">
							<c-query></c-query>
						</b-card>
					</section>
				</transition> -->
				<section class="field-form">
					<transition name="fade">
						<b-card bg-variant="light" v-if="formShow">
							<b-col class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>得意先拠点 ＞{{processName}}</span></h5>
							</b-col>
							<c_form :formdata="prevData" @submit="submit" @clickCancel="clickCancel" :processName="processName"></c_form>
						</b-card>
					</transition>	
				</section>
			</b-col>
			<b-col xl="5">
				<b-row v-if="tableShow">
					<b-col sm="6">
						<b-col>
							<section class="field-input mb-2">
								<b-row class="p-0 m-0">
									<b-col xl="6" class="p-0">
										<b-form-input v-model="filterdept" placeholder="フィルタ" size="sm"/>
									</b-col>
									<b-col xl="3" class="p-0">
									</b-col>
									<b-col xl="3" class="p-0">
										<b-button size="sm" variant="success" @click="addUserItem(1)">新規</b-button>
									</b-col>
								</b-row>
							</section>
							<section class="field-table table-half">
								<b-table striped 
										bordered 
										small 
										:items="deptItem" 
										:fields="fieldDept" 
										:filter="filterdept" 
										hover　
										class="m-0"
										tbody-class="table-half"
										@row-dblclicked="dblClickTableDept">
										<template slot="end" slot-scope="deptItem">
										    <span v-if="deptItem.value">終了</span>
								         </template>
								</b-table>
								
							</section>		
						</b-col>
						<b-col>
							<section class="field-form">
								<transition name="fade">
									<b-card bg-variant="light" v-if="formShowDept">
										<b-col class="p-0">
											<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>得意先部課 ＞{{processName}}</span></h5>
										</b-col>
										<c-form-dept :formdata="prevDataDept" @submit="submitDept" @clickCancel="clickCancelDept" :processName="processName"></c-form-dept>
									</b-card>	
								</transition>
							</section>
						</b-col>
					</b-col>
					<b-col sm="6">
						<b-col>
							<section class="field-input mb-2">
								<b-row class="p-0 m-0">
									<b-col xl="6" class="p-0">
										<b-form-input v-model="filterUser" placeholder="フィルタ" size="sm"/>
									</b-col>
									<b-col xl="3" class="p-0">
									</b-col>
									<b-col xl="3" class="p-0">
										<b-button size="sm" variant="success" @click="addUserItem(2)">新規</b-button>
									</b-col>
								</b-row>
							</section>
							<section class="field-table table-half">
								<b-table striped 
										bordered 
										small 
										:items="userItem" 
										:fields="fieldUser" 
										:filter="filterUser" 
										hover
										class="m-0"
										tbody-class="table-half"
										@row-dblclicked="dblClickTableUser">
										<template slot="end" slot-scope="userItem">
										    <span v-if="userItem.value">終了</span>
								         </template>
										
								</b-table>
							</section>		
						</b-col>
						<b-col>
							<section class="field-form">
								<transition name="fade">
									<b-card bg-variant="light" v-if="formShowUser">
										<b-col class="p-0">
											<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>主担当者 ＞{{processName}}</span></h5>
										</b-col>
										<c-form-user :formdata="prevDataUser" @submit="submitUser" @clickCancel="clickCancelUser" :processName="processName"></c-form-user>
									</b-card>
								</transition>
							</section>
						</b-col>
					</b-col>
				</b-row>
			</b-col>
			
		</b-row>
		<c-modal @submit="confirmSubmit" id="confirm"></c-modal>
		<c_footer class="content-footer" @data="clickButton" @clickCancel="clickCancel"></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component,Watch, Vue, Prop} from 'nuxt-property-decorator'

	import c_footer from '@/components/footer.vue'
	import c_form from '@/components/forms/customer/branch'
	import util from '@/assets/js/util.js'
	import cModal from '@/components/modal'
	import cFormDept from '@/components/forms/customer/department'
	import cFormUser from '@/components/forms/customer/user'
	import cQuery from '@/components/forms/customer/query'

	@Component({
		components:{
			c_footer,
			c_form,
			cModal,
			cFormDept,
			cFormUser,
			cQuery
		},
	})
	export default class Cbranch extends Vue{		
		filter = null
		filter2 = null
		formShow = false
		formShowDept = false
		formShowUser = null
		processName = ''
		prevData = null
		submittedData = null
		size = 8
		isHide = false
		fsize = 2;
		fieldHolder =  null
		tableShow = false
		filterdept = null
		filterUser = null
		prevDataDept = null
		prevDataUser = null
		submitFlg = 0
		submittedDataDept = null
		submittedDataUser = null
		deptItem = null
		userItem = null
		currentData1 = [];
		filteredByID = [];
		showSpace = true;
		space = 6;

		field = [
				{key: 'g_code', label: '得CD',class: 'table-col-width-60 text-center '},
				{key: 'g_name',label: '得意先グループ名',class: 'table-col-width-150 '},
				{key: 'code', label: '拠点CD',class: 'table-col-width-60 text-center '},
				{key: 'name',label: '得意先拠点名',class: 'table-col-width-150 '},
				{key: 'mst_cst_contact_id',label: '主担当者ID',class: 'table-col-width-110 '},
				{key: 'one_shot',label: '単発',class: 'table-col-width-50 '},
				{key: 'phone_number_1', label: '電話番号',class: 'table-col-width-100 text-center '},
				{key: 'fax_number_1', label: 'FAX番号',class: 'table-col-width-100 text-center '},
				{key: 'billing_destination_code', label: '請求先CD',class: 'table-col-width-100 text-center '},
				{key: 'billing_name', label: '請求先名',class: 'table-col-width-100 text-center '},
				{key: 'end',label: '終了',class: 'table-col-width-50 '},
		]
		field_2 = [
				{key: 'code', label: '拠点CD',class: 'table-col-width-50 text-center'},
				{key: 'name', label: '得意先拠点名',class: 'table-col-width-150'},
				{key: 'end',label: '終了',class: 'table-col-width-30'},
		]

		fieldDept = [
				{key: 'code', label:'部課CD',class: 'table-col-width-60 text-center'},
				{key: 'name', label:'部課'},
				{key: 'end', label: '終了',class:'table-col-width-50'}
		]
		fieldUser = [
				{key: 'code', label: '主担当者CD',class:'table-col-width-80 text-center'},
				{key: 'name', label: '主担当者名'},
				{key: 'end', label: '終了',class:'table-col-width-50'},
		]

		data = [
				{no: 0,g_code: '11000',g_name:'WIN BRIGHT',code: '02',name:'TOKYO' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: true,mst_cst_contact_id: "124 日本　太郎", bill_code: '00000',bill_name: '請求'},
				{no: 1,g_code: '11000',g_name:'WIN BRIGHT',code: '02',name:'OKINAWA' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: '',mst_cst_contact_id: "125 ジョン", bill_code: '00001',bill_name: '請求1'},
				{no: 2,g_code: '11000',g_name:'WIN BRIGHT',code: '02',name:'PHILIPPINES' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: '',mst_cst_contact_id: "126 ポール", bill_code: '00002',bill_name: '請求2'},
				{no: 3,g_code: '11030',g_name:'TOYOTA',code: '03',name:'OKINAWA' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: '',mst_cst_contact_id: "127フランク", bill_code: '00003',bill_name: '請求3'},
				{no: 4,g_code: '11000',g_name:'TOYOTA',code: '03',name:'SAPPORO' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: true},
				{no: 5,g_code: '11000',g_name:'TOYOTA',code: '03',name:'PHILIPPINES' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: ''},
				{no: 6,g_code: '11200',g_name:'InStyle wedding',code: '03',name:'HOKAIDO' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: ''},
				{no: 7,g_code: '11000',g_name:'InStyle wedding',code: '03',name:'KYOTO' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: true},
				{no: 8,g_code: '11000',g_name:'InStyle wedding',code: '03',name:'PHILIPPINES' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: ''},
				{no: 9,g_code: '11000',g_name:'WIN BRIGHT',code: '03',name:'NARA' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: true},
				{no: 10,g_code: '12000',g_name:'WIN BRIGHT',code: '03',name:'CEBU' ,phone_number_1: '06-6972-5251',fax_number_1: '06-6975-1513',kana: '',adress_2: '',address_3: '',one_shot: '', billing_destination_code: '00000',billing_name: '請求',end: ''},
		]

		dataDept = [
				[
					{code: '00',name: 'テスト部課',phone_number: '06-6972-5251',extension: '',end: true}
				],
				[
					{code: '01',name: 'テスト部課１',phone_number: '06-6972-5251',extension: ''},{code: '02',name: 'テスト部課2',phone_number: '06-6972-5251',extension: '',end: true},
				],
				[
					{code: '01',name: 'テスト部課１',phone_number: '06-6972-5251',extension: ''},{code: '02',name: 'テスト部課2',phone_number: '06-6972-5251',extension: '',end: true},
					
					{code: '03',name: 'テスト部課１',phone_number: '06-6972-5251',extension: ''},{code: '04',name: 'テスト部課2',phone_number: '06-6972-5251',extension: '',end: true},
				]
		]

		dataUser = [
				[{code: '124',name: '日本　太郎',end: true}],
				[{code: '125',name: 'ジョン',end: true}],
				[{code: '126',name: 'ポール',end: true}],
				[{code: '127',name: 'フランク',end: true}],
		]
		
		addUserItem(dialog){
			if(dialog == 1) 
				this.formShowDept = true;
			
			else if(dialog == 2) 
				this.formShowUser = true;
			
			this.processName = "新規"
		}
				
		selectChecked1(ev) {
			this.data = this.filteredByID;
			if(ev) {
				this.currentData1 = this.data;
				this.data = this.data.filter(function(res){
					return res['end'] == ev;
				})
			} else this.data = this.filteredByID;
		}

		getContactId(ev){
			this.data = this.filteredByID;
			if(ev) {
				this.currentData1 = this.data;
				var regex = new RegExp(ev);
				var datas = [];
				datas = this.data.filter(function(res){
					return regex.test(res['mst_cst_contact_id']);
				})
				
				if(datas.length > 0)
					this.data = datas
				else
					this.data = [];
						
			} else this.data = this.filteredByID;
		}

		propItem(data){
			this["$router"].push({ name: 'customer-bill', params:{data} })
		}

		clickButton(item){ 
			if(item.index == '1'){
				//to do
			}else if(item.index == '3'){
				this.formShow = true
				this.processName = item.name
				util.resetActive(this.prevData)
				util.resetActive(this.prevDataDept)
				util.resetActive(this.prevDataUser)
				this.prevData = {}
				this.prevDataDept = null
				this.prevDataUser = null
			}else if(item.index == '2'){
				alert("まだです！")
				this.clickCancel()
			}
		}

		clickTable(item){
			console.log(item);
			this.processName = "変更"
			util.resetActive(this.prevData)
			this.prevData = item
			util.setActive(this.prevData,this.processName)
			this.deptItem = this.dataDept[item.no];
			this.userItem = this.dataUser[item.no];
		}

		dblClickTable(item){
			this.formShow = true
			this.tableShow = true
			this.processName = "変更"
			util.resetActive(this.prevData)
			this.prevData = item
			util.setActive(this.prevData,this.processName)
			if(this.isHide == false)
				this.hideField();
		}

		dblClickTableDept(item){
			this.formShowDept = true
			this.processName = "変更"
			util.resetActive(this.prevDataDept)
			this.prevDataDept = item
			util.setActive(this.prevDataDept,this.processName)
		}
		dblClickTableUser(item){
			this.formShowUser = true
			this.processName = "変更"
			util.resetActive(this.prevDataUser)
			this.prevDataUser = item
			util.setActive(this.prevDataUser,this.processName)
		}
		//all called functions
		clickCancel(){
			this.isHide = false
			this.tableShow = false
			this.formShow = false
			this.formShowUser = false
			this.formShowDept = false
			this.processName = ''
			util.resetActive(this.prevData)
			this.prevData = {}
			this.size = 8
			this.fsize = 2
			this.space = 6
			this.field = this.fieldHolder
		}
		clickCancelDept(){
			this.formShowDept = false
			util.resetActive(this.prevDataDept)
			this.prevDataDept = {}
		}
		clickCancelUser(){
			this.formShowUser = false
			util.resetActive(this.prevDataUser)
			this.prevDataUser = {}
		}

		//To be change
		submit(value){
			this.submittedData = value
			this.submitFlg = 0
		}
		submitDept(value){
			console.log(value)
			this.submittedDataDept = value
			this.submitFlg = 1
		}
		submitUser(value){
			console.log(value)
			this.submittedDataUser = value
			this.submitFlg = 2
		}

		confirmSubmit(){
			if(this.processName == "変更"){
				if(this.submitFlg == 0){
					this.$set(this.prevData,"code",this.submittedData.code)
					this.$set(this.prevData,"kana",this.submittedData.kana)
					this.$set(this.prevData,"name",this.submittedData.name)
					this.$set(this.prevData,"postal_code",this.submittedData.postal_code)
					this.$set(this.prevData,"address_1",this.submittedData.address_1)
					this.$set(this.prevData,"address_2",this.submittedData.address_2)
					this.$set(this.prevData,"address_3",this.submittedData.address_3)
					this.$set(this.prevData,"phone_number_1",this.submittedData.phone_number_1)
					this.$set(this.prevData,"fax_number_1",this.submittedData.fax_number_1)
					util.resetActive(this.prevData)
					this.clickCancel()
				}else if(this.submitFlg == 1){
					this.$set(this.prevDataDept,"code",this.submittedDataDept.code)
					this.$set(this.prevDataDept,"name",this.submittedDataDept.name)
					this.$set(this.prevDataDept,"phone_number",this.submittedDataDept.phone_number)
					this.$set(this.prevDataDept,"extension",this.submittedDataDept.extension)
					util.resetActive(this.prevDataDept)
					this.formShowDept = false
				}else if(this.submitFlg == 2){
					this.$set(this.prevDataUser,"code",this.submittedDataUser.code)
					this.$set(this.prevDataUser,"name",this.submittedDataUser.name)
					this.$set(this.prevDataUser,"end",this.submittedDataUser.end)
					util.resetActive(this.prevDataUser)
					this.formShowUser	 = false
				}
			}else{
				if(this.submitFlg == 0){
					
					util.resetActive(this.prevData)
					this.clickCancel()
				}else if(this.submitFlg == 1){
					
					util.resetActive(this.prevDataDept)
					this.formShowDept = false
				}else if(this.submitFlg == 2){
					this.dataUser.push(this.submittedDataUser)
					util.resetActive(this.prevDataUser)
					this.formShowUser	 = false
				}
				
			}
			
			
		}
		hideField(){
			this.size = 3
			this.fsize = 3
			this.field = this.field_2
			this.isHide = true
			this.space = 3;
		}

		mounted() {
			this.filteredByID = this.data;
			console.log(this["$router"].currentRoute.params)
			this.fieldHolder = this.field
			if(this["$router"].currentRoute.params.data != null){
				this.filter = this["$router"].currentRoute.params.data.code
			}
		}

	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
</style>