<template>
	<b-form id="bill-form" @submit.prevent="submit">
		<b-row>
			<b-col>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="請求先CD"
				            label-for="input_sm"
				            class="my-1 sm-size">
				            <b-col>
				            	<b-row>
				            		<b-form-input size="sm" v-model="localData.billing_code" class="width-6"></b-form-input>
				            		<b-form-group horizontal
									            :label-cols="6"
									            label-size="sm"
									            label="終了"
									            label-for="input_sm"
									            class="m-0 ml-5">
										<b-form-checkbox id="終了"
									                     v-model="localData.end"
									                     value="終了"
									                     unchecked-value=" ">
									    </b-form-checkbox>
									</b-form-group>
				            	</b-row>
				            </b-col>
					
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="請求先名"
				            label-for="input_sm"
				            class="my-1 sm-size">
					<b-form-input size="sm" v-model="localData.billing_name"></b-form-input>
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="請求先ｶﾅ"
				            label-for="input_sm"
				            class="my-1 sm-size">
					<b-form-input size="sm" v-model="localData.billing_name_kana"></b-form-input>
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="略称名"
				            label-for="input_sm"
				            class="my-1 sm-size">
					<b-form-input size="sm" v-model="localData.billing_shortname"></b-form-input>
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="郵便番号"
				            label-for="input_sm"
				            class="my-1 sm-size">
					<b-form-input size="sm" v-model="localData.billing_postal_code" class="width-10"></b-form-input>
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="住所"
				            label-for="input_sm"
				            class="my-1">
					<b-form-input size="sm" v-model="localData.billing_address1"></b-form-input>
					<b-form-input size="sm" v-model="localData.billing_address2" class="my-1"></b-form-input>
					<b-form-input size="sm" v-model="localData.billing_address3"></b-form-input>
				</b-form-group>
				
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="経理電話番号"
				            label-for="input_sm"
				            class="my-1 sm-size">
					<b-form-input size="sm" v-model="localData.billing_acc_phoneno" class="width-10"></b-form-input>
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="電話番号"
				            label-for="input_sm"
				            class="my-1 sm-size">
				            <b-row>
				            	<b-col sm="4">
				            		<b-form-input size="sm" v-model="localData.billing_telno1" class="width-10"></b-form-input>
				            	</b-col>
				            	<b-col sm="4">
				            		<b-form-input size="sm" v-model="localData.billing_telno2" class="width-10"></b-form-input>
				            	</b-col>
				            </b-row>
					
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="敬称"
				            label-for="input_sm"
				            class="my-1 sm-size">
					<b-form-input size="sm" v-model="localData.billing_title"></b-form-input>
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="請求締グループ"
				            label-for="input_sm"
				            class="my-1 sm-size">
					<b-form-input size="sm" v-model="localData.billing_claims_group"></b-form-input>
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="合計請求書印刷区分"
				            label-for="input_sm"
				            class="my-1 sm-size">
				            <b-row>
				            	<b-col sm="4">
				            		<b-form-checkbox id="total_invoice_printing_type"
								                     v-model="localData.total_invoice_printing_type"
								                     value="true"
								                     unchecked-value=" ">
								    </b-form-checkbox>
				            	</b-col>
				            	<b-col>
								    <b-form-group horizontal
									            :label-cols="9"
									            label-size="sm"
									            label="請求明細書印刷区分"
									            label-for="input_sm"
									            class="my-1 sm-size">
										<b-form-checkbox id="billing_printing_type"
									                     v-model="localData.billing_printing_type"
									                     value="true"
									                     unchecked-value=" ">
									    </b-form-checkbox>
									</b-form-group>
				            	</b-col>
				            </b-row>
				</b-form-group>
				
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="物件明細書印刷区分"
				            label-for="input_sm"
				            class="my-1 sm-size">
				            <b-row>
				            	<b-col sm="4">
				            		<b-form-checkbox id="property_printing_type"
								                     v-model="localData.property_printing_type"
								                     value="true"
								                     unchecked-value=" ">
								    </b-form-checkbox>
				            	</b-col>
				            	<b-col>
								    <b-form-group horizontal
									            :label-cols="9"
									            label-size="sm"
									            label="代引"
									            label-for="input_sm"
									            class="my-1">
										<b-form-checkbox id="billing_cash_on_delivery"
									                     v-model="localData.billing_cash_on_delivery"
									                     value="true"
									                     unchecked-value=" ">
									    </b-form-checkbox>
									</b-form-group>
				            	</b-col>
				            </b-row>
				</b-form-group>

				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="集金"
				            label-for="input_sm"
				            class="my-1 sm-size">
				            <b-row>
				            	<b-col sm="4">
				            		<b-form-checkbox id="billing_collection"
								                     v-model="localData.billing_collection"
								                     value="true"
								                     unchecked-value=" ">
								    </b-form-checkbox>
				            	</b-col>
				            	<b-col>
								    <b-form-group horizontal
									            :label-cols="9"
									            label-size="sm"
									            label="入金確認後出荷"
									            label-for="input_sm"
									            class="my-1">
										<b-form-checkbox id="shipment_after_payment_confirmed"
									                     v-model="localData.shipment_after_payment_confirmed"
									                     value="true"
									                     unchecked-value=" ">
									    </b-form-checkbox>
									</b-form-group>
				            	</b-col>
				            </b-row>
				</b-form-group>

				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="専用伝票"
				            label-for="input_sm"
				            class="my-1 sm-size">
				            <b-row>
				            	<b-col sm="4">
				            		<b-form-checkbox id="billing_dedicated_slip"
								                     v-model="localData.billing_dedicated_slip"
								                     value="true"
								                     unchecked-value=" ">
								    </b-form-checkbox>
				            	</b-col>
				            </b-row>
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="入金サイト"
				            label-for="input_sm"
				            class="my-1 sm-size">
					<b-form-input size="sm" v-model="localData.billing_deposit_site" class="width-5"></b-form-input>
				</b-form-group>
				<b-form-group horizontal
				            :label-cols="4"
				            label-size="sm"
				            label="入金条件"
				            label-for="input_sm"
				            class="my-1 sm-size">
					<b-form-input size="sm" v-model="localData.billing_payment_conditions"></b-form-input>
				</b-form-group>
			</b-col>
			<b-col>
				<b-card bg-variant="light">
					<h5 class="text-center">入金方法１</h5>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="入金基準額"
					            label-for="input_sm"
					            class="my-1 sm-size">
								<b-row>
					            	<b-col class="d-inline-flex">
										<b-form-input size="sm" v-model="localData.deposit_method1_reference" class="width-160"></b-form-input>
										<label for="deposit_method1_payment_month_after" class="my-1 px-1">全額</label>
									</b-col>
								</b-row>
					</b-form-group>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="分割入金"
					            label-for="input_sm"
					            class="my-1 sm-size">
						<b-form-input size="sm" v-model="localData.deposit_method1_split_payment"></b-form-input>
					</b-form-group>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="入金方法"
					            label-for="input_sm"
					            class="my-1 sm-size">
						<b-form-input size="sm" v-model="localData.deposit_method1"></b-form-input>
					</b-form-group>

					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="入金予定"
					            label-for="input_sm"
					            class="my-1 sm-size">
					            <b-row>
					            	<b-col class="d-inline-flex">
					            		<b-form-input size="sm" v-model="localData.deposit_method1_payment_month_after" class="width-5"></b-form-input>
					            		<label for="deposit_method1_payment_month_after" class="my-1 px-1">カ月後</label>
					            		<b-form-input size="sm" v-model="localData.deposit_method1_plan_day" class="width-5"></b-form-input>
					            		<label for="deposit_method1_plan_day" class="my-1 px-1">日</label>
					            	</b-col>
					            </b-row>
					</b-form-group>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="分割基準"
					            label-for="input_sm"
					            class="my-1">
					            <b-row>
					            	<b-col class="d-inline-flex">
					            		<b-form-input size="sm" v-model="localData.deposit_method1_div" class="width-10"></b-form-input>
					            		<b-form-input size="sm" v-model="localData.deposit_method1_criteria_percentage" class="mx-2 width-5"></b-form-input>
					            		<label for="deposit_method1_criteria_percentage" class="my-1 px-1">%</label>
					            	</b-col>
					            </b-row>
					            <b-row　class="my-1">
					            	<b-col class="d-inline-flex">
				            			<b-form-input size="sm" v-model="localData.deposit_method1_div_standard_amount"></b-form-input>
					            		<label for="deposit_method1_div_standard_amount" class="my-1 px-1">円</label>
					            	</b-col>
					            </b-row>
						
					</b-form-group>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="分割入金方法"
					            label-for="input_sm"
					            class="my-1 sm-size">
						<b-form-input size="sm" v-model="localData.deposit_method1_slip"></b-form-input>
					</b-form-group>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="分割入金予定"
					            label-for="input_sm"
					            class="my-1 sm-size">
					            <b-row>
					            	<b-col class="d-inline-flex">
					            		<b-form-input size="sm" v-model="localData.deposit_method1_payment_month_after" class="width-5"></b-form-input>
					            		<label for="deposit_method1_payment_month_after" class="my-1 px-1">カ月後</label>
					            		<b-form-input size="sm" v-model="localData.deposit_method1_plan_day" class="width-5"></b-form-input>
					            		<label for="deposit_method1_plan_day" class="my-1 px-1">日</label>
					            	</b-col>
					            </b-row>
					</b-form-group>
				</b-card>

				<b-card bg-variant="light" class="my-1">
					<h5 class="text-center">入金方法2</h5>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="入金基準額"
					            label-for="input_sm"
					            class="my-1 sm-size">
						<b-form-input size="sm" v-model="localData.deposit_method1_reference"></b-form-input>
					</b-form-group>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="分割入金"
					            label-for="input_sm"
					            class="my-1 sm-size">
						<b-form-input size="sm" v-model="localData.deposit_method1_split_payment"></b-form-input>
					</b-form-group>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="入金方法"
					            label-for="input_sm"
					            class="my-1 sm-size">
						<b-form-input size="sm" v-model="localData.deposit_method1"></b-form-input>
					</b-form-group>

					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="入金予定"
					            label-for="input_sm"
					            class="my-1 sm-size">
					            <b-row>
					            	<b-col class="d-inline-flex">
					            		<b-form-input size="sm" v-model="localData.deposit_method1_payment_month_after" class="width-5"></b-form-input>
					            		<label for="deposit_method1_payment_month_after" class="my-1 px-1">カ月後</label>
					            		<b-form-input size="sm" v-model="localData.deposit_method1_plan_day" class="width-5"></b-form-input>
					            		<label for="deposit_method1_plan_day" class="my-1 px-1">日</label>
					            	</b-col>
					            </b-row>
					</b-form-group>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="分割基準"
					            label-for="input_sm"
					            class="my-1">
					            <b-row>
					            	<b-col class="d-inline-flex">
					            		<b-form-input size="sm" v-model="localData.deposit_method1_div" class="width-10"></b-form-input>
					            		<b-form-input size="sm" v-model="localData.deposit_method1_criteria_percentage" class="mx-2 width-5"></b-form-input>
					            		<label for="deposit_method1_criteria_percentage" class="my-1 px-1">%</label>
					            	</b-col>
					            </b-row>
					            <b-row　class="my-1">
					            	<b-col class="d-inline-flex">
				            			<b-form-input size="sm" v-model="localData.deposit_method1_div_standard_amount"></b-form-input>
					            		<label for="deposit_method1_div_standard_amount" class="my-1 px-1">円</label>
					            	</b-col>
					            </b-row>
						
					</b-form-group>
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="分割入金方法"
					            label-for="input_sm"
					            class="my-1 sm-size">
						<b-form-input size="sm" v-model="localData.deposit_method1_slip"></b-form-input>
					</b-form-group>	
					<b-form-group horizontal
					            :label-cols="4"
					            label-size="sm"
					            label="分割入金予定"
					            label-for="input_sm"
					            class="my-1 sm-size">
					            <b-row>
					            	<b-col class="d-inline-flex">
					            		<b-form-input size="sm" v-model="localData.deposit_method1_payment_month_after" class="width-5"></b-form-input>
					            		<label for="deposit_method1_payment_month_after" class="my-1 px-1">カ月後</label>
					            		<b-form-input size="sm" v-model="localData.deposit_method1_plan_day" class="width-5"></b-form-input>
					            		<label for="deposit_method1_plan_day" class="my-1 px-1">日</label>
					            	</b-col>
					            </b-row>
					</b-form-group>
				</b-card>
			</b-col>
		</b-row>
		<b-row>
			<b-col>
				<label for="memo">メモ</label>
				<b-form-textarea id="memo"
			                     v-model="localData.memo"
			                     placeholder="ここにメモを書いてください"
			                     :rows="3"
			                     :max-rows="6"
			                     class="mb-2">
			    </b-form-textarea>
			</b-col>
			
		</b-row>

		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">キャンセル</b-button>
			<b-button class="button-width-90" variant="primary"  v-b-modal="'confirm'" type="submit">変更</b-button>
		</b-col>
	</b-form>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';

	@Component
	export default class Cgroup extends Vue {

		@Prop()
		formdata: Object;

		localData = {};

		submit(){
			this.$emit('submit',this.localData)
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
	@import '@/assets/scss/forms.scss';
	 
    .my-1 {
	    margin-bottom: 0.15rem !important; 
	    margin-top: 0.15rem !important;
	}
    
    .sm-size {
        height: 23px;
    }
    
    .card-body {
        padding: 0.25rem 1rem; 
    }
    
    input[type='text'] {
    	height: 22px;
    }
    
    .width-160 {
    	width: 80%;
    }
</style>
