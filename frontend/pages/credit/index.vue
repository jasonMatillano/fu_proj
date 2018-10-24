<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="3" md="6" sm="12">
				<section class="field-input mb-2">
					<b-col sm="12" md="9" class="p-0">
						<b-form-input v-model="filter" :placeholder="filter_lang" size="sm"/>
					</b-col>
				</section>
				<section class="field-table">
					<b-table striped
					         bordered
				         	 small
					         :items="data"
					         :fields="field"
					         hover
					         :filter="filter"
					         @row-dblclicked="dblClickTable">
					</b-table>
				</section>
			</b-col>
			<b-col xl="3" md="6" sm="12">
				<section class="field-form">
					<transition name="fade">
						<b-card bg-variant="light" v-if="formShow">
							<b-col class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>単位　＞　{{processName}}</span></h5>
							</b-col>
							<c_form :formdata="prevData" :processName="processName" @clickCancel="clickCancel" @submit="submit"></c_form>
						</b-card>
					</transition>
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
	import c_form from '@/components/forms/credit'
	import c_modal from '@/components/modal'
	@Component({
		components:{
			c_footer,
			c_form,
			c_modal
		},
	})
	export default class Credit extends Vue {

			formShow = false
			prevData = {}
			processName = ''
			filter = null
			submittedData = null

			// change lang
			title_lang = ""
			filter_lang = ""
			unit_name_lang = ""

			created() {
				this.title_lang = this.$t("Auth.pages.credit.title").toString()
				this.filter_lang = this.$t("Auth.pages.credit.filter").toString()

				this.unit_name_lang = this.$t("Auth.pages.credit.unit_name").toString()
				this.$set(this.field[1], "label", this.unit_name_lang)
			}

			field = [
				{key: 'code', label: 'CD',class: 'text-center table-col-width-50',sortable: true},
				{key: 'name', label: '単位名',sortable: true}
			]
			data = [
				{code: '01',name: '個1'},
				{code: '02',name: '個2'},
				{code: '03',name: '個3'},
				{code: '04',name: '個4'},
				{code: '05',name: '個5'},
				{code: '06',name: '個6'},
				{code: '07',name: '個7'},
				{code: '08',name: '個8'},
				{code: '09',name: '個9'},
				{code: '10',name: '個10'},
				{code: '11',name: '個11'},
				{code: '12',name: '個12'},
				{code: '13',name: '個13'},
				{code: '14',name: '個14'},
				{code: '15',name: '個15'},
				{code: '16',name: '個16'},
				{code: '17',name: '個17'},
				{code: '18',name: '個18'},
				{code: '19',name: '個19'},
				{code: '20',name: '個20'},
				{code: '21',name: '個21'},
				{code: '22',name: '個22'},
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
					this.$set(this.prevData,"code",this.submittedData.code)
					this.$set(this.prevData,"name",this.submittedData.name)
				}else{
					this.data.push(this.submittedData)
				}
				this.processName = ''
			}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
</style>