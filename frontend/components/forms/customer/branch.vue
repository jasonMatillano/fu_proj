<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="得意先グループCD"
		            label-for="input_sm"
		            class="mb-1">
		            <b-col>
		            	<b-row>
		            		<b-form-input size="sm" v-model="localData.g_code" class="width-8" maxlength="5"></b-form-input>
		            		<b-form-input size="lg" :value="localData.g_name" class="width-15 mx-1 border-0 disabled-input" disabled></b-form-input>
		            	</b-row>
		            </b-col>
			
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="請求先CD"
		            label-for="input_sm"
		            class="mb-1">
		            <b-col>
		            	<b-row>
		            		<b-form-input size="sm" v-model="localData.bill_code" class="width-8"></b-form-input>
		            		<b-form-input size="lg" class="width-15 mx-1 border-0 disabled-input" disabled v-model="localData.bill_name"></b-form-input>
							<b-btn v-b-tooltip.hover 
									title="請求マスターを開く!" 
									size="sm" 
									variant="outline-primary" 
									@click="toBill(localData)" 
									class="py-0 px-2">
									<span><fa :icon="['far','edit']" id="edit"/></span>
							</b-btn>
		            	</b-row>
		            </b-col>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="得意先拠点CD"
		            label-for="input_sm"
		            class="mb-1">
					<b-col>
		            	<b-row>
		            		<b-form-input size="sm" v-model="localData.code" class="width-4"></b-form-input>
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
		            label="得意先拠点カナ"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.kana"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="得意先拠点名"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="略称名"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.short_name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="管轄拠点"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.short_name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="郵便番号"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.postal_code" class="width-8"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="住所"
		            label-for="input_sm"
		            class="mb-1">
            		<b-form-input size="sm" v-model="localData.address_1" class=""></b-form-input>
            		<b-form-input size="sm" v-model="localData.address_2" class="my-1"></b-form-input>
            		<b-form-input size="sm" v-model="localData.address_3" class=""></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="電話番号"
		            label-for="input_sm"
		            class="mb-1">
		            <b-col>
		            	<b-row>
						    <b-form-input size="sm" v-model="localData.phone_number_1" class="width-11"></b-form-input>
							<b-form-input size="sm" v-model="localData.phone_number_2" class="width-11 mx-2"></b-form-input>
							<b-form-input size="sm" v-model="localData.phone_number_3" class="width-11"></b-form-input>        		
		            	</b-row>
		            </b-col>
			
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="FAX番号"
		            label-for="input_sm"
		            class="mb-1">
		            <b-col>
		            	<b-row>
		            		<b-form-input size="sm" v-model="localData.fax_number_1" class="width-11"></b-form-input>
							<b-form-input size="sm" v-model="localData.fax_number_2" class="width-11 mx-2"></b-form-input>
		            	</b-row>
		            </b-col>
			
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="Email"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.email"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="URL"
		            label-for="input_sm"
		            class="mb-1">
			<b-form-input size="sm" v-model="localData.url"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="契約書"
		            label-for="input_sm"
		            class="mb-1">
		            <b-col>
		            	<b-row>
		            		<b-form-input size="sm" v-model="localData.contract" class="width-4"></b-form-input>
		            		<b-form-input size="sm" :value="localData.contract_name" class="width-15 mx-1 border-0" disabled></b-form-input>
		            		<b-form-group horizontal
							            :label-cols="7"
							            label-size="sm"
							            label="単発"
							            label-for="input_sm"
							            class="m-0 ml-5">
										<b-form-checkbox id="単発"
									                     v-model="localData.one_shot"
									                     value="単発"
									                     unchecked-value=" ">
									    </b-form-checkbox>
							</b-form-group>	
		            	</b-row>
		            </b-col>
		</b-form-group>
		<label for="memo">メモ</label>
		<b-form-textarea id="memo"
	                     v-model="localData.memo"
	                     placeholder="ここにメモを書いてください"
	                     :rows="3"
	                     :max-rows="6"
	                     class="mb-2">
	    </b-form-textarea>
		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">キャンセル</b-button>
			<b-button class="button-width-90" variant="primary" v-b-modal="'confirm'" type="submit">{{processName}}</b-button>
		</b-col>
	</b-form>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';

	@Component
	export default class Cbranch extends Vue {

		@Prop()
		formdata: Object;

		@Prop()
		processName: String;

		localData = {};

		submit(){
			this.$emit('submit', this.localData)
		}
		clickCancel(){
			this.$emit('clickCancel', false)
		}
		toBill(data){
			this["$router"].push({ name: 'customer-bill', params:{data} })
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
	@import '@/assets/scss/forms.scss'
</style>