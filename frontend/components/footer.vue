<template>
	<b-row class="row-pad">
		<b-col v-for="(fn, i) in buttonFunctions">
			<b-button :variant="fn.variant" @click="buttonClickEventHandler(fn.event)" :disabled="!fn.event.isActive" :key="i">
				{{ i }} {{ fn.name }}
            </b-button>
		</b-col>
	</b-row>
</template>
<script lang="ts">
	import { Component,Prop, Vue } from 'nuxt-property-decorator';
	import { Keyboard } from '@/assets/ts/keyboard.ts'
	import { UIEvent } from '@/assets/ts/UIEvent.ts'
	import  KeyPressableComponent  from '@/assets/ts/KeyPressableComponent.ts'
	import {Keys} from '@/assets/ts/keys.ts'
	
	interface Button {
		name: string
		key: string
	} 

	@Component({})
	export default class Footer extends KeyPressableComponent {

		buttonFunctions = {
			"F1": 	{ 	variant: "secondary",  	isActive: false, name: " ",event: {} },
			"F2": 	{  	variant: "secondary", 	isActive: false, name: " ",event: {} },
			"F3": 	{  	variant: "secondary", 	isActive: false, name: " ",event: {} },
			"F4": 	{  	variant: "secondary", 	isActive: false, name: " ",event: {} }, 
			"F5": 	{  	variant: "secondary", 	isActive: false, name: " ",event: {} },
			"F6": 	{  	variant: "secondary",  	isActive: false, name: " ",event: {} },
			"F7": 	{  	variant: "secondary", 	isActive: false, name: " ",event: {} },
			"F8": 	{  	variant: "secondary", 	isActive: false, name: " ",event: {} },
			"F9": 	{  	variant: "secondary",  	isActive: false, name: " ",event: {} },
			"F10": 	{ 	variant: "secondary", 	isActive: false, name: " ",event: {} },
			"F11": 	{	variant: "secondary",  	isActive: false, name: " ",event: {} },
			"F12": 	{	variant: "secondary",  	isActive: false, name: " ",event: {} },
		}

		//overrides from parent class
		mapEvents(){
			this.UIEvents=this.events
			if(this.UIEvents){
				this.UIEvents!.forEach(e=>{				
					this.buttonFunctions[Keys[e.key]].isActive = e.isActive
					this.buttonFunctions[Keys[e.key]].name = e.eventName
					this.buttonFunctions[Keys[e.key]].variant = "success"
					this.buttonFunctions[Keys[e.key]].event = e					
					this.addKeyPressListener(e.key)
				})
			}
		}

		buttonClickEventHandler(event){
			if(event.isActive)
				this.$emit('eventTriggered', event)			
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