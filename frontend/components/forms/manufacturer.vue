<template>
	<b-form @submit.prevent="submit">
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            label="CD"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" class="width-5"
				v-model="formdata.code" 
				:disabled="formIsDisabled"
				@input="changeCD" 
			></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            :label="name_lang"
		            label-for="input_sm"
		            class="my-1">
			<b-form-input size="sm" 
				v-model="formdata.name"
				:disabled="formIsDisabled"
				@input="changeName" 
			></b-form-input>
		</b-form-group>
		<b-form-group horizontal
		            :label-cols="4"
		            label-size="sm"
		            :label="end_lang"
		            label-for="input_sm"
		            class="my-1">
					<b-form-checkbox 
						id="終了"
				        v-model="formdata.end"
						@input="changeEndVal"
					></b-form-checkbox>
		</b-form-group>
		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">{{cancel_lang}}</b-button>
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

		@Prop()
		formIsDisabled: Boolean;

		// localData = {};
		cancel_lang = ""
		name_lang = ""
		end_lang = ""

		created() {
			this.cancel_lang = this.$t("Auth.pages.manufacturer.cancel").toString()
			this.name_lang = this.$t("Auth.pages.manufacturer.name").toString()
			this.end_lang = this.$t("Auth.pages.manufacturer.end").toString()
		}

		mounted() {
		  	//this.localData = Object.assign({}, this.formdata)
		}

		// @Watch('formdata')
		// onChanged(val: string, oldVal: string){
		// 	this.localData = Object.assign({}, val)
		// }
		
		submit(){
			this.$emit('submit', this.formdata)
		}

		clickCancel(){
			this.$emit('clickCancel', false)
		}
		
        changeCD(event){
            this.$emit('changeCD',event)
        }

        changeName(event){
            this.$emit('changeName',event)
		}

		changeNameKana(event){
            this.$emit('changeNameKana',event)
		}
		
		changeEndVal(event){
			this.$emit('changeEndVal',event)
		}

	}
</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	@import '@/assets/scss/forms.scss';
	
</style>