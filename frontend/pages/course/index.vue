<template>
    <b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{ coursePage.title }}</h5>
				<!-- <p> 
					currCourse: {{currCourse}} courseLen: {{courseItems.length}} <br>
					currSubCourse: {{currSubCourse}} subCourseLen: {{subCourseItems.length}} <br>
					section_name: {{section_name}} <br>
					processName: {{processName}} <br>
					formData => {{ formData }}				
				</p> -->
				<!-- <p>
					modalPrShowing: {{modalPrShowing}} <br>
					modalType: {{modalType}}
				</p> -->
			</b-col>
		</b-row>
        <b-row class="content-body m-0">
			<!-- Products Section-->
			<b-col xl="4" @click="prodFirstClickDefault()">
				<section class="mb-2 field-input">
					<b-col md="8" sm="12" class="p-0">
						<b-row>
							<b-col xl="8">
								<b-form-input
									v-model="filterCourseSearch" 
									:placeholder="coursePage.filter" 
									size="sm"/> 
							</b-col>
							<b-col xl="4">
								<b-form-group
									horizontal 
									:label-cols="6" 
									label-size="sm" 
									:label="coursePage.end" 
									label-for="input_sm" 
									class="m-0"
									>
									<b-form-checkbox 
										id="end_page"
										v-model="filterProdIsEnd"
										@input="endProdClick()"
									></b-form-checkbox>
								</b-form-group>
							</b-col>
						</b-row>
					</b-col>
				</section>
				<section class="field-table">		
					<b-table
						striped
						small
						bordered
						responsive
						:sort-by.sync="sortBy"
             			:sort-desc.sync="sortDesc"
						:items="courseItems"
						:fields="courseFields"
						hover
						:filter="filterCourseSearch"
						@row-clicked="changeHighLightCourse"
						@row-dblclicked="buttonProcess({_eventName:coursePage.edit})"
					>
						<template slot="course_end" slot-scope="data">
							{{data.value == true ? '終了' : ' '}}
						</template>
					</b-table>
				</section>
			</b-col>
			<!-- /Products Section-->

			<!-- Form Section-->
				<b-col xl="4">
					<transition name="fade">
						<section class="field-form"  v-if="formShow">
							<b-row>
								<b-col :class="arrowPosition"><span><fa :icon="tableArrow" size="2x"></fa></span></b-col>
							</b-row>
							<b-card bg-variant="light">
								<c_form
									:section_name="section_name"
									:processName="processName"
									:formIsDisabled="formIsDisabled"
									:formData="formData"
									@clickCancel="closeForm"
									@submitForm="submitForm"
									@changeCD="formData.val_code = $event"
									@changeName="formData.val_name = $event"
									@changeEndVal="formData.val_end = $event"
								></c_form>
							</b-card>
						</section>
					</transition>
				</b-col>
			<!-- /Form Section-->

			<!-- SubCourse Section-->
			<b-col xl="4" @click="subProdFirstClickDefault()">
				<section class="mb-2 field-input">
					<b-col md="8" sm="12" class="p-0">
						<b-row>
							<b-col xl="8">
								<b-form-input 
									v-model="filterSubCourseSearch" 
									:placeholder="coursePage.filter" 
									size="sm"
									/>
							</b-col>
							<b-col xl="4">
								<b-form-group 
									horizontal
									:label-cols="6"
									label-size="sm"
									label-for="input_sm"
									class="m-0"
									:label="coursePage.end"
									>
									<b-form-checkbox 
										id="chkbox3" 
										value="true" 
										unchecked-value="false" 
										v-model="checkBranchState" 
										@change="showDeletedBranch"
										/>
								</b-form-group>
							</b-col>
						</b-row>
					</b-col>
				</section>
				<section class="field-table">
					<b-table striped
						bordered
						small
						responsive
						:items="subCourseItems"
						:fields="subCourseFields"
						hover
						:filter="filterSubCourseSearch"
						@row-clicked="changeHighLightSubCourse"
						@row-dblclicked="buttonProcess({_eventName:coursePage.edit})"
						>
					<template slot="sub_course_end" slot-scope="data">
					 	{{data.value == true ? '終了' : ' '}}
					</template>
					</b-table>
				</section>
			</b-col>
			<!-- /SubCourse Section-->
        </b-row>
		<c_modal_pr
			:modalPrShowing="modalPrShowing"
			:modalType="modalType"
			:section_name="section_name"
			@closeModalPr="closeModalPr"
		></c_modal_pr>
		<c_footer class="content-footer" 
			@eventTriggered="fnClickOrKeyDown" 
			:events="UIEvents"
		></c_footer>
    </b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator'
	import c_form from '@/components/forms/course'
	import c_modal_pr from '@/components/modal_pr'

	// import c_footer from '@/components/footer_cr'
	//import c_modal_course from '@/components/modal/contents/course/course_modal's

	import {UIEvent} from "@/assets/ts/UIEvent.ts"
	import {Keys} from "@/assets/ts/keys.ts"
	import c_footer from '@/components/footer.vue'

	@Component({
		components:{
			c_footer,
			c_form,
			c_modal_pr,
			//c_modal_course
		},
	})

	export default class Course extends Vue {

			// language type variables en/ja
			coursePage = null
			$t = null

			// footer key values
			UIEvents:Array<UIEvent> = []

			// table contents
			courseFields =  [
							{key: 'course_code', label: 'CD',class:"text-center table-col-width-50",sortable: true},
							{key: 'course_name', label: '主科目名',class:"w-50"},
							{key: 'course_end', label: 'End', class:'table-col-width-50 text-center'}
			]

			subCourseFields = [
							{key: 'sub_course_code', label: 'CD',class:"text-center table-col-width-50"},
							{key: 'sub_course_name', label: '補助科目名'},
							{key: 'sub_course_end', label: 'End', class:'table-col-width-50 text-center'}
			]

			courseItems = [
							{course_code: '100', course_name: '小囗現金', course_end: true},
							{course_code: '101', course_name: '回座預金', course_end: true},
							{course_code: '110', course_name: '道知預金', course_end: false},
							{course_code: '111', course_name: '定期預金', course_end: true},
							{course_code: '112', course_name: '表掛金 ', course_end: false},
							{course_code: '113', course_name: '表掛金 ', course_end: false},
							{course_code: '115', course_name: '表掛金 ', course_end: false},
							{course_code: '116', course_name: '表掛金 ', course_end: false},
							{course_code: '117', course_name: '表掛金 ', course_end: false},
							{course_code: '118', course_name: '表掛金 ', course_end: false},
							{course_code: '119', course_name: '表掛金 ', course_end: false},
							{course_code: '120', course_name: '表掛金 ', course_end: false},
							{course_code: '121', course_name: '表掛金 ', course_end: false},
							{course_code: '122', course_name: '表掛金 ', course_end: false},
							{course_code: '123', course_name: '表掛金 ', course_end: false},
							{course_code: '124', course_name: '表掛金 ', course_end: false},
							{course_code: '125', course_name: '表掛金 ', course_end: false},
			]

			subCourseItems = []

			// tables search filters
			filterCourseSearch = ""
			filterSubCourseSearch = ""
			
			// 終了/End filter
			filterCourseIsEnd = false
			filterSubCourseIsEnd = false

			// value holders for last clicked row in prod/sub-rpod
			currCourse = { course_code: '', course_name: '', course_end: true }
			currSubCourse = { sub_course_code: '', sub_course_name:'', sub_course_end: true}

			// form value holders
			section_name = ""
			formIsDisabled = false
			formData = { val_code: '', val_name: '', val_end: false , val_ind: 0}

			// arrows
			arrowPosition = ""
			tableArrow = ""

			created() {
				this.setPageLanguage()
			}

			beforeMount() {
				this.assignFooterKeys()
				this.setTableFieldsLang()
				this.setTableInitialValues()
			}
			
			setPageLanguage() {
				this.coursePage = this.$t('Auth.pages.course')
			}

			assignFooterKeys() {
				this.UIEvents.push(
					new UIEvent(Keys.F2,this.coursePage.cancel,true),
					new UIEvent(Keys.F3,this.coursePage.delete,true),
					new UIEvent(Keys.F4,this.coursePage.new,true),
					new UIEvent(Keys.F6,this.coursePage.date,true),
					// new UIEvent(Keys.F8,'リスト',true),
					new UIEvent(Keys.F12,this.coursePage.menu,true),
				)
			}

			setTableFieldsLang () {
				this.$set(this.courseFields[1], "label", this.coursePage.course_name)
				this.$set(this.courseFields[2], "label", this.coursePage.end)

				this.$set(this.subCourseFields[1], "label", this.coursePage.sub_course_name)
				this.$set(this.subCourseFields[2], "label", this.coursePage.end)
			}

			setTableInitialValues() {
				this.setCourseIndex() 
				this.changeCourseSubItems(0)
				this.changeHighLightCourse(this.courseItems[0])// default row highlight for products	
			}

			setCourseIndex() {
				var i = 0;
				this.courseItems.forEach(element => {
					this.$set(this.courseItems[i], "ind", i)
					i++
				});
			}

			changeHighLightCourse(record) {
				this.changeArrowToProd()
				this.chooseRowToHighLightInCourse(record)
				this.populateCurrentCourse(record)
				this.setVarForFormContentCourse(record)
				this.changeFormContent()
				this.changeCourseSubItems(record.course_code)// change sub-items
			}

			changeArrowToProd() {
				this.arrowPosition = "text-left"
				this.tableArrow = "arrow-alt-circle-left"
			}

			chooseRowToHighLightInCourse(record) {
				this.courseItems.forEach(element => {
					if(record.course_code == element.course_code) {
						this.$set(element, "_rowVariant", "info")
					} else {
						this.$set(element, "_rowVariant", "")
					}
				});
			}

			populateCurrentCourse(record) {
				this.currCourse = record
			}

			setVarForFormContentCourse(record){
				this.section_name = this.coursePage.course_name
				this.formData.val_code = record.course_code
				this.formData.val_name = record.course_name
				this.formData.val_ind = record.ind
				this.formData.val_end = record.course_end
			}

			changeHighLightSubCourse(record) {
				this.changeArrowToSubCourse()
				this.chooseRowToHighLightInSubCourse(record)
				this.populateCurrSubCourse(record)
				this.setVarForFormContentSubCourse(record)
				this.changeFormContent()
			}

			changeArrowToSubCourse() {
				this.arrowPosition = "text-right"
				this.tableArrow = "arrow-alt-circle-right"
			}

			chooseRowToHighLightInSubCourse(record) {
				this.subCourseItems.forEach(element => {
					if(record.sub_course_code == element.sub_course_code) {
						this.$set(element, "_rowVariant", "info")
					} else {
						this.$set(element, "_rowVariant", "")
					}
				});	
			}

			populateCurrSubCourse(record) {
				this.currSubCourse = null
				this.currSubCourse = record
			}

			setVarForFormContentSubCourse(record){
				this.section_name = this.coursePage.sub_course_name
				this.formData.val_code = record.sub_course_code
				this.formData.val_name = record.sub_course_name
				this.formData.val_ind = record.ind
				this.formData.val_end = record.subCourse_end
			}

				// Fn key process
			fnClickOrKeyDown(val){
				this.processFooterValues(val)
			}

			processFooterValues(val) {
				// alert(JSON.stringify(val))
				switch(val._eventName) {
					// perform delete and create operation
					case "新規":
					case "New":
					case "削除":
					case "Delete":
						// alert(JSON.stringify(val))
						this.buttonProcess(val)
					break;
					
					// perform cancel operation
					case "取消":
					case "Cancel":
						// alert(JSON.stringify(val))
						this.closeForm()
					break;

					// perform date and list modals
					case "日付":
					case "Date":
					case "リスト":
					case "List":
						// alert(JSON.stringify(val))
						this.openModalPr(val)
					break;

					// to to main menu
					case "メニュ":
					case "Menu":
						// alert(JSON.stringify(val))
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

			// show form 
			processName = ''
			formShow = false
			buttonProcess(value){
				this.formShow = true // toggle show
				this.processName = value._eventName
				this.changeFormContent()
			} 

			// close form
			closeForm() {
				this.formShow = false
				this.processName = ''
			}

			changeFormContent () {
				switch(this.section_name) {
					case "主科目名":
					case "Course":
						{
							switch(this.processName) {
								case "新規":
								case "New":
									this.formData.val_code = ""
									this.formData.val_name = ""
									this.formData.val_end = false
									this.formData.val_ind = 0
									this.formIsDisabled = false		
									break;
								case "削除":
								case "Delete":
									if(this.isProdEmpty() == true) {
										alert('Prod is empty')
										this.formShow = false 
									} else {
										this.formData.val_code = this.currCourse.course_code
										this.formData.val_name = this.currCourse.course_name
										this.formData.val_end = this.currCourse.course_end
										this.formIsDisabled = true
									}	
									break;
								case "変更":
								case "Edit":
									this.formData.val_code = this.currCourse.course_code
									this.formData.val_name = this.currCourse.course_name
									this.formData.val_end = this.currCourse.course_end
									this.formIsDisabled = false
									break;
								case "":
									// do nothing
									break;
								default:
									alert("incorrect process name, please check again")
								break;							
							}
						
						}
						break;
					case "補助科目名":
					case "Sub_Course":
						{
							switch(this.processName) {
								case "新規":
								case "New":
									this.formData.val_code = ""
									this.formData.val_name = ""
									this.formData.val_end = false
									this.formData.val_ind = 0
									this.formIsDisabled = false		
									break;
								case "削除":
								case "Delete":
									if(this.isSubCourseEmpty() == true) {
										alert('SubProd is empty')
										this.formShow = false 
									} else {
										this.formData.val_code = this.currSubCourse.sub_course_code
										this.formData.val_name = this.currSubCourse.sub_course_name
										this.formData.val_end = this.currSubCourse.sub_course_end
										this.formIsDisabled = true
									}
									break;
								case "変更":
								case "Edit":
									this.formData.val_code = this.currSubCourse.sub_course_code
									this.formData.val_name = this.currSubCourse.sub_course_name
									this.formData.val_end = this.currSubCourse.sub_course_end
									this.formIsDisabled = false
									break;
								case "":
									// do nothing
									break;
								default:
									alert("incorrect process name, please check again")
									break;
							}
						}
					default:
						// do nothing
						break;
				}
			}

			// check if prod Is Empty
			isProdEmpty() {
				if (this.courseItems.length == 0){
					return true;
				} 
				else {
					return false;
				}
			}

			// check if sub-prod Is Empty
			isSubCourseEmpty() {
				if (this.subCourseItems.length == 0){
					return true;
				} 
				else {
					return false;
				}
			}
		
			// change sub items when prod row-click
			changeCourseSubItems(val) {
				// alert(JSON.stringify(val))
				this.subCourseItems = []
				switch(val) {
					case "100":
						this.subCourseItems.push(
							{sub_course_code: '0001',sub_course_name:'あ定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0002',sub_course_name:'た定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0003',sub_course_name:'が定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0004',sub_course_name:'か定拠出年金支店', sub_course_end: false},
						)	
						break;
					case "101":
						this.subCourseItems.push(
							{sub_course_code: '0001',sub_course_name:'あ定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0002',sub_course_name:'た定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0003',sub_course_name:'が定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0004',sub_course_name:'か定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0005',sub_course_name:'ま定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0006',sub_course_name:'な定拠出年金支店', sub_course_end: true},
						)
						break;
					case "110":
						this.subCourseItems.push(
							{sub_course_code: '0001',sub_course_name:'あ定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0002',sub_course_name:'た定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0003',sub_course_name:'が定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0004',sub_course_name:'か定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0005',sub_course_name:'ま定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0006',sub_course_name:'な定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0007',sub_course_name:'は定拠出年金支店', sub_course_end: true},
						)
						break;
					case "111":
						this.subCourseItems.push(
							{sub_course_code: '0001',sub_course_name:'あ定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0002',sub_course_name:'た定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0003',sub_course_name:'が定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0004',sub_course_name:'か定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0005',sub_course_name:'ま定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0006',sub_course_name:'な定拠出年金支店', sub_course_end: false},
							{sub_course_code: '0007',sub_course_name:'は定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0008',sub_course_name:'確ほ拠出年金支店', sub_course_end: true},
						)
						break;
					case "":
						this.subCourseItems.push(
							{sub_course_code: '0001',sub_course_name:'あ定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0002',sub_course_name:'た定拠出年金支店', sub_course_end: false},
						)
						break;
					default:
						this.subCourseItems.push(
							{sub_course_code: '0001',sub_course_name:'あ定拠出年金支店', sub_course_end: true},
							{sub_course_code: '0002',sub_course_name:'た定拠出年金支店', sub_course_end: true},
						)
					break;
				}
				this.setSubCourseIndex()
				this.currSubCourse = null
				this.currSubCourse = this.subCourseItems[0]
				this.$set(this.subCourseItems[0], "_rowVariant", "info")
			}

			setSubCourseIndex() {
				var i = 0;
				this.subCourseItems.forEach(element => {
					this.$set(this.subCourseItems[i], "ind", i)
					i++
				});
			}

			// modal_pr values and methods
			modalPrShowing = false
			modalType= ""
			openModalPr(val) {
				this.modalPrShowing = true
				this.modalType = val._eventName
			}

			closeModalPr(val){
				this.modalPrShowing = false
			}

			// redirect to menu
			goToMenu() {
				this["$router"].push('/mymenu')
			}

			// endProdClick
			endProdClick() {
				alert("checkbox cliked")
			}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
</style>
