<template>
	<b-form @submit.prevent="submit">
		<!-- Styled -->
		<b-form-file id="file1" placeholder="ファイルを選んでください"　class="my-1"></b-form-file>
		<b-form-file id="file2" placeholder="ファイルを選んでください"　class="my-1"></b-form-file>
		<b-form-file id="file3" placeholder="ファイルを選んでください"　class="my-1"></b-form-file>
		<b-form-file id="file4" placeholder="ファイルを選んでください"　class="my-1"></b-form-file>
		<b-form-file id="file5" placeholder="ファイルを選んでください"　class="my-1"></b-form-file>

		<b-col class="text-right p-0">
			<b-button class="button-width-90 mx-2" variant="danger" @click="clickCancel">キャンセル</b-button>
			<b-button class="button-width-90" variant="primary" type="submit">アップロード</b-button>
		</b-col>
	</b-form>
</template>
<script lang="ts">
	import { Component, Prop, Watch,Vue } from 'nuxt-property-decorator';

	@Component
	export default class Cgroup extends Vue {

		@Prop()
		formdata: Object;

		localData = {};

		submit(){
			this.$emit('submit')
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
		input,select{
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
		
	}
</style>