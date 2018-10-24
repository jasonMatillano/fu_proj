<template>
  <b-container fluid>
    <b-row class="mb-1 text-center">
      <b-col cols="3"> </b-col>
      <b-col>Start</b-col>
      <b-col>End</b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col cols="3">Date</b-col>
      <b-col><b-form-input v-model="date_start" :disabled="formIsDisabled"/></b-col>
      <b-col><b-form-input v-model="date_end" :disabled="formIsDisabled"/></b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col cols="3">Time</b-col>
      <b-col><b-form-input v-model="time_start" :disabled="formIsDisabled"/></b-col>
      <b-col><b-form-input v-model="time_end" :disabled="formIsDisabled"/></b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col cols="3">XXXX</b-col>
      <b-col><b-form-input v-model="extra_start" :disabled="formIsDisabled"/></b-col>
      <b-col><b-form-input v-model="extra_end" :disabled="formIsDisabled"/></b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col cols="3">Type</b-col>
      <b-col><b-form-input v-model="type_start" :disabled="formIsDisabled"/></b-col>
      <b-col><b-form-input v-model="type_end" :disabled="formIsDisabled"/></b-col>
    </b-row>
  </b-container>
</template>
<script lang="ts">
  import { Component, Prop, Vue } from 'nuxt-property-decorator';
  // import { required, minLength, between } from '/../../../../../node_modules/vuelidate/lib/validators';

	@Component
	export default class Warehouse extends Vue {
    
    formIsDisabled = true

    date_start = "2018/11/11"
    date_end = "2018/12/12"
    time_start = "11:11:11"
    time_end = "12:13:14"
    extra_start = ""
    extra_end = ""
    type_start = "Convert"
    type_end = "PICOSYSTEM"
  }

</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	button {
		font-size: $normalrem;
	}
</style>