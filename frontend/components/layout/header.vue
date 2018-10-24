<template>
  <nav class="header-container">
    <header class="topnav">                 
        <a href="javascript:void(0);" class="icon" @click="toggleMenu()">      
            <span class="btn-container">
                <img src="~assets/images/nav-button.png"/>
                <!-- <span><fa :icon="['far','list-alt']" id="edit"/></span> -->
            </span>     
        </a>
    </header>
    <transition name="fade">
        <b-nav v-if="isMenuOpen">
            <div id="primary-menu">
                <b-container fluid>
                    <b-row>
                        <b-col md="10" class="no-pad-col">
                            <b-container fluid>
                                <b-row>
                                    <b-col cols="12" sm="12" lg="4" class="no-pad-col">
                                        <b-container fluid class="menu-divider">
                                            <b-row>
                                                <b-col class="no-pad-col">
                                                    <div class="btn-wrapper">
                                                        <div class="item">
                                                            <b-button @click="selectMyMenu" :class="{isActive : activeCategoryButton=='my_menu'}">
                                                                <span>マイメニュ</span>
                                                                <span class="right-arrow"><fa :icon="['far','arrow-alt-circle-right']" id="edit"/></span>
                                                            </b-button>
                                                        </div>
                                                        <div class="item">
                                                            <b-button @click="redirect('mymenu')" tag="button" class="btn btn-secondary">
                                                                <span>マイメニュ編集</span>
                                                                <span class="right-arrow"><fa :icon="['far','arrow-alt-circle-right']" id="edit"/></span>
                                                            </b-button>                                
                                                        </div>
                                                        <div class="item">
                                                            <b-button>
                                                                <span>メニュMAP</span>
                                                                <span class="right-arrow"><fa :icon="['far','arrow-alt-circle-right']" id="edit"/></span>
                                                            </b-button>
                                                        </div>
                                                    </div>
                                                </b-col>
                                                <b-col class="no-pad-col">
                                                    <div class="btn-wrapper">
                                                        <div class="item">
                                                            <b-button><span></span></b-button>
                                                        </div>
                                                        <div class="item">
                                                            <b-button><span></span></b-button>
                                                        </div>
                                                        <div class="item">
                                                            <b-button><span></span></b-button>
                                                        </div>
                                                    </div>
                                                </b-col>
                                            </b-row>
                                        </b-container>
                                    </b-col>         
                                    <template  v-for="(categoryGroups, key) in groupCategoryList(categories)">                      
                                        <b-col cols="12" sm="12" lg="4" class="no-pad-col" :key="key">
                                            <b-container fluid class="menu-divider">
                                                <b-row>
                                                    <b-col class="no-pad-col" v-for="(categoryGroup, key) in categoryGroups" :key="key">
                                                        <div class="btn-wrapper">
                                                            <div class="item"  v-for="subGroup in categoryGroup" :key="subGroup.category_id">
                                                                <b-button  @click="selectCategory(subGroup)" :class="{isActive : activeCategoryButton==subGroup.category_name}" >
                                                                    <span>{{subGroup.category_name}}</span>
                                                                    <span class="right-arrow"><fa :icon="['far','arrow-alt-circle-right']" id="edit"/></span>
                                                                </b-button>
                                                            </div>                               
                                                        </div>
                                                    </b-col>
                                                </b-row>
                                            </b-container>
                                        </b-col>    
                                    </template>                         
                                </b-row>                       
                            </b-container>
                        </b-col>
                        <b-col col class="calendar">
                            <div class="calendar-container">
                                <div class="logo">                      
                                    <img src="~assets/images/head_logo.jpg" alt="Fujiko Sales System">
                                </div>
                            </div>
                        </b-col>
                    </b-row>     
                </b-container>
            </div>
            <div id="sub-menu-container">
                <div class="sub-menu">
                    <div class="header drop-shadow">
                        <b-row>
                            <b-col class="left no-pad-col">              
                                <b-dropdown id="ddown-left" text="Left align" variant="primary">
                                    <template slot="button-content">
                                        <span>佐藤 義則</span>
                                        <span><fa :icon="['far','user-circle']" id="edit"/></span>
                                    </template>
                                    <b-dropdown-item href="#">プロフィール</b-dropdown-item>
                                    <b-dropdown-item href="#" @click="logout">ログアウト</b-dropdown-item>               
                                </b-dropdown>              
                            </b-col>
                            <b-col class="center">
                                <template v-if="currentSelectedCategory">
                                    <span>{{currentSelectedCategory.category_name}}</span>
                                </template>           
                            </b-col>
                            <b-col class="right">
                                <span>編集番号</span>
                            </b-col>
                        </b-row>         
                    </div>
                    <b-container fluid class="content">
                        <b-row>
                            <b-col>                   
                                <b-container fluid>
                                    <b-row v-for="program in programs.slice(0,10)" :key="program.sequence"> 
                                        <b-col class="no-pad-col">
                                            <div class="item" v-bind:class="{passive:program.isPassive}">
                                                <a href="#" @click="redirect(program.link)" class="sub-menu-item" ><span>  {{[program.sequence]}}{{program.text}}</span></a>
                                            </div>                              
                                        </b-col>
                                    </b-row>
                                </b-container>
                            </b-col>
                            <b-col>     
                                <b-container fluid>
                                    <b-row v-for="program in programs.slice(10,20)" :key="program.sequence"> 
                                        <b-col class="no-pad-col">
                                            <div class="item" v-bind:class="{passive:program.isPassive}">
                                                <a href="#" @click="redirect(program.link)" class="sub-menu-item" ><span>  {{[program.sequence]}}{{program.text}}</span></a>
                                            </div>                                                             
                                        </b-col>
                                    </b-row>
                                </b-container>
                            </b-col>
                            <b-col>
                                <b-container fluid>
                                    <b-row  v-for="program in programs.slice(20,30)" :key="program.sequence"> 
                                        <b-col class="no-pad-col">
                                            <div class="item" v-bind:class="{passive:program.isPassive}">
                                                <a href="#" @click="redirect(program.link)" class="sub-menu-item" ><span>  {{[program.sequence]}}{{program.text}}</span></a>
                                            </div>                                       
                                        </b-col>
                                    </b-row>
                                </b-container>
                            </b-col>                
                        </b-row>
                    </b-container>
                </div>
            </div>
        </b-nav>   
    </transition>    
  </nav>
</template>
<script lang="ts">
  import Lang from "@/components/lang.vue";
  import { Keyboard, Listener } from "@/assets/ts/keyboard.ts";
  import { Component, Vue } from 'nuxt-property-decorator';
  import axios from 'axios';
  
  const modAxios = axios.create({
    baseURL: 'http://192.168.99.100:8080/fujiko'
  });

  @Component({
    components: { Lang }
  })
  export default class Header extends Vue {
     
     togButt:String='list-all'
     activeCategoryButton = null
     currentSelectedCategory = null
     isMenuOpen = false
     categories = []
     myMenus=[]
     //programs containers list
     programs:object[] = [
        {isPassive:true,sequence:1,text:"",link:"#"},
        {isPassive:true,sequence:2,text:"",link:"#"},
        {isPassive:true,sequence:3,text:"",link:"#"},
        {isPassive:true,sequence:4,text:"",link:"#"},
        {isPassive:true,sequence:5,text:"",link:"#"},
        {isPassive:true,sequence:6,text:"",link:"#"},
        {isPassive:true,sequence:7,text:"",link:"#"},
        {isPassive:true,sequence:8,text:"",link:"#"},
        {isPassive:true,sequence:9,text:"",link:"#"},
        {isPassive:true,sequence:10,text:"",link:"#"},
        {isPassive:true,sequence:11,text:"",link:"#"},
        {isPassive:true,sequence:12,text:"",link:"#"},
        {isPassive:true,sequence:13,text:"",link:"#"},
        {isPassive:true,sequence:14,text:"",link:"#"},
        {isPassive:true,sequence:15,text:"",link:"#"},
        {isPassive:true,sequence:16,text:"",link:"#"},
        {isPassive:true,sequence:17,text:"",link:"#"},
        {isPassive:true,sequence:18,text:"",link:"#"},
        {isPassive:true,sequence:19,text:"",link:"#"},
        {isPassive:true,sequence:20,text:"",link:"#"},
        {isPassive:true,sequence:21,text:"",link:"#"},
        {isPassive:true,sequence:22,text:"",link:"#"},
        {isPassive:true,sequence:23,text:"",link:"#"},
        {isPassive:true,sequence:24,text:"",link:"#"},
        {isPassive:true,sequence:25,text:"",link:"#"},
        {isPassive:true,sequence:26,text:"",link:"#"},
        {isPassive:true,sequence:27,text:"",link:"#"},
        {isPassive:true,sequence:28,text:"",link:"#"},
        {isPassive:true,sequence:29,text:"",link:"#"},   
        {isPassive:true,sequence:30,text:"",link:"#"}
    ]

   toggleMenu(){
    this.isMenuOpen = !this.isMenuOpen;      
   }

   logout() {
      let self = this
      this["$store"].dispatch('logout').then(e => self["$router"].go({ path: '/' }))
   }

     async fetchData(){            
      return Promise.all([ this['$axios'].$get('/api/program/category/grantedprograms/1'),
                           this['$axios'].$get('/api/mymenu/get/1/')
      ]).then(resp=>{
            this.categories = resp[0];
            this.myMenus=resp[1];
            // console.log("categories")
            // console.log(this.categories)
            return true;
      }).catch(resp=>{return false})    
    }
    mounted () {
     this.fetchData().then(resp=>{
         //console.log(resp)
         this.selectCategory(this.categories[0]);
     });
     this.isMenuOpen=false;
    }

   groupCategoryList(categoryList) {            
      let categoryGroups:object[] = [];
      var categoryListLength = categoryList.length;
      for(var count = 0;count<categoryListLength;count++) {
          let categoryGroup:object[] = [];
          var group = count;
          let subGroup:object[] = []
          while(group<(count+6) && group<categoryListLength) {    
              subGroup.push(categoryList[group]);
              if((group+1) % 3 == 0 || (group+1) == categoryListLength){
                categoryGroup.push(subGroup);
                subGroup = [];
              }
              group++;
          }
          count = group-1;

          categoryGroups.push(categoryGroup);          
      }
      return categoryGroups;
  }
  selectMyMenu() {
     this.activeCategoryButton = "my_menu";
     this.currentSelectedCategory = {category_name:"マイメニュ"};
     this.changeDisplayedProgramsMyMenu(this.myMenus);
  }
  selectCategory(category) {
    this.activeCategoryButton = category.category_name;
    this.currentSelectedCategory = category;
    this.changeDisplayedPrograms(category);
  }

  changeDisplayedProgramsMyMenu(category:object[]){    
    for(let program of this.programs){  
        var foundProgram = this.getProgramsBySequenceNumber(category,program["sequence"]);
        //console.log(foundProgram);
        if(foundProgram){
          program["isPassive"] = false;
          program["text"] = foundProgram["program"].program.text;
          program["link"] = foundProgram["program"].program.name;
          continue;
        }
        program["isPassive"] = true;
        program["text"] = "";   
        program["link"] = "#";          
    }
  }

  changeDisplayedPrograms(category:object[]){    
    //console.log(category)
    for(let program of this.programs){  
        var foundProgram = this.getProgramsBySequenceNumber(category["programs"],program["sequence"]);
        if(foundProgram){
          program["isPassive"] = false;
          program["text"] = foundProgram["text"];
          program["link"] = foundProgram["name"];
          continue;
        }
        program["isPassive"] = true;
        program["text"] = "";
        program["link"] = "#";
    }
  }
  getProgramsBySequenceNumber(programs: object[],sequenceNumber: number){    
     for(let program of programs){
        let menuSequence :number = program["menu_sequence"];
        if(menuSequence === sequenceNumber)          
          return program;        
      }
  }
  redirect(link:String){     
     var request = link=="#"?"#":"/"+link;
     if(request !== "#"){
      this.isMenuOpen=false;
      this.$root['$router'].push(request)      
     }     
  }
}
</script>
<style lang="scss" scoped>
  @import '@/assets/scss/global.scss';
  @import '@/assets/scss/mixins.scss';
  .container-fluid {
    padding:0;
    margin:0; 
  }
  nav {
    &.header-container {
      .btn-container {
        width:100%;
        height:100%;
      }
      .topnav {
        overflow: hidden;
        padding:0 1%;
        z-index: 1000;
        position: fixed;
        height: auto;
        width:100%;
        a {
          background-color: $gray;
          padding: 10px 16px; 
          float:left;
        }
      }

      .nav {
        background-color: $darkblue;
        display: block;
        position: fixed;
        width: 100%;
        height: 100%;
        //top: 0;
        padding-top: 47px;
        z-index:999;

        #primary-menu {
          padding:20px 10.5%;
          border-top:1px solid $white;

          .row {
            margin:0;
          }

          button {
            background-color: $dirtygray;
            color:$white;
            margin:1px;
            width: 100%;

            span:not(.right-arrow) {
              margin-right:15px;
            }
          }
        }
        #sub-menu-container {
          .header {
              min-height:30px;
              background-color: $white;
              .row {
                margin:0;
                .col{
                  margin: 0 5px;
                  padding: 1% 0;
                  button {    
                      &.dropdown-toggle {
                        &:hover {
                          background-color: unset !important;
                          border-color: $white !important;
                          color: $black !important;
                        }
                        background-color: unset;
                        border-color: $white;
                        color: $black;
                        padding: 0;
                        span:nth-child(2){
                          margin-left: 10px;
                          svg {
                            font-size: 37px;
                            vertical-align: middle;
                          }
                      }
                    }
                  }
                }
              }                 
          }
        }
      }      

      .menu-divider {
        .row {
          padding:0;
          margin: 0;
        }
      }  

      
    }
  }

  article {
            &.main-content {
        @include give-padding(3% 10.5%);
        //div {
          &.container-fluid{            
            @media only screen and (max-width: 300px) {
                width: 280px;
            }
          }
          div{
            &.content{
                &-title{
                  @include no-margin();
                }
                &-body{
                  background-color: $white;
                  border-radius: 5px;
                  @include give-padding(1%);
                  font-size: $normal;
                  min-height: 75vh;
                  max-height: 100%;
                  @include no-margin(); 

                }
                &-footer{
                  background-color: $white;
                  border-radius: 5px;
                  @include give-margin(2% 0 0 0);
                }
              }
          }
          &.row-pad{
              &-15{
                @include give-padding(0 15px 10px 15px)
              }
          }         
        //}                             
            }
    }

  
  .isActive {
    background-color: #0055af !important;
  }  

  header.topnav {
    background-color: $darkblue;
  }
  

  #primary-menu {
    button {
      background-color: $dirtygray;
      color:$white;
      margin:1px;
      width: 100%;

      span:not(.right-arrow) {
        margin-right:15px;
      }
    }
  } 
  .calendar {
    padding:0 0 0 10px;
  }
  .no-pad-col {
    padding:0;
  } 
  .item {
    padding: 0 5px;
    button {
      text-align: left;
      width:100%;
      background-color: $darkgray;      
    }
  }
  .passive {
    background-color: #6c7289 !important;
  }
  .drop-shadow {    
    box-shadow: 7px 2px 1px 0px #00000033;
  }
  .calendar-container {
    background: $white;
    height:100%;
    width: 100%;
  }
  .sub-menu {    
    padding:0 10.5%;
    .content {
      .row{
        margin: 0;  
      }
      .col{
        padding: 0;  
      }
    }
  }
  .left {
    text-align:left;
  }
  .right {
    text-align:right;
  }
  .center {
    text-align:center;
  }
  a {
    &.sub-menu-item {
      color:$white;
      text-decoration: none;
      display: block;
      height:40px;
      //margin-top: 5px;
      padding: 10px 0;
      &:hover {
        text-decoration: none;
      }
      span {
        //margin-left:15px;
      }
    }    
  } 
  .content {
    margin-top:50px;
    .item {
      &:hover {
        background-color: $dirtygray;  
      }
      padding: 0 5px;
      margin:0.5%;      
      //border: 1px solid $opaquewhite;
      background: $darkgray;    
      position:relative;
      border-radius: 5px 5px;    
    }
    .item-container {
      position: absolute;
      bottom: 0;
      left: 0;
    }
  }
  .logo {
    margin: 0 auto;
    text-align: center;
  } 
  .right-arrow {
    float:right;
  }  
</style>