<template>
  <div @click="closeModalPr()">
    <b-modal v-model="modalPrShowing"
             :header-bg-variant="headerBgVariant"
             :header-text-variant="headerTextVariant"
             :body-bg-variant="bodyBgVariant"
             :body-text-variant="bodyTextVariant"
             :footer-bg-variant="footerBgVariant"
             :footer-text-variant="footerTextVariant">

       <div slot="modal-header" class="w-100">
         <h4 class="float-left">{{section_name}} > {{modalType}}</h4>
       </div>

        <c_modals_product_date
          v-if="'Date'== modalType|| '日付'== modalType"
        ></c_modals_product_date>
        
        <c_modals_product_list
          v-if="'List'== modalType|| 'リスト'== modalType"
          :section_name="section_name"
        ></c_modals_product_list>

       <div slot="modal-footer" class="w-100">
          <b-row class="row-pad">
            <b-col></b-col>
            <b-col></b-col>
            <b-col>
              <b-btn variant="primary" @click="okAlert">
                Ok
              </b-btn>
            </b-col>
            <b-col>
              <b-btn variant="danger" @click="closeModalPr">
                Close
              </b-btn>
            </b-col>
          </b-row>
       </div>
       
    </b-modal>
  </div>
</template>
<script lang="ts">
  import { Component, Prop, Vue } from 'nuxt-property-decorator';
  
  import c_modals_product_date from '@/components/modal/contents/date_modal_cont'
	import c_modals_product_list from '@/components/modal/contents/list_modal_cont'

	@Component({
		components:{
      c_modals_product_date,
      c_modals_product_list,
		},
  })
  
	export default class Warehouse extends Vue {
    @Prop()
    modalPrShowing: Boolean;
    
    @Prop()
    section_name: String;

    @Prop()
    modalType: String;
        
    // close modal using emit
    closeModalPr() {
      this.$emit('closeModalPr',false)
    }

    okAlert(){
      alert("modal ok clicked")
    }

    // styles
		headerBgVariant = 'info'
		headerTextVariant = 'light'
		bodyBgVariant = 'light'
		bodyTextVariant = 'dark'
		footerBgVariant = 'info'
		footerTextVariant = 'light'
    }

</script>
<style lang="scss" scoped>
	@import '@/assets/scss/mixins.scss';
	button{
		font-size: $normalrem;
	}
</style>