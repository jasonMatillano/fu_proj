<template>
  <div id="layout-container">
    <header>
      <c_header></c_header>
    </header>
    <main class="layout-content">
      <nuxt/>
    </main>
    <footer>
      <c_footer></c_footer>
    </footer>
  </div>
</template>
<script lang="ts">
  import Lang from "@/components/lang.vue";
  import { Keyboard, Listener } from "@/assets/ts/keyboard.ts";
  import { Component, Vue } from 'nuxt-property-decorator';
  import c_header from "@/components/layout/header";
  import c_footer from "@/components/layout/footer";

  @Component({
    components: { 
      Lang,
      c_header,
      c_footer 
    }
  })
  export default class Default extends Vue {
  }
</script>
　　
<style lang="scss" scoped>
  @import '@/assets/scss/mixins.scss';
  footer {
    position:absolute;
    bottom:0;
    width:100%;
    height: 50px;   /* Height of the footer */
    background-color: $darkblue;
  }
 </style>
 <style lang="scss">
  @import '@/assets/scss/mixins.scss';
  #layout-container{
   min-height:100%;
    position:relative;
    main {
      &.layout-content{
        @include give-padding(49px 1%)
      }
    }
  }
 </style>
