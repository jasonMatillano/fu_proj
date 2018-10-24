<template>
	<b-modal :id="idMod" title="確認" header-bg-variant="light" v-model="show">
	    <p class="m-0">よろしいですか。</p>
	    <div slot="modal-footer" class="john">
         	<b-col class="align-right p-0">
				<b-button class="button-width-90 mx-2" variant="danger" @click="show=false">いいえ</b-button>
				<b-button class="button-width-90" variant="primary" @click="submit">はい</b-button>
			</b-col>
       </div>
	</b-modal>
</template>
<script lang="ts">
	import { Component, Prop, Vue } from 'nuxt-property-decorator';

	@Component
	export default class Warehouse extends Vue {
		show = false

		@Prop({default:""})
		idMod:string

		submit(){
			this.show = false
			this.$emit('submit',true)
		}
	}

</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	button{
		font-size: $normalrem;
	}
</style>