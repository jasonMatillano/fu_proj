<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            label="主担当者CD"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" v-model="localData.code" class="width-5"></b-form-input>
		</b-form-group>
		<transition name="fade">
			<b-form-group horizontal
			            :label-cols="5"
			            label-size="sm"
			            label="得意先グループｶﾅ"
			            label-for="input_sm"
			            class="my-1"
			            v-if="processName == '変更'">
				<b-form-input size="sm" v-model="localData.name"></b-form-input>
			</b-form-group>
		</transition>
		<transition name="fade">
			<b-form-group horizontal
			            :label-cols="5"
			            label-size="sm"
			            label="得意先グループｶﾅ"
			            label-for="input_sm"
			            class="my-1"
			            v-if="processName != '変更'">
				<b-form-input size="sm" v-model="localData.name"></b-form-input>
			</b-form-group>
		</transition>
		
		<b-form-group horizontal
		        :label-cols="5"
		        label-size="sm"
		        label="終了"
		        label-for="input_sm"
		        class="my-1">
				<b-form-checkbox id="終了"
			                     value="終了"
			                     unchecked-value=" ">
			    </b-form-checkbox>
		</b-form-group>	
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