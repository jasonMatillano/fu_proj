<template>
	<select @change="set_lang" v-model="locale">
	  <option v-for="(lang, i) in langs" :key="`Lang${i}`" :value="lang" >{{ lang }}</option>
	</select>
</template>

<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator'
	import { Utils } from '@/assets/ts/Utils.ts'

	@Component 
	export default class Lang extends Vue {
		langs = ['ja', 'en'] 
		locale = ''

		set_lang(evt) {
			this.$i18n.locale = this.locale
			Utils.getInstance.setLocale(this.locale)
		}

		mounted () {
			this.locale = this.$i18n.locale
		}
	}
</script>


<style lang="scss">
	select {
		width: 85%;
	}
</style>