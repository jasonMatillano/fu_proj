<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
				<hr>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="5">
				<section class="field-input mb-2">
					<b-col>
						<b-form v-on:submit.prevent="onSubmit" inline>
							<!-- <label class="mr-sm-2" for="user">{{user_id_lang}}: </label> -->
							<b-input class="mb-2 mr-sm-2 mb-sm-0 input-width-65" @focus="userIdSearchInputBoxHasFocus" v-model="currentlySetUserId" :placeholder="user_id_placeHolder" id="user" size="sm"/>						
							<label style="margin-left:10px;" class="mr-sm-2" for="">{{currentlySetUser.username_kana}}</label>
						</b-form>
					</b-col>
				</section>
				<section class="field-table">
					<table class="permission-table table-bordered">
						<tr >
							<th>プログラムカテゴリ名</th>
							<th>プログラム名</th>
							<th>プログラムコマンド名</th>
							<th>使用</th>
						</tr>
						<template v-for="categoryItem in tableItems">
							<template v-for="(programItem,programIndex) in categoryItem.programs">
								<template v-for="(wrapperItem,wrapperIndex) in programItem.wrapper">
									<tr :key="wrapperItem.id">
										<td v-if="programIndex == 0 && wrapperIndex == 0" :rowspan="getCategoryProgramCount(categoryItem)">{{categoryItem.category_name}}</td>
										<td v-if="wrapperIndex == 0" :rowspan="getCommandCount(programItem)">{{programItem.text}}</td>
										<td>{{wrapperItem.programCommand.key_name}}</td>
										<td>
											<b-form-checkbox v-model="wrapperItem.permission">               
											</b-form-checkbox>
										</td>
									</tr>
								</template>
							</template>
						</template>
					</table>				
				</section>
			</b-col>
		</b-row>
		<c_footer class="content-footer" @eventTriggered="eventHandler" :events="footerEvents"></c_footer>
		<c_modal @submit="confirmSubmit" idMod="confirm"></c_modal>
	</b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator';

	import c_footer from '@/components/footer.vue';
	import {RoleProgramCommandWrapper} from "@/assets/ts/RoleProgramCommandWrapper.ts"
	import {PermissionState} from "@/assets/ts/PermissionState.ts"
	import {UIEvent} from "@/assets/ts/UIEvent.ts"
	import {Keys} from "@/assets/ts/keys.ts"
	import  KeyPressableComponent  from '@/assets/ts/KeyPressableComponent.ts'
	import c_modal from '@/components/modal'
	
	@Component({
		components:{
			c_footer,
			c_modal
		},
	})
	export default class Button extends KeyPressableComponent {
			index = 0
			rowspan:number=6
			// change lange
			title_lang = ''
			user_id_placeHolder=''			
			userIdSearchInputBoxHasFocus:boolean = true
			category_name_lang = ''
			program_name_lang = ''
			action_lang = ''
			useFlg_lang = ''
			referenceFlg_lang = ''
			currentlySetUser:any={username_kana:""}
			currentlySetUserId:any=''
			programCommandPermissions:Array<RoleProgramCommandWrapper>=new Array();		
			tableItems:any = []	
			footerEvents:Array<UIEvent>=[ 
				new UIEvent(Keys.F2,'取消',true),
				new UIEvent(Keys.F4,'保存',true),
				new UIEvent(Keys.F12,'メニュ',true),									
			]     

			created() {
				this.title_lang = this.$t("Auth.pages.roleButton.title").toString();
				this.user_id_placeHolder = this.$t("Auth.pages.roleButton.userid").toString()
			}

			onSubmit(){

			}

			confirmSubmit(){}

			//overrides from parent class
			mapEvents() {
				this.UIEvents = [ 
					new UIEvent(Keys.ENTER,'',true),							
				] 
				this.addKeyPressListener(this.UIEvents[0].key);
			}

			keyPressEventHandler(keyPressEvent): void {				
				this.eventHandler(this.UIEvents[0]);
			}

			getCategoryProgramCount(category:any):number{
				let count:number=0;
				category.programs.forEach(program=>{
					count+=this.getCommandCount(program);
				})
				return count;
			}

			getCommandCount(program:any):number{
				let count:number=0;
				program.commands.forEach(c=>{
					count+=1;
				})
				return count;
			}

			userChanged():void{	
				if(this.currentlySetUserId!="" && this.currentlySetUserId!=this.currentlySetUser.id)
					this.fetchUser(this.currentlySetUserId)
			}

			fetchUser(userId:string):void{
				this['$axios'].$get('/api/user/'+userId)
				.then(user=>{																								
						this.currentlySetUser=user
						this.fetchCommandsOfUser(this.currentlySetUser.id)
				}).catch(ex=>{
					console.log("exception: "+ex)					
				})
			}

			fetchCommandsOfUser(userId:string):void{
				this['$axios'].$get('/api/program/command/grantedcommands/'+userId)
				.then(commands=>{																		
						this.programCommandPermissions=new Array()
						this.tableItems=commands
						this.tableItems.forEach(cat=>{
							cat.programs.forEach(prog=>{
								let roleProgramCommandWrapperList: Array<RoleProgramCommandWrapper>=new Array();
								prog.commands.forEach(command=>{	
									let roleProgramCommandWrapper = new RoleProgramCommandWrapper(command);
									this.programCommandPermissions.push(roleProgramCommandWrapper);
									roleProgramCommandWrapperList.push(roleProgramCommandWrapper);
								})			
								prog.wrapper = roleProgramCommandWrapperList;					
							})							
							
						})										
				}).catch(ex=>{
					console.log("exception: "+ex)					
				})
			}

			savePermissions():void{
				let revokedProgramCommandList:any=[];
				let grantedProgramCommandList:any=[];

				this.programCommandPermissions.forEach(command=>{

					let progCommand:any = command.programCommand
					switch(command.permissionState()){
						case PermissionState.Revoked:
							revokedProgramCommandList.push(this.createProgramCommandRoleObject(progCommand))
						break;
						case PermissionState.Granted:
							grantedProgramCommandList.push(this.createProgramCommandRoleObject(progCommand))
						break;
					}
				})

				let request:any=[]
				
				if(revokedProgramCommandList.length<1 &&
					grantedProgramCommandList.length<1){
					return;						
				}				
				else if(revokedProgramCommandList.length>0 &&
						grantedProgramCommandList.length>0){
					request=[this.getGrantPermissionRequest(grantedProgramCommandList),
								this.getRevokePermissionRequest(revokedProgramCommandList)
						];
				}
				else if(revokedProgramCommandList.length>0){
					request=[this.getRevokePermissionRequest(revokedProgramCommandList)
						];
				}
				else if(grantedProgramCommandList.length>0){
					request=[this.getGrantPermissionRequest(grantedProgramCommandList)
						];
				}

				this.promiseSave(request);

			}

			createProgramCommandRoleObject(programCommand:any):any{
				return {
					command:{
						user_id:this.currentlySetUser.id,
						program_id:programCommand.program_id,
						command_id:programCommand.id
					},
					created_by: {id: 1},
					created_at: {id: "AQOT01"},				
					updated_by: {id:1},
					updated_at: {id: "AQOT01"}
				}
			}

			getGrantPermissionRequest(grantedPermissionList:any):void{
				return this['$axios'].$post('/api/role/program/command/grantprogramcommandroles/',{ role_program_commands: grantedPermissionList });			
			}
			
			getRevokePermissionRequest(revokedPermissionList:any):void{
				return this['$axios'].$delete('/api/role/program/command/revokeprogramcommandroles/',{ data: {role_program_commands: revokedPermissionList }});			
			}

			promiseSave(requests:any){
				return Promise.all(requests).then(resp=>{
					this.fetchCommandsOfUser(this.currentlySetUser.id)
				}).catch(resp=>{return false})  
			}

			eventHandler(event){								 
				switch(event.key){
					case Keys.F2:
						this.cancel()
					break;				
					case Keys.F4:
						this.savePermissions();
					break;
					case Keys.ENTER:								
						if(this.userIdSearchInputBoxHasFocus)
							this.userChanged();
					break;
				}
			}			
			cancel(){
				this.currentlySetUserId="";
				this.currentlySetUser={};
				this.tableItems=[];
			}

	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';
	// .my-table {
	// 	tr,th,td {
	// 		vertical-align: text-top;
	// 		border: 1px solid #526a7f;
	// 	}
	// }
	section{
		&.field-table{
			.permission-table{
				 tr, th, td{
					border: 1px solid #526a7f;
					vertical-align: text-top;
					&.table-col-width, .table-col{
						&-margin{
							&-20{
								@include give-margin(0 0 0 10px);
							}
						}
					}
				}
				tr:nth-child(1){
					background-color: $darkblue;
					color: white;
					//display:table;
					@include give-width(-webkit-calc(100% - 10px));
					@include give-width(    -moz-calc(100% - 10px));
					@include give-width(         calc(100% - 10px));
					table-layout:fixed;
					th{
						text-align: center !important;
						&:last-child{
							@include give-padding(0.3rem 17px 0.3rem 0.3rem);
						}
					}
				}
				tr:not(:nth-child(1)){
					//display:block;
					//height: 620px;
					overflow-y:scroll;
					//display:table;
					@include give-width(100%);
					table-layout:fixed;
					&.table{
						&-half{
							height: 280px;
						}
						&-adjusted{
							height: 600px;
						}
					}
				}
				//tbody{
					tr{
					    @include give-margin(0 0 -1px 0);
					    td{
					    	tr:first-child{
								td{
									border-top: 0;
								}
					    	}
					    }
					}
				//}
				&.table{
					&-bordered{
						&-all{
							th, td{
								font-weight: normal;
								border: 1px solid #526a7f;
							}
						}
					}
					&-sm{
						th{
							padding: 0.3rem !important;
						}
					}
				}
			}
		}
		div{
			&.custom-control{
				min-height: 1.299rem;
			}
			form{
				input{
					&.input-width{
						&-65{
							@include give-width(65px !important);
						}
					}
				}	
			}
		}
	}
</style>