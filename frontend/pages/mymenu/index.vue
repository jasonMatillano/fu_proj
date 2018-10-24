<template>
	<b-container fluid id="wg-master">
		<b-row class="content-title py-2">
			<b-col>
				<h5 class="m-0">マイメニュ</h5>
			</b-col>
		</b-row>
		<b-row class="content-body">
			<b-col>
				<div class="page">
					<div class="header">
						<b-row>
							<b-col>
								<span>佐藤 義則</span>
							</b-col>
							<b-col>
								<span>マイメニュ</span>
							</b-col>
							<b-col>
								<span>編集番号</span>
							</b-col>
						</b-row>
					</div>
					<div id="sub-menu-container">
		            <div class="sub-menu">
		              <b-container fluid class="content">
		                <b-row>
		                  <b-col>                   
		                      <b-container fluid>
		                        <b-row v-for="program in programsContainer.slice(0,10)" :key="program.sequenceNumber"> 
		                          <b-col class="no-pad-col">
		                            <div class="item">
		                               <a href="javascript:void(0);" 
		                                  @click="editProgramContainer(program)" 
		                                  class="sub-menu-item">										  	
											<span>{{[program.sequenceNumber]}}</span>
											<transition name="fade">															
												<a v-if="program.costumizeMenuData" @click="deleteProgram(program)" v-b-modal="'confirm-delete-modal'" class="delete-programs-btn"><span key=1>クリア</span></a>					
											</transition>
											<transition name="fade">
												<span v-if="program.costumizeMenuData">{{program.costumizeMenuData.program.program.text}}</span>
											</transition>
		                               </a>                               
		                            </div>                                        
		                          </b-col>
		                        </b-row>
		                    </b-container>
		                  </b-col>
		                  <b-col>     
		                      <b-container fluid>
		                        <b-row v-for="program in programsContainer.slice(10,20)" :key="program.sequenceNumber">
		                          <b-col class="no-pad-col">
		                            <div class="item">
		                               <a href="javascript:void(0);"
		                                  @click="editProgramContainer(program)"
		                               	  class="sub-menu-item">
		                               	<span>{{[program.sequenceNumber]}}</span>																		
										<transition name="fade">															
												<a v-if="program.costumizeMenuData" @click="deleteProgram(program)" v-b-modal="'confirm-delete-modal'" class="delete-programs-btn">
													<span key=1>クリア</span>
												</a>					
										</transition>
										<transition name="fade">
											<span v-if="program.costumizeMenuData">{{program.costumizeMenuData.program.program.text}}</span>
										</transition>
		                               	</a>                               
		                            </div>                          
		                          </b-col>
		                        </b-row>
		                    </b-container>
		                  </b-col>
		                  <b-col>                   
		                      <b-container fluid>
		                        <b-row  v-for="program in programsContainer.slice(20,30)" :key="program.sequenceNumber"> 
		                          <b-col class="no-pad-col">
		                            <div class="item">
		                                <a href="javascript:void(0);" 
		                                  @click="editProgramContainer(program)" 
		                                  class="sub-menu-item">
		                                	<span>{{[program.sequenceNumber]}}</span>																		
											<transition name="fade">															
												<a v-if="program.costumizeMenuData" @click="deleteProgram(program)" v-b-modal="'confirm-delete-modal'" class="delete-programs-btn"><span key=1>クリア</span></a>					
											</transition>
											<transition name="fade">
												<span v-if="program.costumizeMenuData">{{program.costumizeMenuData.program.program.text}}</span>
											</transition>
		                              	</a>                  	
		                            </div>                                        
		                          </b-col>
		                        </b-row>
		                    </b-container>
		                  </b-col>                
		                </b-row>
		              </b-container>
		             </div>
		          </div>
				</div>		
			</b-col>
		</b-row>
		
		<b-modal id="Update" v-model="programChoicesListModal" hide-footer no-close-on-backdrop>	        
			<b-table striped
								bordered				         
								small
								foot-clone
								foot-variant="dark"
								:items="categoryWithProgramsSelectionItems"
								:fields="categoryWithProgramsSelectionFields"
								hover>
						<template slot="program_name" slot-scope="row">				  			
								<div class="row-header"><span><fa :icon="['far','object-group']"/></span>{{row.item.category_name}}</div>
								<tr v-for="program in row.item.programs" :key=program.id >
									<td class="foo">
										<b-button @click="selectProgram(program)" 
												  v-b-modal="'confirm-add-modal'"
												  class="program-item"
												  v-bind:class="{isActive : isSelectedProgram(program)}" >									 
											<span>{{program.text}}</span>
										</b-button>
									</td>				  				  			
								</tr>				  				
						</template>
			</b-table>			
		</b-modal>
		<confirmation_modal idMod="confirm-delete-modal" @submit="confirmDelete"></confirmation_modal>		
		<confirmation_modal idMod="confirm-add-modal" @submit="confirmAdd"></confirmation_modal>		
	</b-container>

</template>
<script lang="ts">
	import Lang from "@/components/lang.vue";
	import { Component, Vue } from 'nuxt-property-decorator'
	import axios from 'axios'
	import programDeleteConfirmation_modal from '@/components/modal'
	import confirmation_modal from '@/components/modal'

	@Component({
		components: { Lang,					  
					  confirmation_modal
					}
	})
	export default class MyMenu extends Vue {

		 programChoicesListModal = false
		 latestSelectedProgramContainer:any = {}
		 isProgramDeleted = false
		 confirmModal = false
		 //programs containers list
	     programsContainer:object[] = [
	        {sequenceNumber:1,costumizeMenuData:null},
	        {sequenceNumber:2,costumizeMenuData:null},
	        {sequenceNumber:3,costumizeMenuData:null},
	        {sequenceNumber:4,costumizeMenuData:null},
	        {sequenceNumber:5,costumizeMenuData:null},
	        {sequenceNumber:6,costumizeMenuData:null},
	        {sequenceNumber:7,costumizeMenuData:null},
	        {sequenceNumber:8,costumizeMenuData:null},
	        {sequenceNumber:9,costumizeMenuData:null},
	        {sequenceNumber:10,costumizeMenuData:null},
	        {sequenceNumber:11,costumizeMenuData:null},
	        {sequenceNumber:12,costumizeMenuData:null},
	        {sequenceNumber:13,costumizeMenuData:null},
	        {sequenceNumber:14,costumizeMenuData:null},
	        {sequenceNumber:15,costumizeMenuData:null},
	        {sequenceNumber:16,costumizeMenuData:null},
	        {sequenceNumber:17,costumizeMenuData:null},
	        {sequenceNumber:18,costumizeMenuData:null},
	        {sequenceNumber:19,costumizeMenuData:null},
	        {sequenceNumber:20,costumizeMenuData:null},
	        {sequenceNumber:21,costumizeMenuData:null},
	        {sequenceNumber:22,costumizeMenuData:null},
	        {sequenceNumber:23,costumizeMenuData:null},
	        {sequenceNumber:24,costumizeMenuData:null},
	        {sequenceNumber:25,costumizeMenuData:null},
	        {sequenceNumber:26,costumizeMenuData:null},
	        {sequenceNumber:27,costumizeMenuData:null},
	        {sequenceNumber:28,costumizeMenuData:null},
	        {sequenceNumber:29,costumizeMenuData:null},   
	        {sequenceNumber:30,costumizeMenuData:null}
	    ]

	    //table header
	    categoryWithProgramsSelectionFields = [
	    	{key: 'program_name', label: 'プログラムメニュー名',class:'data-header'}					
		]
		//table items
		categoryWithProgramsSelectionItems:any[] = []	   

		programContainerToBeDeleted:any={} //my menu program to be deleted
		programContainerToBeAdded:any={} //my menu program to be added or updated

		data () {
			return {}
		}

		async fetchData() {

			this['$axios'].$get('/api/program/category/grantedprograms/1')
								.then(categories=> {
										this.categoryWithProgramsSelectionItems = categories.filter(category=>category.programs.length>0) //set to list of category with programs filtered to only programs that has granted access to this user					             ;
								}).catch(ex=>{
										console.log("exception: "+ex)									 
										return []											 
								})
			
			this['$axios'].$get('/api/mymenu/get/1/')
										.then(mymenus=>{																		
												this.populateProgramsToContainer(mymenus)										
										}).catch(ex=>{
											console.log("exception: "+ex)
											this.populateProgramsToContainer([])
										})
			
	}

		mounted () {
			this.fetchData();
		}

		editProgramContainer(programContainer:object) {			
			if(!this.isProgramDeleted){				
				this.latestSelectedProgramContainer = programContainer;
				this.programChoicesListModal = true;
				return;
			}
			this.isProgramDeleted = false;
		}

		deleteProgram(programContainer:any) {
			this.programContainerToBeDeleted = programContainer
			this.isProgramDeleted = true			
		}

		populateProgramsToContainer(programs:object[]){
			if(programs.length>0){
				for(let program of this.programsContainer){  
						var foundProgram = this.getProgramsBySequenceNumber(programs,program["sequenceNumber"]);
						if(foundProgram){
							program["costumizeMenuData"] = foundProgram;
							continue;
						}
						program["costumizeMenuData"] = null;		         
				}
			}
		}

		getProgramsBySequenceNumber(programs: object[],sequenceNumber: number){    
		    for(let program of programs) {
		        let menuSequence :number = program["menu_sequence"];
		        if(menuSequence === sequenceNumber)          
		          return program;        
		    }
		}

		selectProgram(selectedProgram:object) {			
			console.log(selectedProgram)
			this.programContainerToBeAdded=selectedProgram			
		}

		save(program:any) {
			if(this.latestSelectedProgramContainer.costumizeMenuData) {
				if(this.latestSelectedProgramContainer.costumizeMenuData.program.mst_role_prog.program_id == program.program_id) {	
				  this.programChoicesListModal = false;
				  return;						
				}
			}
			program=this.createObject(program,this.latestSelectedProgramContainer.costumizeMenuData);
			this['$axios'].$patch('/api/mymenu/',program)
										.then(mymenus=> {
											this.fetchData()
											this.programChoicesListModal = false;
										}).catch(ex=>console.log("exception: "+ex))
		}

		confirmDelete(){
			this['$axios'].$delete('/api/mymenu/',{ data: this.programContainerToBeDeleted.costumizeMenuData } )
				.then(mymenus=> {
					this.programContainerToBeDeleted.costumizeMenuData=null
				}).catch(ex=>console.log("exception: "+ex))
		}

		confirmAdd(){			
			this.save(this.programContainerToBeAdded)			
		}

		createObject(program:any,myMenuData:any) {
			let hasData:boolean  = myMenuData != null
			if(hasData) {
				myMenuData.program.mst_role_prog.program_id=program.program_id;
				myMenuData.program.mst_role_prog.authorized_user=1;
				myMenuData.menu_sequence = this.latestSelectedProgramContainer.sequenceNumber
				myMenuData.updated_by.user_id = 1
				myMenuData.updated_at.program_id = "JMST07"
				return myMenuData;
			}

			return {
				program:{
					mst_role_prog:{
						"program_id": program.program_id,
						"authorized_user": 1
					}
				},
				menu_sequence:this.latestSelectedProgramContainer.sequenceNumber,
				created_by:{
					"user_id":1
				},
				created_at:{
					"program_id":"JMST09"
				},
				updated_by: {
					user_id:1
				},
				updated_at:{
					"program_id":"JMST07"
				}
			}
		}

		isSelectedProgram(program:any){
			//console.log(program)
			if(this.latestSelectedProgramContainer && this.latestSelectedProgramContainer.costumizeMenuData){// checks if not null
				console.log("costumizeMenuData name: "+this.latestSelectedProgramContainer.costumizeMenuData.name);
				console.log("program name: "+program.name);
				console.log("is it true?: "+(this.latestSelectedProgramContainer.costumizeMenuData.program.program.name == program.name));
				return (this.latestSelectedProgramContainer.costumizeMenuData.program.program.name == program.name)
			}
			console.log("false");
			return false;
			
		}
	}
</script>
<style lang="scss">
	@import '@/assets/scss/mixins.scss';
	
	.page {
		.header {
			margin: 0 1%;
			text-align: center;
			.col {
				background-color: $darkblue;
				margin:0 5px;
				padding:1% 0;
				span {
					color:$white;
					font-size:1rem;								
				}
			}
		}
		#sub-menu-container {
			.sub-menu {    
				.content {
					.row{
					margin: 0;  
					}
					.col {
					padding: 0;  
					}
				}
				.container-fluid {
					padding:0;
				}
			}
		}		

		.no-pad-col {
			padding:0;
		}
		.item {
			margin: 0 5px 0 0;
			button {
				text-align: left;
			}
		}
		.passive {
			background-color: #6c7289 !important;
		}
		a {
			&.sub-menu-item {
				color:$white;
				text-decoration: none;
				display: block;
				padding: 10px 0;
				
				&:hover {
					text-decoration: none;
				}
				span {
					padding: 9px 2%;
				}
			}
			&.delete-programs-btn {				
				span {
					background: #ea0000;
					color: #ffffff;
					&:hover{
						//background-color: blue;
					}
				}
			}
		}
		.content {
			margin-top:20px;
			.item {
				margin:0.4%;
				border-radius: 0.25rem;
				background: $darkgray;    
				position:relative;
				
				&:hover {
					background-color: $darkblue;
				}
			}
			.item-container {
				position: absolute;
				bottom: 0;
				left: 0;
			}
		} 		
		
		button{
			&.delete-programs-btn {
				float:right;
			}
			fa {
				&.delete-program-icon {
					font-size:25px;
				}
			}
		}					
	}	

	.isActive {
		background-color: #0055af !important;
	}	
	.fade{
		&-enter{
			//transform: translateX(20px);
				opacity: 0;
				&-active{
					transition: all 0.5s ease;
				}
		}
		&-leave{
			&-active{
				 transition: all 0.5s ease;
			}
			&-to{
				//transform: translateX(-20px);
					opacity: 0;
			}
		}
	}

	#Update{									
		.modal-body {
			padding:1%;	
			max-height: 800px;
			overflow: hidden;
		}
		table {
			margin-bottom: 1%;

			.data-header {
				padding: 1%;
				background: $darkblue;
				color:$white;	
				td {
					width:1000px;
					background-color: $white;
					color:#111;
					&:hover{
						background-color:#232323;
						color:$white;
						cursor: pointer;
					}
				}
			}
			th {
				&.data-header {
					text-align: center;
					font-size: 1.2rem;
				}
			}
			tbody {
				height: 720px;
				display: block;
				overflow-y: scroll;	

				td {
					&.foo {
						padding: 0;
					}
				}
				
				.row-header {
					padding: 1%;
					background-color: $white;
					margin: -4px -5px 3px -5px;
					color: $black;
					font-size:1.2rem;
					font-weight:bold;
					span {
						margin-right: 5px;
					}
				}
				.program-item {
					color:$white;
					text-decoration: none;
					display: block;
					height:40px;
					width: 100%;
					text-align: left;
					&:hover {
						text-decoration: none;
						background-color: $darkblue;
					}			
				}
			}
			tfoot {
				tr{
					width: 100%;
					height: 10px;
					display: block;
					background-color: $darkblue;
					th {
						display: none;
					}
				}						
			}
		}
	}
</style>