<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
				<hr>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="4" md="12">
				<section class="field-input mb-2">
					<b-row>
						<b-col xl="4">
							<b-form-input v-model="filter1" :placeholder="filter_lang" size="sm"/>
						</b-col>
						<b-col xl="4" align-self="center" id="status">
							<b-form-checkbox id="statusDeleted_1" @change="selectChecked1">終了</b-form-checkbox>
						</b-col>
					</b-row>
				</section>	
				<section class="field-table">
					<b-table 
							striped 
							bordered 
							small 
							:items="data" 
							:fields="field" 
							:filter="filter1"
							@row-clicked="getResultData"
							@row-dblclicked="editDialog">
							hover
							class="m-0">
							<template slot="is_end" slot-scope="data">
							    <span v-if="data.value">終了</span>
					         </template>
					</b-table>
				</section>
			</b-col>
			
			<b-col xl="4" md="12">
				<section class="field-form">
					<transition name="fade">
						<b-col v-if="dialogShow" xl="12" md="12">
							<b-row>
								<b-col :class="table == 0? 'text-left' : 'text-right'"><span><fa :icon="table == 0? 'arrow-alt-circle-left' : 'arrow-alt-circle-right'" size="2x"></fa></span></b-col>
							</b-row>
							<b-card bg-variant="light" >
							<c_form
								:procval1="procval1"
								:procval2="procval2"
								:procval3="procval3"
								:procval4="procval4"
								:procval5="procval5"
								:procval6="procval6"
								:table="table"
								:processName="processName"
								@fade="fade"
								@process="process"
								@changep1="procval1 = $event"
								@changep2="procval2 = $event"
								@changep3="procval3 = $event"
								@changep4="procval4 = $event"
								@changep5="procval5 = $event"
								@changep6="procval6 = $event"
							></c_form>
							</b-card>
						</b-col>
					</transition>	
				</section>
			</b-col>
			<b-col xl="4" md="12">
				<section class="field-input mb-2">
					<b-row>
						<b-col xl="4">
							<b-form-input v-model="filter2" :placeholder="filter_lang" size="sm"/>
						</b-col>
						<b-col xl="4" align-self="center" id="status">
							<b-form-checkbox id="statusDeleted_2" @change="selectChecked2">終了</b-form-checkbox>
						</b-col>
					</b-row>
				</section>
				<section class="field-table">
					<b-table 
							striped 
							bordered 
							small 
							:items="result_data" 
							:fields="field_1" 
							:filter="filter2" 
							@row-clicked="getResultData"
							@row-dblclicked="editDialog"
							hover
							class="m-0">
							<template slot="is_end2" slot-scope="result_data">
					         	<span v-if="result_data.value">終了</span>
					         </template>
					</b-table>
				</section>
			</b-col>
		</b-row>
		<c_modal_pr
			:modalPrShowing="modalPrShowing"
			:modalName="modalName"
			@closeModalPr="closeModalPr"
		></c_modal_pr>
		<c_footer class="content-footer" @eventTriggered="buttonProcess" @clickCancel="fade" :fnData="fnData" :events="UIEvents"></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator'
	import c_form from '@/components/forms/general'
	import c_footer from "@/components/footer.vue";
	import c_modal_pr from '@/components/modal_pr'
	import {UIEvent} from "@/assets/ts/UIEvent.ts"
	import {Keys} from "@/assets/ts/keys.ts"
	
	@Component({
		components:{
			c_footer,
			c_form,
			c_modal_pr,
		},
	})
	 
	export default class Bumon extends Vue {
		dialogShow = false
		processName = '';
		table = 0;
		tableData = [{department_name: ' '}];
		procval1 = '';
		procval2 = '';
		procval3 = false;
		procval4 = '';
		procval5 = '';
		procval6 = 0;
		currentCID = '';
		clickCheck = 1;
		currentData1 = [];
		currentData2 = [];
		currentUnUpdatedData = {
			mst_u_genpurp_id: '',
			genpurp_item_code: '',
			genpurp_item_name: '',
			genpurp_item_SEQ: 0,
			is_end2: false 
		};
		filter1 = null
		filter2 = null
		//change lang 
		title_lang = ''
		filter_lang = ''
		id_lang = ''
		mst_g_system_setting_lang = ''
		mst_g_recognition_name_lang = ''
		mst_g_hw_num_unallowed_lang = ''
		mst_g_system_character_lang = ''
		mst_g_system_number_lang = ''
		modalPrShowing = false
		modalName = ""
		key = '';
		
		UIEvents:Array<UIEvent>=[ 
		      new UIEvent(Keys.F2,'取消',true),
		      new UIEvent(Keys.F3,'削除',true),
		      new UIEvent(Keys.F4,'新規登録',true),
		      new UIEvent(Keys.F6,'日付',true),
		      new UIEvent(Keys.F8,'リスト',true),
		      new UIEvent(Keys.F12,'メニュ',true),
		  ]     
		
		fnData: any = [
		    { key: 113, name: "取消", action: "delete" },
		    { key: 115, name: "新規登録", action: "register" },
		    { key: 117, name: "日付", action: "showDate" },
		    { key: 119, name: "リスト", action: "showList" },
			  { key: 123, name: "メニュ", action: "menu" },
			  { key: 120, name: "上部門", action: "menu" },
			  { key: 121, name: "下部門", action: "menu" },
			  { key: 114, name: "削除", action: "menu" }	
		  ];
		
		created() {
			this.title_lang = this.$t("Auth.pages.general.title").toString();
			this.filter_lang = this.$t("Auth.pages.general.filter").toString();
		}

		field = [ 
		  	{key:'mst_u_genpurp_id' ,label:'汎用ID',class: 'text-center table-col-width-2 align-middle',sortable: true},
		    {key:'genpurp_name',label:'汎用名',class:'table-col-width-4 align-middle'},
		    {key:'is_end',label:'終了',class: 'text-center table-col-width-3 align-middle'}
		]
		data = [
			{mst_u_genpurp_id: 'SIKB1',genpurp_name: '仕入先区分1',is_end: false},
			{mst_u_genpurp_id: 'SHKB2',genpurp_name: '仕入先区分2',is_end: true},
			{mst_u_genpurp_id: 'SIKB9',genpurp_name: '仕入先区分3',is_end: false},
			{mst_u_genpurp_id: 'SIKB8',genpurp_name: '仕入先区分4',is_end: false},
		]
		field_1 = [ 
		  	{key:'mst_u_genpurp_id',label:'汎用ID',class: 'text-center table-col-width-2 align-middle',sortable: true},
		    {key:'genpurp_item_code',label:'汎用項目CD',class:'table-col-width-4 align-middle',sortable: true},
		    {key:'genpurp_item_name',label:'汎用項目名',class:'table-col-width-4 align-middle',sortable: true},
		    {key:'genpurp_item_SEQ',label:'汎用項目SEQ',class:'text-center table-col-width-4 align-middle',sortable: true},
		    {key:'is_end2',label:'終了',class: 'table-col-width-3 text-center align-middle'}
		]
		result_data = [{mst_u_genpurp_id: "SIKB1",genpurp_item_code: 'A',genpurp_item_name: '仕入先区分1',genpurp_item_SEQ: 1,is_end2: false}];
		label = [['汎用ID','汎用名','終了'],['汎用ID','汎用項目CD','終了','汎用項目名','汎用項目SEQ']];
				
		fade() {
			this.dialogShow = false;
		}
		
		openModalPr(val) {
			this.modalPrShowing = true
			this.modalName = val.name
		}
		
		closeModalPr(val){
			this.modalPrShowing = false
		}
		
		setData(ev) {
			if(this.table == 0) {
				this.procval1 = ev.mst_u_genpurp_id;
				this.procval2 = ev.genpurp_name;
				this.procval3 = ev.is_end;
			} else if(this.table == 1) {
				this.procval1 = ev.mst_u_genpurp_id;
				this.procval4 = ev.genpurp_item_code;
				this.procval5 = ev.genpurp_item_name;
				this.procval6 = ev.genpurp_item_SEQ;
				this.procval3 = ev.is_end2;
			}	
		}
		
		editDialog(ev) {
			this.currentUnUpdatedData = {
				mst_u_genpurp_id: ev.mst_u_genpurp_id,
				genpurp_item_code: ev.genpurp_item_code,
				genpurp_item_name: ev.genpurp_item_name,
				genpurp_item_SEQ: ev.genpurp_item_SEQ,
				is_end2: ev.is_end2
			}
			
			this.currentCID = this.table == 0 ? ev.mst_u_genpurp_id : parseInt(ev.mst_u_genpurp_id);
			this.dialogShow = true;
			this.key = "update";
			this.processName = '変更';
			this.resetValues();
			this.setData(ev);
			this.clickCheck = 2;
		}
		
		update() {		
			if(this.table == 0) {
				var id = this.procval1;
				var currentID = this.currentCID;
				var idArr = [];
				this.data = this.data.filter(function(res){
					return res['mst_u_genpurp_id'] != currentID;
				})
				
				idArr = this.data.map(function(res) {
					return res['mst_u_genpurp_id'];
				})
				
				if(!idArr.includes(id.toUpperCase()) && id != '') {
					this.data.push({
						mst_u_genpurp_id: id.toUpperCase(),
						genpurp_name: this.procval2,
						is_end: this.procval3 });
				}
			} else if(this.table == 1) {
				var index = this.result_data.findIndex(i => i.mst_u_genpurp_id === this.currentUnUpdatedData.mst_u_genpurp_id && 
						i.genpurp_item_code === this.currentUnUpdatedData.genpurp_item_code &&
						i.genpurp_item_name === this.currentUnUpdatedData.genpurp_item_name &&
						i.genpurp_item_SEQ === this.currentUnUpdatedData.genpurp_item_SEQ &&
						i.is_end2 === this.currentUnUpdatedData.is_end2);
				console.log(index);
				if(index != -1)
					this.result_data.splice(index, 1);
				this.result_data.push({
					mst_u_genpurp_id: this.procval1,
					genpurp_item_code: this.procval4,
					genpurp_item_name: this.procval5,
					genpurp_item_SEQ: this.procval6,
					is_end2: this.procval3
				})
			}
		}
		
		resetValues() {
			this.procval1 = '';
			this.procval2 = '';
			this.procval3 = false;
			this.procval4 = '';
			this.procval5 = '';
			this.procval6 = 0;
			this.clickCheck = 1;
		}
		
		add(ev) {
			if(this.table == 0) {
				var idArr = [];
				idArr = this.data.map(function(res) {
					return res['mst_u_genpurp_id'];
				})
				
				if(!idArr.includes(this.procval1.toUpperCase()) && this.procval1 != '') {
					this.data.push({
						mst_u_genpurp_id: this.procval1.toUpperCase() ,
						genpurp_name: this.procval2,
						is_end: this.procval3 });
				}
				
			} else if(this.table == 1) {
				var filter = {
					mst_u_genpurp_id: this.procval1,
					genpurp_item_code: this.procval4,
					genpurp_item_name: this.procval5,
					genpurp_item_SEQ: this.procval6,
					is_end2: this.procval3
				}

				var checkAdd = this.result_data.filter(function(res) {						
					for (let key in filter) {
						if (res[key] === undefined || res[key] != filter[key])
							return false;
					}
					return true;
				})
														
				if(checkAdd.length == 0) {
					this.result_data.push({
						mst_u_genpurp_id: this.procval1,
						genpurp_item_code: this.procval4,
						genpurp_item_name: this.procval5,
						genpurp_item_SEQ: this.procval6,
						is_end2: this.procval3
					})
				}
			} 
		}
		
		delete(ev) {
				if(this.table == 0) {
					var id = this.procval1;
					this.data = this.data.filter(function(res){
						return res['mst_u_genpurp_id'] != id;
					})
				} else if(this.table == 1) {
					var filter = {
						mst_u_genpurp_id: this.procval1,
						genpurp_item_code: this.procval4,
						genpurp_item_name: this.procval5,
						genpurp_item_SEQ: this.procval6,
						is_end2: this.procval3
					}
					this.result_data = this.result_data.filter(function(res){						
						for (let key in filter) {
							if (res[key] === undefined || res[key] != filter[key])
								return true;
							}
						  	return false;
					})
				}
		} 
						
		getResultData(ev) {		
			this.table = ev.genpurp_item_code == null? 0 : 1;
			this.currentCID = this.table == 0 ? ev.mst_u_genpurp_id : parseInt(ev.mst_u_genpurp_id);
			this.setData(ev);	
			
			if(this.table == 0 && this.clickCheck == 1) {
				var ctr = Math.floor(Math.random() * (20 - 1 + 1)) + 1;
				var cdArr = [];
				this.result_data.splice(0, this.result_data.length);
				
				for(var i = 0; i <= ctr; i++) {
					var cd = Math.floor(Math.random() * (20 - 1 + 1)) + 1;
					cdArr = this.result_data.map(function(res){
						return res['mst_u_genpurp_id'];
					})
					
					if(!cdArr.includes(cd.toString())) {
						this.result_data.push({
							mst_u_genpurp_id: ev.mst_u_genpurp_id,
							genpurp_item_code: String.fromCharCode(65+Math.floor(Math.random() * 26)),
							genpurp_item_name: 'サンプル' + (Math.floor(Math.random() * (20 - 1 + 1)) + 1),
							genpurp_item_SEQ: Math.floor(Math.random() * (20 - 1 + 1)) ,
							is_end2: Math.floor(Math.random() * (20 - 1 + 1)) % 2 == 0? false : true
						})
					}
				}
			}
			if(this.clickCheck == 2) this.clickCheck = 1;
		}
		
		selectChecked1(ev) {
			if(ev) {
				this.currentData1 = this.data;
				this.data = this.data.filter(function(res){
					return res['is_end'] == ev;
				})
			} else this.data = this.currentData1;
		}
		
		selectChecked2(ev) {
			if(ev) {
				this.currentData2 = this.result_data;
				this.result_data = this.result_data.filter(function(res){
					return res['is_end2'] == ev;
				})
			} else this.result_data = this.currentData2;
		}
			
		buttonProcess(value){
			console.log(value);
		
			this.dialogShow = true;
			this.processName = value.eventName;
			this.resetValues();
			switch(value.key) {
				// cancel delete and create operation
				case Keys.F2:
					this.fade()
					this.key = "F2";
				break;
				case Keys.F3:
					this.key = "F3";
				break;
				case Keys.F4:
					this.key = "F4";
				break;
	
				// perform date and list modals
				case Keys.F6:
					this.key = "F6";
				case Keys.F8:
					this.key = "F8";
				case Keys.F12:
					this.key = "F12";
					this.fade();
					this.openModalPr(value)
				break;
			}	
		}
		
		process(ev){
			
			switch(this.key){
				case "F2":
					this.fade();
					break;
				case "F3":
					this.delete(ev);
					break;
				case "F4":
					this.add(ev);
					break;
				case "update":
					this.update();
					break;
			}
			this.fade();
		}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/global.scss';
	@import '@/assets/scss/mixins.scss';
	@import '@/assets/scss/common.scss';
	
	table{
		background-color: $gray !important;
		height: 100%;
		&.table-hover{
			tbody {
					tr:hover{
							td,th{
								background-color: aqua;
							}
					}
				}
		}
		thead tr th, tbody tr td{
			@for $i from 1 through 20 {
			  	&.table-col-width-#{$i}{
					@include give-width(percentage($i/100));
				}
			}
		}
		thead{
				background-color: $darkblue;
				color: white;
				text-align: center;
				display:table;
				@include give-width(97.2%);
				@include give-width(-webkit-calc(100% - 10px));
				@include give-width(    -moz-calc(100% - 10px));
				@include give-width(         calc(100% - 10px));
			    table-layout:fixed;
			    th{
			    	vertical-align: middle;
			    }
		}
		tbody{
				display:block;
				height: 570px;
				overflow-y:scroll;
				tr{
					display:table;
				    @include give-width(100%);
				    table-layout:fixed;
				}
				
				tr:last-child {
					margin-bottom: 35px;
				}
		}
		&.table-bordered{	
			th, td{
				font-weight: normal;
				border: 1px solid black !important;
			}
		}
	}
	div{
		input, select{
			&.form-control{
				border: 1px solid black;
				&-sm{
					font-size: 0.75rem;
					font-weight: 600;
				}
			}
		}
		select{
			&.custom-select-sm{
				font-size: 100%;
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
	}
	.custom-control-inline {
		margin-right: 0;
		display: initial;
	}
	
	.custom-control {
		padding-left: 24px; 
	    padding-left: 1.5rem;
	
		.custom-control-label {
			&::before {
				top: -12px;
		    	top: -0.75rem;
				left: -20px;
			    left: -1.25rem;
			}
			
			&::after {
				top: -12px;
		    	top: -0.75rem;
				left: -20px;
			    left: -1.25rem;
			}
		}
	}
	
	.form-row {
		display: block;
	
		label, div {
			display: inline-block;
		}
	}
	
	.bg-light {
		width: 100%;
	}
	
	#status {
		.custom-control-label {
			&::before {
				top: 0;
		    	top: 0;
			}
			
			&::after {
				top: 0;
		    	top: 0;
			}
		}
	}
	
	.small-input {
		width:80px;
	}
</style>