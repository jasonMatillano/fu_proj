<template>
	<b-row class="row-pad">
		<b-col v-for="fnKey in fnFunctions" v-bind:key="fnKey.label">
			<b-button :variant="fnKey.variant" :disabled="fnKey.isdisabled" @click="passFnName(fnKey)"><!--F1-->
				{{fnKey.label}} {{fnKey.name}}
			</b-button>
		</b-col>
	</b-row>
</template>
<script lang="ts">
	import { Component, Vue, Prop } from 'nuxt-property-decorator';
	import { Keyboard } from '@/assets/ts/keyboard.ts'
	
	@Component
	export default class Footer extends Vue {

		@Prop()
		fnFunctions: Array<Object>;

		fnFunctionsInFooter= []

		// setup footer keys on page mount
		mounted() {
			if(this.fnFunctions == null) {
				alert('no footer found, assign values to var: fnFunctions')
			} else {
				this.copyValuesFromProp()
				this.assignValuesToFnKeys()
			}
		}

		copyValuesFromProp(){
			this.fnFunctions.forEach(element => {
				this.fnFunctionsInFooter.push(element)
			});
		}

		assignValuesToFnKeys(){
			let s = Keyboard.getInstance
			let k = Keyboard.keys

			this.fnFunctionsInFooter.forEach(element => {
				// alert(JSON.stringify(element))
				if(element.name==""){
					// dont apply action
				} else {
					s.addPress(parseInt(element.key), (result) => {
						this.passFnName(element)
						
					})
				}
			});
		}

		passFnName(item){
			this.$emit('fnClickOrKeyDown', {name:item.name, success: "true"})
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