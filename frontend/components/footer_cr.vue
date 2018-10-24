<template>
	<b-row class="row-pad">
		<b-col>
			<b-button :variant="fnFunctions[0].variant" :disabled="fnFunctions[0].isdisabled"><!--F1-->
				{{fnFunctions[0].label}} {{fnFunctions[0].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[1].variant" :disabled="fnFunctions[1].isdisabled" @click="fn_cancel()"><!--F2-->
				{{fnFunctions[1].label}} {{fnFunctions[1].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[2].variant" :disabled="fnFunctions[2].isdisabled" @click="fn_delete()"><!--F3-->
				{{fnFunctions[2].label}} {{fnFunctions[2].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[3].variant" :disabled="fnFunctions[3].isdisabled" @click="fn_new()"><!--F4-->
				{{fnFunctions[3].label}} {{fnFunctions[3].value}} 
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[4].variant" :disabled="fnFunctions[4].isdisabled"><!--F5-->
				{{fnFunctions[4].label}} {{fnFunctions[4].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[5].variant" :disabled="fnFunctions[5].isdisabled" @click="fn_date()"><!--F6-->
				{{fnFunctions[5].label}} {{fnFunctions[5].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[6].variant" :disabled="fnFunctions[6].isdisabled"><!--F7-->
				{{fnFunctions[6].label}} {{fnFunctions[6].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[7].variant" :disabled="fnFunctions[7].isdisabled" @click="fn_list()"><!--F8-->
				{{fnFunctions[7].label}} {{fnFunctions[7].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[8].variant" :disabled="fnFunctions[8].isdisabled"><!--F9-->
				{{fnFunctions[8].label}} {{fnFunctions[8].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[9].variant" :disabled="fnFunctions[9].isdisabled"><!--F10-->
				{{fnFunctions[9].label}} {{fnFunctions[9].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[10].variant" :disabled="fnFunctions[10].isdisabled"><!--F11-->
				{{fnFunctions[10].label}} {{fnFunctions[10].value}}
            </b-button>
		</b-col>
		<b-col >
			<b-button :variant="fnFunctions[11].variant" :disabled="fnFunctions[11].isdisabled" @click="fn_menu()"><!--F12-->
				{{fnFunctions[11].label}} {{fnFunctions[11].value}}
            </b-button>
		</b-col>
	</b-row>
</template>
<script lang="ts">
	import { Component, Vue, Prop } from 'nuxt-property-decorator';
	import { Keyboard } from '@/assets/ts/keyboard.ts'
	
	@Component
	export default class Footer extends Vue {

		// footer options assigning
		fnFunctions = [ // footer keys initial value
				{label:"F1", variant: "secondary", value: "", isdisabled: true},//<--F1-->
				{label:"F2", variant: "secondary", value: "", isdisabled: true},//<--F2-->
				{label:"F3", variant: "secondary", value: "", isdisabled: true },//<--F3-->
				{label:"F4", variant: "secondary", value: "", isdisabled: true },//<--F4-->
				{label:"F5", variant: "secondary", value: "", isdisabled: true },//<--F5-->
				{label:"F6", variant: "secondary", value: "", isdisabled: true },//<--F6-->
				{label:"F7", variant: "secondary", value: "", isdisabled: true },//<--F7-->
				{label:"F8", variant: "secondary", value: "", isdisabled: true },//<--F8-->
				{label:"F9", variant: "secondary", value: "", isdisabled: true },//<--F9-->
				{label:"F10", variant: "secondary", value: "", isdisabled: true },//<--F10-->
				{label:"F11", variant: "secondary", value: "", isdisabled: true },//<--F11-->
				{label:"F12", variant: "secondary", value: "", isdisabled: true },//<--F12-->
			]

		@Prop()
		F1_val: String;
		@Prop()
		F2_val: String;
		@Prop()
		F3_val: String;
		@Prop()
		F4_val: String;
		@Prop()
		F5_val: String;
		@Prop()
		F6_val: String;
		@Prop()
		F7_val: String;
		@Prop()
		F8_val: String;
		@Prop()
		F9_val: String;
		@Prop()
		F10_val: String;
		@Prop()
		F11_val: String;
		@Prop()
		F12_val: String;
		
		created() {
			this.setValueForFnKeys()
		}

		setValueForFnKeys() {
			this.$set(this.fnFunctions[0], "value", this.F1_val)// F1
			this.$set(this.fnFunctions[1], "value", this.F2_val)// F2 
			this.$set(this.fnFunctions[2], "value", this.F3_val)// F3
			this.$set(this.fnFunctions[3], "value", this.F4_val)// F4 
			this.$set(this.fnFunctions[4], "value", this.F5_val)// F5
			this.$set(this.fnFunctions[5], "value", this.F6_val)// F6
			this.$set(this.fnFunctions[6], "value", this.F7_val)// F7
			this.$set(this.fnFunctions[7], "value", this.F8_val)// F8
			this.$set(this.fnFunctions[8], "value", this.F9_val)// F9
			this.$set(this.fnFunctions[9], "value", this.F10_val)// F10
			this.$set(this.fnFunctions[10], "value", this.F11_val)// F11
			this.$set(this.fnFunctions[11], "value", this.F12_val)// F12 
		}

		beforeMount() {
			this.assignVariantDisable()
		}
		
		assignVariantDisable(){	
			this.fnFunctions.forEach(element => {
				if(element.value!=null) {
					this.$set(element, "variant", "success")// F1
					this.$set(element, "isdisabled", false)// F2 
				} else {
					// do nothing
				}
			});
		}

		// setup footer keys on page mount
		mounted() {
			let s = Keyboard.getInstance
			let k = Keyboard.keys
			var thisvue = this

				s.addPress(k.F1, (result) => {
					// no function
				})
				s.addPress(k.F2, (result) => {
					this.fn_cancel()
				})
				s.addPress(k.F3, (result) => {
					this.fn_delete()
				})
				s.addPress(k.F4, (result) => {
					this.fn_new()
				})
				s.addPress(k.F5, (result) => {
					// no function
				})
				s.addPress(k.F6, (result) => {
					this.fn_date()
				})
				s.addPress(k.F7, (result) => {
					// no function
				})
				// s.addPress(k.F8, (result) => {
				// 	this.fn_list()
				// })
				s.addPress(k.F9, (result) => {
					// no function
				})
				s.addPress(k.F10, (result) => {
					// no function
				})
				s.addPress(k.F11, (result) => {
					// no function
				})
				s.addPress(k.F12, (result) => {
					this.fn_menu()
				})
				s.addPress(k.ENTER, (result) => {
					// thisvue.register(12,thisvue.fnFunctions[12].value)
				})
		}

		// product footer functions
		fn_cancel(){
			this.$emit('clickCancel', false)
		}
		fn_delete () {
			this.$emit('clickDeleteBtn', {name:this.fnFunctions[2].value})
		}
		fn_new () {
			this.$emit('clickNewBtn', {name:this.fnFunctions[3].value})
		}
		fn_date() {
			this.$emit('clickDateBtn', {name:this.fnFunctions[5].value})
		}
		// fn_list() {
		// 	this.$emit('clickListBtn', {name:this.fnFunctions[7].value})
		// }
		fn_menu() {
			this.$emit('clickMenuBtn', {name:this.fnFunctions[11].value})
		}

	}

</script>
<style lang="scss">
	@import '@/assets/scss/mixins.scss';
	.row-pad{
		div{
			&.col{
				@include give-padding(1%);
				button{
					@include give-width(100%);
				}
			}
		}
	}
</style>