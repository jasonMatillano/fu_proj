<template>
    <b-form>
    <b-col class="row-pad-15">
			<b-row>
				<b-col class="p-0">
					<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>汎用 {{ processName != ''? '＞' + processName : '' }}</span></h5>
				</b-col>
			</b-row>
		</b-col>
		<b-form-group horizontal
		            :label-cols="5"
		            label-size="sm"
		            :label="label[table][0]"
		            label-for="input_sm">
					<b-form-input size="sm" type = "text" v-model="procval1" maxlength="5" @input="changep1"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					v-if="table==0"
		            :label-cols="5"
		            label-size="sm"
		            :label="label[table][1]"
		            label-for="input_sm">
			<b-form-input size="sm" type="text" v-model="procval2" @input="changep2"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					v-if="table==1"
		            :label-cols="5"
		            label-size="sm"
		            :label="label[table][1]"
		            label-for="input_sm">
			<b-form-input size="sm" type="text" v-model="procval4" @input="changep4"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					v-if="table==1"
		            :label-cols="5"
		            label-size="sm"
		            :label="label[table][1]"
		            label-for="input_sm">
			<b-form-input size="sm" type="text" v-model="procval5" @input="changep5"></b-form-input>
		</b-form-group>
		<b-form-group horizontal
					v-if="table==1"
		            :label-cols="5"
		            label-size="sm"
		            :label="label[table][1]"
		            label-for="input_sm">
			<b-form-input size="sm" type="text" v-model="procval6" @input="changep6"></b-form-input>
		</b-form-group>
		
		<b-form-group horizontal
		        :label-cols="5"
		        label-size="sm"
		        :label="label[table][2]"
		        label-for="input_sm">
		<b-form-checkbox v-model="procval3" v-bind:checked="procval3?'checked':'false'" @input="changep3"></b-form-checkbox>
		</b-form-group>
		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="fade">キャンセル</b-button>
			<b-button class="button-width-90" variant="primary" @click="process">{{processName}}</b-button>
		</b-col>
    </b-form>
</template>
<script lang="ts">
    import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';
    
	@Component
	export default class Warehouse extends Vue {
        @Prop()
		section_name: String;
        
        @Prop()
        procval1: String;
        
        @Prop()
		procval2: String;
        
        @Prop()
		procval3: boolean;
        
        @Prop()
		procval4: String;
        
        @Prop()
		procval5: String;
        
        @Prop()
		procval6: number;

        @Prop()
        processName: String;

        @Prop()
        table: number;

        @Prop()
		name_val: String;
		
		@Prop()
        end_val: boolean;

		localData = {};
		
		cancel_lang = ""
		end_lang = ""
		name_lang = ""
			
		label = [['汎用ID','汎用名','終了'],['汎用ID','汎用項目CD','終了','汎用項目名','汎用項目SEQ']];

		created() {
			this.cancel_lang = this.$t("Auth.components.forms.product.cancel").toString()
			this.end_lang = this.$t("Auth.components.forms.product.end").toString()
			this.name_lang = this.$t("Auth.components.forms.product.name").toString()
		}

		clickCancel(){
			this.$emit('clickCancel', false)
		}
		
		mounted() {
		//	this.localData = Object.assign({}, this.formdata)
		}

		@Watch('formdata')
		onChanged(val: string, oldVal: string){
			this.localData = Object.assign({}, val)
        }
        
        // added methods

		fade(val) {
            this.$emit('fade', val)
        }

        process(val) {
            this.$emit('process', val)
        }

        changeCD(event){
            this.$emit('changeCD',event)
        }

        changeName(event){
            this.$emit('changeName',event)
		}
		
        changep1(event){
			this.$emit('changep1',event)
		}
        
        changep2(event){
			this.$emit('changep2',event)
		}
        
        changep3(event){
			this.$emit('changep3',event)
		}
        
        changep4(event){
			this.$emit('changep4',event)
		}
        
        changep5(event){
			this.$emit('changep5',event)
		}
        
        changep6(event){
			this.$emit('changep6',event)
		}
    }
    
</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	@import '@/assets/scss/forms.scss';
</style>