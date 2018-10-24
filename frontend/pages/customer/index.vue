<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">得意先グループマスタメンテナンス</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="7" md="12">
				<section class="field-input mb-2">
					<b-col xl="4" class="p-0">
						<b-form-input v-model="filter" placeholder="フィルタ" size="sm"/>
					</b-col>
				</section>
				<section class="field-table">
					<b-table striped 
							bordered 
							small 
							:items="data" 
							:fields="field" 
							:filter="filter" 
							hover　
							@row-dblclicked="dblClickTable">
							<template slot="action" slot-scope="row">
								<b-row class="p-0 m-0">
									<b-col sm="12" class="text-center">
							        	<b-button variant="info" size="sm" @click.stop="propItem(row.item)" class="mr-1">
								          拠点
								        </b-button>
									</b-col>
								</b-row>
							</template>
					</b-table>
				</section>
			</b-col>
			<transition name="fade">
				<b-col xl="3" md="7" v-if="formShow">
					<section class="field-form">
						<b-card bg-variant="light" v-if="formShow">
							<b-col class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>得意先 ＞{{processName}}</span></h5>
							</b-col>
							<c_form :formdata="prevData" @submit="submit" @clickCancel="clickCancel" :processName="processName"></c_form>
						</b-card>
					</section>
				</b-col>
			</transition>
			<transition name="fade">
				<b-col xl="2" class="p-0" v-if="formShowFile">
					<section class="field-form">
							<b-card bg-variant="light">
								<h6>企業情報ファイル</h6>
								<c-file-up @submit="submitFile" @clickCancel="formShowFile = !formShowFile"></c-file-up>
							</b-card>
					</section>
				</b-col>
			</transition>	
		</b-row>
		<c-modal @submit="confirmSubmit" id="confirm"></c-modal>
		<c_footer class="content-footer" @data="clickButton" @clickCancel="clickCancel"></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component,Watch, Vue } from 'nuxt-property-decorator'

	import c_footer from '@/components/footer.vue'
	import c_form from '@/components/forms/customer/group'
	import util from '@/assets/js/util.js'
	import cModal from '@/components/modal'
	import cFileUp from '@/components/forms/customer/fileupload'

	@Component({
		components:{
			c_footer,
			c_form,
			cModal,
			cFileUp
		},
	})
	export default class Customer extends Vue{
		filter = null
		formShow = false
		formShowFile = false
		processName = ''
		prevData = null
		submittedData = null
		memo = ""
		dt = null
		y = null
		m = null
		dy = null
		fd = null

		field = [
				{key: 'code', label: '得意先グループCD',class: 'table-col-width-130 text-center'},
				{key: 'kana',label: '得意先グループｶﾅ'},
				{key: 'name',label: '得意先グループ名'},
				{key: 'claim_date',label: '最終請求日',class: "text-center table-col-width-80"},
				{key: 'end', label: '終了',class: 'table-col-width-50'},
				{key: 'credit_limit',label: '与信限度額',class: 'table-col-width-150 text-right'},
				{key: 'rank',label: 'ランク',class: 'table-col-width-50 text-center'},
				{key: 'score_1',label: '評点１',class: 'table-col-width-50 text-right'},
				{key: 'score_2',label: '評点２',class: 'table-col-width-50 text-right'},
				{key: 'score_3',label: '評点３',class: 'table-col-width-50 text-right'},
				{key: 'action', label: 'アクション',class: 'table-col-width-85'},
		]

		data = [
				{code: '1100000',kana: 'ウィンブライト',name:'WIN BRIGHT',credit_limit: '1,000,000',rank:'A',score_1:'10',score_2: '20',score_3: '30',claim_date: '2018/09/18',obj_class: true,short_name: 'ウィンブライト'},
				{code: '1100010',kana: 'トヨタ',name:'TOYOTA',credit_limit: '3,000,000',rank:'B',score_1:'40',score_2: '50',score_3: '60',claim_date: '2018/09/18',obj_class: false,short_name: 'トヨタ'},
				{code: '1100210',kana: 'インスタイルウイデイング',name:'InStyle wedding',credit_limit: '1,000,000,000',rank:'C',score_1:'70',score_2: '80',score_3: '90',claim_date: '2018/09/18',obj_class: false,short_name: 'インスタイル'},
				{code: '1102110',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1120011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1130101',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1140011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1150011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1160011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1170011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1180011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1190011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1100011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1200011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1300011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1400011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1500011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1600011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1700011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1800011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '1900011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '2000011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '2100011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '2200011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '2300011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '2400011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '2500011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
				{code: '3100011',kana: 'ウィンブライト',name:'WINDSGATE',credit_limit: '1,000,000',rank:'A',score_1:'0',score_2: '0',score_3: '0',claim_date: '2018/09/18',obj_class: false,short_name: 'WG'},
		]
		clickButton(item){
			if(item.index == '1'){
				//to do
			}else if(item.index == '3'){
				this.formShow = true
				this.formShowFile = true
				this.processName = item.name
				util.resetActive(this.prevData)
				this.prevData = {}
			}else if(item.index == '2'){
				alert("まだです！")
				this.clickCancel()
			}else if(item.index == '0'){
				this.formShowFile = false
			}
		}
		dblClickTable(item){
			this.formShow = true
			this.formShowFile = true
			this.processName = "変更"
			util.resetActive(this.prevData)
			this.prevData = item
			util.setActive(this.prevData,this.processName)
			this.setDate(this.prevData)
		}

		//all called functions
		clickCancel(){
			this.formShow = false
			this.processName = ''
			util.resetActive(this.prevData)
			this.prevData = {}
		}

		//To be change
		submit(value){
			this.submittedData = value
		}
		//To be change
		submitFile(value){
			alert("まだです。")
		}
		confirmSubmit(){
			this.formShow = false
			if(this.processName == "変更"){
				this.$set(this.prevData,"code",this.submittedData.code)
				this.$set(this.prevData,"kana",this.submittedData.kana)
				this.$set(this.prevData,"name",this.submittedData.name)
				this.$set(this.prevData,"credit_limit",this.submittedData.credit_limit)
				this.$set(this.prevData,"rank",this.submittedData.rank)
				this.$set(this.prevData,"score_1",this.submittedData.score_1)
				this.$set(this.prevData,"score_2",this.submittedData.score_2)
				this.$set(this.prevData,"score_3",this.submittedData.score_3)
			}else{
				this.data.push(this.submittedData)
			}
			this.processName = ''
			util.resetActive(this.prevData)
		}
		propItem(data){
			this["$router"].push({ name: 'customer-branch', params:{data} })
		}

		//will delete soon
		setDate(data){
			this.$set(data,"score_1_date",this.fd);
			this.$set(data,"score_2_date",this.fd);
			this.$set(data,"score_3_date",this.fd);
		}

		created(){
			 this.dt = new Date();
			 this.y = this.dt.getFullYear()
			 this.m = (this.dt.getMonth() < 9) ? "0"+ (this.dt.getMonth()+1) : this.dt.getMonth() + 1
			 this.dy = (this.dt.getDate() < 9) ? "0" + (this.dt.getDate()+1) : this.dt.getDate()

			 this.fd = this.y + "/" + this.m + "/" + this.dy;
		}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
</style>