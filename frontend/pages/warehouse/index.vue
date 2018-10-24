<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="8" md="12">
				<section class="field-input mb-2">
					<b-col xl="4" class="p-0">
						<b-form-input v-model="filter" :placeholder="filter_lang" size="sm"/>
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
							empty-text="表示するデータがありません"
							show-empty
							@row-dblclicked="dblClickTable">
					</b-table>
				</section>
			</b-col>
			<b-col xl="4" md="6">
				<section class="field-form">
					<transition name="fade">
						<b-card bg-variant="light" v-if="formShow">
							<b-col class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>倉庫 ＞ {{prevData["id"] ? "変更" : "新規登録"}}</span></h5>
							</b-col>
							<c-form :formdata="prevData" @submit="submit" @clickCancel="hideForm"></c-form>
						</b-card>
					</transition>	
				</section>
			</b-col>
		</b-row>
		<c-modal @submit="confirmSubmit" idMod="confirm"></c-modal>
		<c-footer class="content-footer" :events="UIEvents" @eventTriggered="eventHandler"></c-footer>
	</b-container>
</template>
<script lang="ts">
	import { Component,Watch, Vue } from 'nuxt-property-decorator'

	import cFooter from '@/components/footer.vue'
	import cForm from '@/components/forms/warehouse'
	import cModal from '@/components/modal'
	import {UIEvent} from "@/assets/ts/UIEvent.ts"
	import {Keys} from "@/assets/ts/keys.ts"
	import { Utils } from "@/assets/ts/Utils.ts"

	@Component({
		components:{
			cFooter,
			cForm,
			cModal
		},
	})
	export default class Warehouse extends Vue{

		//CRUD
		pageId = { id : "JMST10"}
		data = []
		util:any=null
		submittedData = null
		formShow = false
		prevData = {}
		filter = null

		UIEvents:Array<UIEvent>=[ 
			new UIEvent(Keys.F2,'取消',true),
			new UIEvent(Keys.F4,'新規登録',true),
			new UIEvent(Keys.F12,'メニュ',true),
		]

		async fetchData(){
			this['$axios'].$get('/api/warehouses/')
				.then(response => {
					this.data = response
				})
				.catch(err => {
					console.log(err)
				})
		}

		saveOrUpdate(data : any){
			this['$axios'].$patch('/api/warehouses/',data)
				.then(response =>{
					this.fetchData()
					this.hideForm()
				})
				.catch(err =>{
					console.log(err)
				})
		}

		hideForm(){
			this.reset_color()
			this.prevData = {}
			this.formShow = false
		}

		create(){
			this.reset_color()
			this.prevData = {}
			this.formShow = true
		}

		eventHandler(event) {    
		    switch(event.key){
		      case Keys.F2:
		        this.hideForm();
		      break;
		      case Keys.F4:
		        this.create();
		      break;
		    }
		}

		//all called functions
		reset_color(){
			if(this.prevData !== null){
				Vue.set(this.prevData,"_rowVariant","")
			}
		}
		set_color(item){
			if(this.prevData["id"] != null){
				this.prevData = item
				Vue.set(item, "_rowVariant", "info");
			}
		}

		mounted(){
			this.fetchData()
		}

		// change lang
		title_lang = ""
		filter_lang = ""

		whs_name_lang = ""
		whs_kana_lang = ""
		whs_abbreviation_lang = ""
		whs_postal_code_lang = ""
		whs_address_1_lang = ""
		whs_street_address_1_lang = ""
		whs_phone_number_lang = ""
		whs_fax_number_lang = ""

		created() {
			this.title_lang = this.$t("Auth.pages.warehouse.title").toString()
			this.filter_lang = this.$t("Auth.pages.warehouse.filter").toString()

			this.whs_name_lang = this.$t("Auth.pages.warehouse.whs_name").toString()
			this.whs_kana_lang = this.$t("Auth.pages.warehouse.whs_kana").toString()
			this.whs_abbreviation_lang = this.$t("Auth.pages.warehouse.whs_abbreviation").toString()
			this.whs_postal_code_lang = this.$t("Auth.pages.warehouse.whs_postal_code").toString()
			this.whs_address_1_lang = this.$t("Auth.pages.warehouse.whs_address_1").toString()
			this.whs_street_address_1_lang = this.$t("Auth.pages.warehouse.whs_street_address_1").toString()
			this.whs_phone_number_lang = this.$t("Auth.pages.warehouse.whs_phone_number").toString()
			this.whs_fax_number_lang = this.$t("Auth.pages.warehouse.whs_fax_number").toString()

			this.$set(this.field[1], "label", this.whs_name_lang)
			this.$set(this.field[2], "label", this.whs_kana_lang)
			this.$set(this.field[3], "label", this.whs_abbreviation_lang)
			this.$set(this.field[4], "label", this.whs_postal_code_lang)
			this.$set(this.field[5], "label", this.whs_address_1_lang)
			this.$set(this.field[6], "label", this.whs_street_address_1_lang)
			this.$set(this.field[7], "label", this.whs_phone_number_lang)
			this.$set(this.field[8], "label", this.whs_fax_number_lang)
		}


		field = [ 
			{key: 'code', label: 'CD',sortable:true ,class:'text-center table-col-width-45'},
		    {key: 'name',label:'倉庫名',sortable:true, class: ''},
		    {key: 'kana',label: '倉庫ｶﾅ',sortable:true,class: 'table-col-width-90'},
		    {key: 'shortname',label:'倉庫略称',sortable: true, class:''},
		    {key: 'postal_code',label:'〒',class:'text-center table-col-width-80'},
		    {key: 'addr_one',label: '住所１'},
		    {key: 'addr_two',label: '住所２'},
		    {key: 'phone_num',label: '電話番号',class: 'text-center table-col-width-90'},
		    {key: 'fax_num',label: 'FAX番号',class: 'text-center table-col-width-90'}
		]

	    //all click event function
		dblClickTable(item){
			this.formShow = true
			this.reset_color()
			this.prevData = item
			this.set_color(item)
		}

		submit(value:any){
			value.data.updated_at = this.pageId
			value.data.updated_by = 0

			if(value.data["id"] == null){ // //REGISTER
				value.data.created_at = this.pageId
				value.data.created_by = 0
				value.data.isResigned = false
			}

			this.submittedData = value.data
			console.log("value",value)
		}
		confirmSubmit(){
			this.saveOrUpdate(this.submittedData)
		}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
</style>