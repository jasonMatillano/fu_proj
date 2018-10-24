<template>
	<b-modal :id="id" :title="confirmation + ' ＞ ' + processName " header-bg-variant="light" v-model="show">
	    <p class="m-0">{{ continue1 }}?</p>
	    <div slot="modal-footer" class="john">
         	<b-col class="text-right p-0">
				<b-button class="button-width-90 mx-2" variant="danger" @click="show=false">{{cancel}}</b-button>
				<b-button class="button-width-90 mx-2" variant="primary" @click="submit">{{yes}}</b-button>
			</b-col>
       </div>
	</b-modal>
</template>
<script lang="ts">
	import { Component, Prop, Vue } from 'nuxt-property-decorator';

	@Component
	export default class Warehouse extends Vue {
		@Prop({default:""})
		id:string

		@Prop()
		processName: String;

		@Prop()
		processAction: String;

		show = false
		title = ""
		confirmation = ""
		continue1 = ""
		yes = ""
		cancel = ""

		created () {
			this.confirmation = this.$t("Auth.pages.manufacturer.confirmation").toString()
			this.continue1 = this.$t("Auth.pages.manufacturer.continue").toString()
			this.yes = this.$t("Auth.pages.manufacturer.yes").toString()
			this.cancel = this.$t("Auth.pages.manufacturer.cancel").toString()			
		}

		// mounted() {
		// 	this.checkProps()
		// }

		// checkProps() {
		// 	if (this.processName == null) {
		// 		alert("create props for your component modal.vue")
		// 	} else {
		// 		//
		// 	}
		// }

		submit(){
			// alert("form submit modal: button submit was clicked > " + this.processAction)
			this.show = false
			this.$emit('submit',this.processAction)
		}
	}

</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	button{
		font-size: $normalrem;
	}
</style>