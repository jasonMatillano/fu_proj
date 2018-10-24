<template>
    <b-form>
    	<b-col class="row-pad-15">
    		<b-row>
				<!-- <p>
					formData : {{formData}}
				</p> -->
    			<b-col class="p-0">
    				<h5 for="edit"><span>
    					<fa :icon="['far','edit']" id="edit" /> 
						{{section_name}} ＞ {{processName}}
						</span>
					</h5>
    			</b-col>
    		</b-row>
    	</b-col>
    	<b-form-group 
			horizontal 
			:label-cols="2" 
			label-size="sm" 
			label="CD" 
			label-for="input_sm"
			>
    		<b-form-input size="sm" class="width-6"
				:value="formData.val_code" 
				:disabled="formIsDisabled"
				@input="changeCD" 
			></b-form-input>
    	</b-form-group>
    	<b-form-group 
			horizontal 
			:label-cols="2" 
			label-size="sm" 
			:label="productForm.name" 
			label-for="input_sm"
			>
    		<b-form-input size="sm" 
				:value="formData.val_name" 
				:disabled="formIsDisabled"
				@input="changeName" 
			></b-form-input>
    	</b-form-group>
			<b-form-group 
				horizontal
		        :label-cols="4"
		        label-size="sm"
		        :label="productForm.end"
		        label-for="input_sm"
		        class="my-1"
				>
				<b-form-checkbox id="終了"
			        v-model="formData.val_end"
					:disabled="formIsDisabled"
					@input="changeEndVal"
				></b-form-checkbox>
			    	<b-col class="text-right p-0">
    		<b-button class="button-width-90 mx-2" variant="danger" @click="closeForm(false)">{{productForm.cancel}}</b-button>
    		<b-button class="button-width-90" variant="primary" @click="submitForm(processName)">{{processName}}</b-button>
    	</b-col>
		</b-form-group>

    </b-form>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator'
	
	@Component
	export default class Warehouse extends Vue {
        @Prop()
		section_name: String;

        @Prop()
        processName: String;

        @Prop()
        formIsDisabled: Boolean;

        @Prop()
        formData: {val_code: string, val_name: string, val_kana: string, val_end: boolean , val_ind: number};
		
		cancel_lang = ""
		end_lang = ""
		name_lang = ""

		productForm = "" // chage language handler
		$t = null

		created() {
			this.productForm = this.$t("Auth.components.forms.product")
			// this.cancel_lang = this.$t("Auth.components.forms.product.cancel").toString()
			// this.end_lang = this.$t("Auth.components.forms.product.end").toString()
			// this.name_lang = this.$t("Auth.components.forms.product.name").toString()
		}

		clickCancel(){
			this.$emit('clickCancel', false)
		}
        
        closeForm(val) {
            this.$emit('clickCancel', val)
        }

        submitForm(val) {
            this.$emit('submitForm', val)
        }

        changeCD(event){
            this.$emit('changeCD',event)
        }

        changeName(event){
			this.$emit('changeName',event)
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