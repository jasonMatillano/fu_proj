<template>
  <div @click="closeDateModal()">
    <b-modal v-model="dateModalShowing"
             :header-bg-variant="headerBgVariant"
             :header-text-variant="headerTextVariant"
             :body-bg-variant="bodyBgVariant"
             :body-text-variant="bodyTextVariant"
             :footer-bg-variant="footerBgVariant"
             :footer-text-variant="footerTextVariant"
             >
       <div slot="modal-header" class="w-100">
         <h4 class="float-left">Course Modal</h4>
       </div>
       <b-container fluid>
         <b-row class="mb-1 r text-center">
           <b-col cols="3"> </b-col>
           <b-col>text here</b-col>
           <b-col>text here</b-col>
         </b-row>
         <b-row class="mb-1">
           <b-col cols="3">日付</b-col>
           <b-col><b-form-input size="sm" disabled class="input-width-50 text-center" :value="newDate"/></b-col>
           <b-col><b-form-input size="sm" disabled class="input-width-50 text-center" :value="newDate"/></b-col>
         </b-row>
         <b-row class="mb-1">
           <b-col cols="3">text here</b-col>
           <b-col><b-form-input size="sm" disabled class="input-width-50 text-center" :value="newTime"/></b-col>
           <b-col><b-form-input size="sm" disabled class="input-width-50 text-center" :value="newTime"/></b-col>
         </b-row>
         <b-row class="mb-1">
           <b-col cols="3">text here</b-col>
           <b-col><b-form-input size="sm" disabled class="input-width-50 text-center" value="646"/></b-col>
           <b-col><b-form-input size="sm" disabled class="input-width-50 text-center" value="646"/></b-col>
         </b-row>
         <b-row class="mb-1">
           <b-col cols="3">ID</b-col>
           <b-col><b-form-input size="sm" disabled class="input-width-50 text-center" value="ZHIMIZU"/></b-col>
           <b-col><b-form-input size="sm" disabled class="input-width-50 text-center" value="ZHIMIZU"/></b-col>
         </b-row>
       </b-container>
       <div slot="modal-footer" class="w-100">
         <!-- <p class="float-left">text here</p> -->
         <b-btn size="lg" class="float-right" variant="primary" @click="closeDateModal">
           Close
         </b-btn>
       </div>
    </b-modal>
  </div>
</template>
<script lang="ts">
	import { Component, Prop, Vue } from 'nuxt-property-decorator';

	@Component
	export default class Warehouse extends Vue {

    newDate = ""
    newTime = ""

    @Prop()
    dateModalShowing: Boolean;
        
     //  close modal using emit
    closeDateModal() {
        this.$emit('closeDateModal',false)
    }

		variants =  [
			'primary', 'secondary', 'success', 'warning', 'danger', 'info', 'light', 'dark'
		]
		headerBgVariant = 'info'
		headerTextVariant = 'light'
		bodyBgVariant = 'light'
		bodyTextVariant = 'dark'
		footerBgVariant = 'light'
    footerTextVariant = 'dark'
    
    created () {
      this.getDate()
    }

    getDate() {
      var dateObj = new Date()
      var year = dateObj.getFullYear()
      var month = dateObj.getMonth()
      var day = dateObj.getDate()
      var hours = dateObj.getHours()
      var min = dateObj.getMinutes()
      var sec = dateObj.getSeconds()
      var time = dateObj.getTime()
      
      this.newDate = year + "/" + month + "/" + day
      this.newTime = hours + ":" + min + ":" + sec
    } 

  }

</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	button{
		font-size: $normalrem;
	}
</style>