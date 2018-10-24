<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="CD"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.code"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="拠点名"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="拠点名ｶﾅ"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.name_kana"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="拠点略称"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.short_name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="順序"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.sequence"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="〒"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" v-model="localData.postal_code"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="住所"
		            label-for="input_sm" class="mb-1">
			<b-form-input size="sm" class="mb-1" v-model="localData.address1"></b-form-input>
			<b-form-input size="sm" v-model="localData.address2"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="電話番号"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.telephone_number"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="FAX番号"
		            label-for="input_sm" 
		            class="mb-1">
			<b-form-input size="sm"  v-model="localData.fax_number"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="領収連番"
		            label-for="input_sm" class="mb-1">
			<b-form-select class="input-width-100"
                 :options="{ '1': 'T','2': 'O'}"
				 v-model="localData.receipt_sequence_number"
                 size="sm">
			</b-form-select>
		</b-form-group>
        <b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="領収記号"
		            label-for="input_sm" class="mb-1">
			<b-form-select class="input-width-100"
                 :options="{ '1': 'T','2': 'O'}"
				 v-model="localData.receipt_symbol"
                 size="sm">
			</b-form-select>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="Sales flag"
		            label-for="input_sm" class="mb-1">
			 <b-form-checkbox v-model="localData.sales_flag">               
            </b-form-checkbox>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="終了"
		            label-for="input_sm" class="mb-1">
			 <b-form-checkbox v-model="localData.is_end">               
            </b-form-checkbox>
		</b-form-group>
	  	<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">キャンセル</b-button>
			<b-button class="button-width-90" variant="primary" v-b-modal="'confirm'">{{processName}}</b-button>
		</b-col>
		 <c_modal @submit="submit" idMod="confirm"></c_modal>
	</b-form>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';
	import {TreeNode} from '@/assets/ts/TreeNode.ts'
	import c_modal from '@/components/modal'

	@Component({
		components:{
			c_modal
		},
	})
	export default class Department extends Vue {
		@Prop()
		formdata: TreeNode;

		@Prop()
		processName: String;

		localData:any={};

		submit(){
			this.$emit('submit', this.localData)
		}
		clickCancel(){
			this.$emit('clickCancel', false)
		}
		
		mounted() {
		  	this.localData = Object.assign({}, this.formdata.nodeData)
		}

		@Watch('formdata')
		onChanged(val: any, oldVal: string){
			this.localData = Object.assign({}, val.nodeData)
		}
	}
</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	form{
		input,select{
			border: 1px solid $black;
			font-size: $normalrem;
			font-weight: 600;
		}
		button{
			&.button-width{
					&-90{
						@include give-width(90px);
						font-size: $normalrem;
					}
				}
		}
	}
</style>