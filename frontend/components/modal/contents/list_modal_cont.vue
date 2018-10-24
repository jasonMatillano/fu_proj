<template>
  <b-container fluid>
    <b-row class="row-pad">
      <b-col><!-- blanck --></b-col>
      <b-col> From Code  </b-col>
      <b-col> To Code </b-col>
      <b-col><!-- blanck --></b-col>
    </b-row>
    <b-row class="row-pad">
      <b-col>{{section_name}}<!-- blanck --></b-col>
      <b-col><b-form-input v-model="sampleVal" /></b-col>
      <b-col><b-form-input v-model="sampleVal" /></b-col>
      <b-col>
        <b-btn variant="info" @click="btnProcess('XXX')">
           XXX F5
        </b-btn>
      </b-col>
    </b-row>
    <b-row class="row-pad">
      <b-col><!-- blanck --></b-col>
      <b-col><!-- blanck --></b-col>
      <b-col><!-- blanck --></b-col>
      <b-col>
        <b-btn variant="info" @click="btnProcess('CSV')">
           CSV F7
        </b-btn>
      </b-col>
    </b-row>
    <b-row class="row-pad">
      <b-col><!-- blanck --></b-col>
      <b-col><!-- blanck --></b-col>
      <b-col><!-- blanck --></b-col>
      <b-col>
        <b-btn variant="info" @click="btnProcess('PDF')">
           PDF F8
        </b-btn>
      </b-col>
    </b-row>
    <b-row class="row-pad">
      <b-col><!-- blanck --></b-col>
      <b-col><!-- blanck --></b-col>
      <b-col><!-- blanck --></b-col>
      <b-col>
        <b-btn variant="info" @click="btnProcess('YYY')">
           YYY F12
        </b-btn>
      </b-col>
    </b-row>
  </b-container>
</template>
<script lang="ts">
	import { Component, Prop, Vue } from 'nuxt-property-decorator';

	@Component
	export default class Warehouse extends Vue {
    @Prop()
    section_name: String;

    sampleVal = 1

    btnProcess(val){
      alert(val)
    }

  }  
</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	button{
		font-size: $normalrem;
	}
</style>