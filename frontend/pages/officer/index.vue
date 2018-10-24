<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="6" md="12">
				<section class="field-input mb-2">
					<b-col class="p-0" md="4" sm="12">
						<b-form-input v-model="filter" :placeholder="filter_lang" size="sm"/>
					</b-col>
				</section>
				<section class="field-table">
					<b-table 
							striped 
							bordered 
							small 
							:items="userData" 
							:fields="field" 
							:filter="filter" 
							hover
							class="m-0"
							@row-dblclicked="dblClickTable">
			        		<template slot="isResigned" slot-scope="data">
								   <p class="m-0">{{data.value ? '終了' : ' '}}</p>
			        		</template>
					</b-table>
				</section>
			</b-col>
			<b-col xl="4" md="6" sm="12">
				<section class="field-form">
					<transition name="fade">
							<b-card bg-variant="light" v-if="formShow">
								<b-col class="p-0">
									<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>担当者 ＞　{{prevData["id"] ? "変更" : "新規登録"}}</span></h5>
								</b-col>
								<c_form :formdata="prevData" :processName="processName" @submit="submit" :deptdata="deptData" @clickCancel="hideForm"></c_form>
							</b-card>
					</transition>
				</section>
			</b-col>
		</b-row>
		<c_modal @submit="confirmSubmit" idMod="confirm"></c_modal>
		<c_footer class="content-footer" :events="UIEvents" @eventTriggered="eventHandler" ></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator'

	import c_footer from '@/components/footer.vue'
	import c_form from '@/components/forms/officer'
	import c_modal from '@/components/modal'
	import {UIEvent} from "@/assets/ts/UIEvent.ts"
	import {Keys} from "@/assets/ts/keys.ts"

	@Component({
		components:{
			c_footer,
			c_form,
			c_modal
		},
	})
	export default class Officer extends Vue {

		//progress
		userData = []
		deptData:any = []
		pageId = { id : "JMST02"}
		UIEvents:Array<UIEvent>=[ 
			new UIEvent(Keys.F2,'取消',true),
			new UIEvent(Keys.F4,'新規登録',true),
			new UIEvent(Keys.F12,'メニュ',true),
		]

		async fetchData(){       

	    	this['$axios'].$get('/api/user/').then(resp=>{
	    		this.userData = resp
	    	}).catch(error=>{
	    		console.log(error)
	    	})

	    	this['$axios'].$get('/api/department/').then(resp=>{
	    		this.deptData = resp
	    	}).catch(error=>{
	    		console.log(error)
	    	})
	    }

	    saveOrUpdate(data:any){
	    	this['$axios'].$patch('/api/user/',data).then(resp => {
	    			this.fetchData();
                    this.hideForm();
	    	}).catch(ex=>console.log("exception: "+ex))
	    }

	    mounted(){
			this.fetchData()
	    }

		formShow = false
		prevData = {}
		processName = ''
		filter = null
		submittedData = null
		
		// change lang 
		title_lang = ''
		filter_lang = ''

		mst_u_user_id_lang = ''
		mst_u_username_lang = ''
		username_kana_lang = ''
		mst_u_dept_id_lang = ''
		department_name_lang = ''
		resigned_lang = ''

		created() {
			this.title_lang = this.$t("Auth.pages.officer.title").toString()
			this.filter_lang = this.$t("Auth.pages.officer.filter").toString()

			this.mst_u_user_id_lang = this.$t("Auth.pages.officer.mst_u_user_id").toString()
			this.mst_u_username_lang = this.$t("Auth.pages.officer.mst_u_username").toString()
			this.username_kana_lang = this.$t("Auth.pages.officer.username_kana").toString()
			this.mst_u_dept_id_lang = this.$t("Auth.pages.officer.mst_u_dept_id").toString()
			this.department_name_lang = this.$t("Auth.pages.officer.department_name").toString()
			this.resigned_lang = this.$t("Auth.pages.officer.resigned").toString()

			this.$set(this.field[0], "label", this.mst_u_user_id_lang)
			this.$set(this.field[1], "label", this.mst_u_username_lang)
			this.$set(this.field[2], "label", this.username_kana_lang)
			this.$set(this.field[3], "label", this.mst_u_dept_id_lang)
			this.$set(this.field[4], "label", this.department_name_lang)
			this.$set(this.field[5], "label", this.resigned_lang)
		}

		field = [ 
		  	{key:'user_code',label:'担当者ID',class: 'text-center table-col-width-65 align-middle'},
		    {key:'username',label:'担当者名',class:'align-middle'},
		    {key:'username_kana',label:'担当者ｶﾅ',class:'align-middle'},
		    {key:'mst_u_dept_id',label:'部門ID',class: 'text-center table-col-width-50 align-middle'},
		    {key:'department_name',label:'部門名',class: 'align-middle'},
		    {key:'isResigned',label:'終了',class:"table-col-width-65 text-center"}
		]
		data = [/*
			{mst_u_user_id: '001',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '日本太郎',username_kana: 'ニホンタロウ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: false},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: false},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: false},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: false},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: false},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: false},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: false},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: false},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},
			{mst_u_user_id: '000',mst_u_username: '花道桜木',username_kana: 'ハナミチサクラギ',mst_u_dept_id: '0000',department_name: '東京駅',resigned: true},*/

		]

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

		//To be change
		submit(value:any){
			value.data.password = value.password //execute at all time
			value.data.updated_at = this.pageId
			value.data.updated_by = 0

			if(value.department != 0)
				value.data.department = { "id" : value.department}
			else
				value.data.department = { "id" : null}

			if(value.data["id"] == null){ // //REGISTER
				value.data.created_at = this.pageId
				value.data.created_by = 0
				value.data.isResigned = false
			}

			this.submittedData = value.data
			console.log("value",value)
		}
		confirmSubmit(){
			console.log("submit",this.submittedData)
			this.saveOrUpdate(this.submittedData)



			/*if(this.processName == "変更"){
				this.$set(this.prevData,"mst_u_user_id",this.submittedData.mst_u_user_id)
				this.$set(this.prevData,"mst_u_username",this.submittedData.mst_u_username)
				this.$set(this.prevData,"username_kana",this.submittedData.username_kana)
			}else{
				this.data.push(this.submittedData)
			}*/
			this.processName = ''
		}
	}
</script>
<style lang="scss"	>
	@import '@/assets/scss/common.scss';
</style>