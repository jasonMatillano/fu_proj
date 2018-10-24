<template>
	<transition name="fade-y">
		<div class="div-pad" v-if="node">
			<div 
				:style="{'margin-left': depth * 20 + 'px'}"
				class="node"
				:class="{highlighted : selectedNode.nodeData.name==node.nodeData.name}">
		 		<span v-if="node.childNodes"
					@click="expand = !expand"> 
					[{{expand ? '-' : '+' }}]
				</span>
				<span @click="nodeSelected(node)">{{node.nodeData.code}} {{node.nodeData.name}}</span>
		 	</div>
		 	<Browser
						v-show= "expand"
		 				v-for="child in node.childNodes"
						:key="child.nodeData.id"
						:node="child"
						:depth="depth + 1"
						:selectedNode="selectedNode"
						v-on:nodeSelected="selectedNodeChanged"
		 				/>
		</div>
	</transition>
</template>
<script lang="ts">
	import { Component,  Prop, Vue, Watch } from 'nuxt-property-decorator'
	import { TreeNode } from '@/assets/ts/TreeNode.ts'

	@Component
	export default class Browser extends Vue {	

		expand = true
		
		@Prop()
		node: TreeNode;

		@Prop({default: 0})
		depth: Number;

		@Prop()
		selectedNode: TreeNode;

		nodeSelected(name:TreeNode){			
			this.$emit('nodeSelected' , name)
		}

		selectedNodeChanged(changedNodeName:TreeNode){
			this.$emit('nodeSelected' , changedNodeName)
		}

		mounted(){
			this.loadedVal()
		}

		loadedVal(){
			this.$emit('treeVal' , this.depth)
		}
	}
</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	.highlighted{
		background-color: #bee5eb;
	}
	div{
		&.div-pad{
			border: 1px solid black;
		    margin-left: -1px;
		    margin-right: -1px;
		    margin-bottom: -1px;
		    &:first-child{
		    	border-top: none;
		    }
		}
	}
	.node{
		font-size: 12px !important;
		@include give-padding(1%);
	}
	span{
		cursor: pointer;
	}
	.fade-y{
		&-enter{
			transform: translateY(-10px) !important;
	  		opacity: 0;
	  		&-active{
	  			transition: all 0.3s ease;
	  		}
		}
		&-leave{
			&-active{
				 transition: all 0.3s ease;
			}
			&-to{
				transform: translateY(-10px) !important;
  				opacity: 0;
			}
		}
	}
</style>