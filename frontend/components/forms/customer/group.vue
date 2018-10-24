<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="得意先グループCD"
		            label-for="input_sm"
		            class="my-1">
		            <b-col>
		            	<b-row>
		            		<b-form-input size="sm" v-model="localData.code" class="width-8"></b-form-input>
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
		            :label-cols="5"
		            label-size="sm"
		            label="得意先グループ名"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="得意先グループｶﾅ"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.kana"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="得意先グループ略称"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.short_name"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="保証対象区分"
		            label-for="input_sm"
		            class="my-1">
			<b-form-checkbox id="保証対象区分"
		                     v-model="localData.obj_class"
		                     value="保証対象区分"
		                     unchecked-value=" ">
		    </b-form-checkbox>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="業種"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.industry"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="与信限度額"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.credit_limit" class="width-15"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="ランク"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.rank" class="width-5"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="評点"
		            label-for="input_sm"
		            class="my-1">
		            <b-col>
		            	<b-row class="mb-1">
		            		<b-form-input size="sm" v-model="localData.score_1" class="width-5"></b-form-input>
		            		<b-form-input size="sm" v-model="localData.score_1_date" class="width-10 mx-1 border-0" disabled placeholder="yyyy/mm/dd"></b-form-input>
		            	</b-row>
		            	<b-row class="mb-1">
		            		<b-form-input size="sm" v-model="localData.score_2" class="width-5"></b-form-input>
		            		<b-form-input size="sm" v-model="localData.score_2_date" class="width-10 mx-1 border-0" disabled placeholder="yyyy/mm/dd"></b-form-input>
		            	</b-row>
		            	<b-row class="mb-1">
		            		<b-form-input size="sm" v-model="localData.score_3" class="width-5"></b-form-input>
		            		<b-form-input size="sm" v-model="localData.score_3_date" class="width-10 mx-1 border-0" disabled placeholder="yyyy/mm/dd"></b-form-input>
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
	export default class Cgroup extends Vue {

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
		input,select,textarea{
			border: 1px solid $black;
			font-size: $normalrem;
			font-weight: 600;
			&.width{
				@for $i from 1 through 15 {
				  	&-#{$i}{
						@include give-width($i * 10px);
					}
				}
			}
		}
		button{
			&.button-width{
					&-90{
						@include give-width(90px);
						font-size: $normalrem;
					}
				}
		}
		.custom-control{
			min-height: 0.58rem;
		}
	}
</style>