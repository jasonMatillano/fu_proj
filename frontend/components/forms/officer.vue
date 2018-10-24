<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="担当者CD"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.user_code" class="width-5"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="担当者名"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.username"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="担当者カナ"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.username_kana"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="パスワード"
		            label-for="input_sm"
		            class="my-1">
			<!-- <b-form-input size="sm" type="password"></b-form-input> -->
			<div v-if="showPwd == false">
				<transition-group name="fade" tag="div">
					<b-button key="btn" class="button-width-90 position-absolute" size="sm"　v-if="mode == false" @click="setTrue">変更</b-button>
					<b-row class="position-absolute" key="pass" v-if="mode == true">
						<b-col sm="9">
							<b-form-input size="sm" type="password" v-model="pwd"></b-form-input>
						</b-col>
						<b-col class="px-0" sm="3">
							<!-- <b-button size="sm" variant="outline-success"><fa :icon="['far','times-circle']" id="edit"/></b-button> -->
					  		<b-btn v-b-tooltip.hover title="編集をキャンセル" 
					  				size="sm" 
					  				class="pb-0" 
					  				variant="outline-danger"
					  				@click="setFalse"><fa :icon="['far','times-circle']" id="edit"/></b-btn>
						</b-col>
					</b-row>
				</transition-group>
			</div>
			<div v-if="showPwd == true">
				<b-form-input size="sm" type="password" v-model="pwd" class="w-50"></b-form-input>
			</div>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="部門"
		            label-for="input_sm"
		            class="my-1">
			<b-form-select
						v-model="selected"
						size="sm">
						<option v-for="(key, i) in deptdata" :value="key.id">{{key.name}}</option>
						<template slot="first">
							<option value="null" disabled> --Please select a department-- </option>
						</template>
					</b-form-select>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="終了"
		            label-for="input_sm"
		            class="my-1"
		            v-if="showPwd == false">
			<b-form-checkbox id="isResigned"
                     v-model="localData.isResigned">
		    </b-form-checkbox>
		</b-form-group>
		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">キャンセル</b-button>
			<b-button class="button-width-90" variant="primary" v-b-modal="'confirm'" type="submit">{{localData["id"] ? "変更"　: "新規登録"}}</b-button>
		</b-col>
	</b-form>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';

	@Component
	export default class Officer extends Vue {

		@Prop()
		formdata: Object;

		@Prop()
		processName: String;

		@Prop()
		deptdata: Object;

		localData = {}
		optionData:any = [{value: '',text: ''}]
		selected = null
		showPwd:boolean = false
		pwd = null
		mode = false //  true:change password , false: no password change

		setFalse(){
			this.mode = false
		}
		setTrue(){
			this.mode = true
		}
		submit(){
			this.$emit('submit', {data: this.localData, password: this.pwd,department: this.selected, success: true})
		}
		clickCancel(){
			this.$emit('clickCancel', false)
		}
		mounted() {
		  	this.localData = Object.assign({}, this.formdata)
		  	console.log("local data: ",this.localData["id"])
		  	this.isRegisterOrNot()
		  	
		}

		//watcher for data change
		@Watch('formdata')
		onChanged(val: string, oldVal: string){
			this.localData = Object.assign({}, val)
			this.mode = false
			this.isRegisterOrNot()
			
		}

		//check if register or update
		isRegisterOrNot(){
			if(this.localData["id"]){
		  		this.showPwd = false
				if( this.localData["department"] != null){
					this.selected = this.localData["department"].id
				}else{
					this.selected = null
				}
		  	}else if(!this.localData["id"]){
		  		this.selected = 0
		  		this.showPwd = true
		  	}
		}

	}
</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	@import '@/assets/scss/forms.scss';
</style>
