<template>
	<b-form @submit.prevent="submit">
		<b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer">得意先名</label>
			</b-col>
			<b-col sm="8">
				<b-form-input id="customer" size="sm" v-model="localData.customer_name"></b-form-input>
			</b-col>
	    </b-row>
	    <b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer">納入先名 </label>
			</b-col>
			<b-col sm="5">
				<b-form-input id="customer" size="sm" class="mb-1" v-model="localData.supplier_name"></b-form-input>
				<b-form-input id="customer" size="sm"></b-form-input>
			</b-col>
	    </b-row>
	    <b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer" v-model="localData.supplier_kana">カナ </label>
			</b-col>
			<b-col sm="8">
				<b-form-input id="customer" size="sm" v-model="localData.supplier_kana"></b-form-input>
			</b-col>
	    </b-row>
	    <b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer">略称名 </label>
			</b-col>
			<b-col sm="8">
				<b-form-input id="customer" size="sm"></b-form-input>
			</b-col>
	    </b-row>
	    <b-row class="m-0 pb-1">
			<b-col sm="3" class="pr-0">
				<label for="customer">伝票印字名称 </label>
			</b-col>
			<b-col sm="8">
				<b-form-input id="customer" size="sm"></b-form-input>
			</b-col>
	    </b-row>
	    <b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer">住所 </label>
			</b-col>
			<b-col sm="8">
				<b-row class="m-0">
					<b-row class="m-0">
						<b-col sm="1" class="p-0">
							<label for="customer">〒 </label>
						</b-col>
						<b-col sm="8" class="pb-1 pl-0">
							<b-col class="p-0">
								<b-form-input id="customer" size="sm"></b-form-input>
							</b-col>
						</b-col>
						<b-col class="p-0"  sm="12">
							<b-form-input id="customer" size="sm" class="mb-1" ></b-form-input>
						</b-col>
						<b-col sm="12"  class="p-0">
							<b-form-input id="customer" size="sm" class="mb-1"></b-form-input>
						</b-col>
						<b-col  sm="12"  class="p-0">
							<b-form-input id="customer" size="sm"></b-form-input>
						</b-col>
				    </b-row>
				</b-row>
			</b-col>
	    </b-row>
	    <!-- <b-row class="m-0 pb-1">
	    	<b-col>
	    		<b-row>
	    			    	<b-col sm="4">
	    						<label for="customer">電話番号 </label>
	    					</b-col>
	    					<b-col sm="8">
	    						<b-form-input id="customer" size="sm" v-model="localData.phone_number"></b-form-input>
	    					</b-col>
	    				</b-row>	
	    	</b-col>
	    	<b-col>
	    		<b-row>
	    			    	<b-col sm="3" class="p-0">
	    						<label for="customer">FAX番号 </label>
	    					</b-col>
	    					<b-col sm="8"  class="p-0">
	    						<b-form-input id="customer" size="sm"></b-form-input>
	    					</b-col>
	    				</b-row>	
	    	</b-col>
	    </b-row> -->
	     <b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer">電話番号 </label>
			</b-col>
			<b-col sm="5">
				<b-form-input id="customer" size="sm"></b-form-input>
			</b-col>
	    </b-row>
	     <b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer">FAX番号 </label>
			</b-col>
			<b-col sm="5">
				<b-form-input id="customer" size="sm"></b-form-input>
			</b-col>
	    </b-row>
	    <b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer">E-MAIL </label>
			</b-col>
			<b-col sm="8">
				<b-form-input id="customer" size="sm"></b-form-input>
			</b-col>
	    </b-row>
	    <b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer">URL </label>
			</b-col>
			<b-col sm="8">
				<b-form-input id="customer" size="sm"></b-form-input>
			</b-col>
	    </b-row>
	    <b-row class="m-0 pb-1">
			<b-col sm="3">
				<label for="customer">先方担当者 </label>
			</b-col>
			<b-col sm="8">
				<b-form-input id="customer" size="sm"  class="input-width-200"></b-form-input>
			</b-col>
	    </b-row>
		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">キャンセル</b-button>
			<b-button class="button-width-90" variant="primary" v-b-modal="'confirm'" type="submit">{{processName}}</b-button>
		</b-col>
	</b-form>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';

	@Component
	export default class Warehouse extends Vue {

		@Prop()
		formdata: Object;

		@Prop()
		processName: String;

		localData = {};

		submit(){
			this.$emit('submit', this.$data.localData)
		}
		clickCancel(){
			this.$emit('clickCancel', false)
		}

		mounted() {
		  	this.localData = Object.assign({}, this.formdata)
		}

		@Watch('formdata')
		onChanged(val: string, oldVal: string){
			this.localData = Object.assign({}, val)
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