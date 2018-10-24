<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="8" cols="12">
				<section class="field-input mb-2">
					<b-container>
						<b-row>
							<b-col sm="12" md="5" xl="3" class="p-0">
								<b-form-input v-model="filter" :placeholder="filter_lang" size="sm"/>
							</b-col>
							<b-col>
								<b-form-checkbox 													
										value="accepted"
										unchecked-value="not_accepted">
									{{is_end_lang}}
								</b-form-checkbox>
							</b-col>							
						</b-row>
					</b-container>
				</section>
				<section class="field-input mb-2">
					<b-container>
						<b-row>														
							<b-col class="p-0">
								<b-form inline>
									<label for="heirarchy" class="mr-sm-2">{{hierarchy_lang}}</label>
									<b-input class="input-width-30" v-model="nodeHeirarchyLevel" id="heirarchy" size="sm" readonly/>
									<label for="superior" class="mr-sm-2" >{{superior_lang}}</label>
									<b-input id="superior" size="sm" v-model="nodeHeirarchyOrder" readonly/>
								</b-form>
							</b-col>
						</b-row>
					</b-container>
				</section>
				<section class="field-table">
					<b-table 
					         bordered
				         	 small
					         :items="tableItems"
					         :fields="fields"
					         hover
					         :filter="filter"
					         tbody-class="table-adjusted"
					         @row-dblclicked="itemPreview"
							 @row-clicked="itemSelected">
							 
							 <template slot="short_name" slot-scope="data">
					         	{{data.value}}
					         	<br />
					         	<!-- <b-form-checkbox v-model="data.item.sales_flag" :id="data.value + data.index">
							      営業
							    </b-form-checkbox> -->
					         </template>
					         <template slot="info" slot-scope="data">								 
					         	{{data.item.nodeData.address1}}
					         	<br />
					         	{{data.item.nodeData.address2}}
					         	<br />
					         	{{data.item.nodeData.telephone_number}}
					         	<br />
					         	{{data.item.nodeData.fax_number}}
					         </template>
					         <template slot="warehouse" slot-scope="data" v-if="isBranchMode">
					         	{{data.item.nodeData.warehouse}}
					         	<!-- <br />
					         	{{data.value.name}} -->
					         </template>
					         <template slot="receipt" slot-scope="data">
					         	{{data.item.nodeData.receipt_sequence_number}}
					         	<br />
					         	{{data.item.nodeData.receipt_symbol}}
					         </template> 
					</b-table>
				</section>
			</b-col>
			<b-col xl="4" sm="6" cols="12">
				<section class="tree position-absolute">
					<section class="field-input mb-2">
						<b-col sm="12" class="p-0">
							<!-- <b-form inline>
								<label for="heirarchy" class="mr-sm-2">{{hierarchy_lang}}</label>
					    		<b-input class="input-width-30" id="heirarchy" size="sm" readonly/>
					    		<label for="superior" class="mr-sm-2">{{superior_lang}}</label>
								 <b-input id="superior" size="sm" readonly/>
							</b-form> -->
						</b-col>
					</section>
					<section class="field-tree"  v-if="!branchFormShow && !departmentFormShow">
						<b-col　sm="9" class="col-border p-0">
							<div class="tree-list-border">	
								<template v-for="branchNode in branchTree.nodes">										
									<browser :node="branchNode" :selectedNode="currentSelectedNode" v-on:nodeSelected="selectedNodeChanged" :key="branchNode.nodeData.id"/>
								</template>
							</div>	
						</b-col>	
					</section>
				</section>
				<section class="field-form position-absolute">
					<transition name="fade">
						<b-card bg-variant="light" v-if="branchFormShow">
							<b-col class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>拠点 ＞ {{processName}}</span></h5>
							</b-col>
							<c_branchForm :formdata="prevData" :processName="processName" @clickCancel="clickCancel" @submit="submit"></c_branchForm>
						</b-card>
					</transition>
					<transition name="fade">
						<b-card bg-variant="light" v-if="departmentFormShow">
							<b-col class="p-0">
								<h5 for="edit"><span><fa :icon="['far','edit']" id="edit"/>部門 ＞ {{processName}}</span></h5>
							</b-col>
							<c_departmentForm :formdata="prevData" :processName="processName" @clickCancel="clickCancel" @submit="submit"></c_departmentForm>
						</b-card>
					</transition>
				</section>
			</b-col>
		</b-row>
		<!-- <c_modal @submit="confirmSubmit" idMod="confirm"></c_modal> -->
		<c_footer class="content-footer" @eventTriggered="eventHandler" @data="clickButton" @clickCancel="clickCancel" :fnData="fnData" :events="UIEvents"></c_footer>
	</b-container>
</template>
<script lang="ts">
import { Component, Vue } from "nuxt-property-decorator";

import c_footer from "@/components/footer.vue";
import browser from "@/components/department/browser.vue";
import c_departmentForm from "@/components/forms/department";
import c_branchForm from "@/components/forms/branch";
import c_modal from "@/components/modal";
import { TreeNode } from "@/assets/ts/TreeNode.ts";
import { Tree } from "@/assets/ts/Tree.ts";
import {UIEvent} from "@/assets/ts/UIEvent.ts"
import {Keys} from "@/assets/ts/keys.ts"

@Component({
  components: {
    c_footer,
    browser,
    c_departmentForm,
    c_branchForm,
    c_modal
  }
})
export default class Department extends Vue {
  //tree data
  //to be change with API
  branchTree: Tree = new Tree();
  currentSelectedNode: TreeNode = new TreeNode();
  currentSelectedNodeParent: TreeNode = new TreeNode();
  prevData: TreeNode = new TreeNode();
  branchFormShow: boolean = false;
  departmentFormShow: boolean = false;
  isBranchMode:boolean = true;
  isUpdateMode:boolean = false;
  submittedData = null;
  filter:string = "";
  processName:string = "";
  branchData: any;
  readonly HIGHEST_HEIRARCHY_ORDER:string="(最上位)";
  nodeHeirarchyLevel:number=1;
  nodeHeirarchyOrder:string=this.HIGHEST_HEIRARCHY_ORDER
  go1LevelUpButton:UIEvent=new UIEvent(Keys.F9,'上部門',true);
  go1LevelDownButton:UIEvent= new UIEvent(Keys.F10,'下部門',true);
  UIEvents:Array<UIEvent>=[ 
      new UIEvent(Keys.F2,'取消',true),
      new UIEvent(Keys.F4,'新規登録',true),
      new UIEvent(Keys.F6,'日付',true),
      new UIEvent(Keys.F8,'リスト',true),
      new UIEvent(Keys.F12,'メニュ',true),
      new UIEvent(Keys.F3,'削除',true),
      this.go1LevelUpButton,
      this.go1LevelDownButton
     
  ]     
  //to be change with API

  title_lang = "";
  filter_lang = "";
  hierarchy_lang = "";
  superior_lang = "";

  name_lang = "";
  kana_lang = "";
  short_name_lang = "";
  order_lang = "";
  postal_lang = "";
  info_lang = "";
  warehouse_lang = "";
  receipt_lang = "";
  is_end_lang = "終了"

  fnData: any = [
    { key: 113, name: "取消", action: "delete" },
    { key: 115, name: "新規登録", action: "register" },
    { key: 117, name: "日付", action: "showDate" },
    { key: 119, name: "リスト", action: "showList" },
	  { key: 123, name: "メニュ", action: "menu" },
	  { key: 120, name: "上部門", action: "menu" },
	  { key: 121, name: "下部門", action: "menu" },
	  { key: 114, name: "削除", action: "menu" }	
  ];

  fields: any = [
    {
      key: "nodeData.code",
      label: "CD",
      class: "text-center table-col-width-6"
    },
    { key: "nodeData.name", label: "部門名", class: "table-col-width-16" },
    {
      key: "nodeData.name_kana",
      label: "部門カナ",
      class: "table-col-width-12"
    },
    { key: "short_name", label: "部門略称", class: "table-col-width-10" },
    { key: "nodeData.sequence", label: "順序", class: "table-col-width-7" },
    {
      key: "nodeData.postal_code",
      label: "〒",
      class: "text-center table-col-width-10"
    },
    { key: "info", label: "住所１／住所２／電話番号／FAX番号" },
    { key: "warehouse", label: "倉庫", class: "table-col-width-8" },
    { key: "receipt", label: "領収連番領収記号", class: "table-col-width-75" }
  ];

  tableItems: any = [];

  mounted() {
    this.fetchData().then(response => {
      this.selectedNodeChanged(this.branchTree.nodes[0]);
    });   
  }

  async fetchData() {
    return this["$axios"]
      .$get("/api/branch/")
      .then(branches => {
        this.branchData = branches;
        this.branchTree = this.getBranchDataTree(this.branchData);
        return true;
      })
      .catch(ex => {
        console.log("exception: " + ex);
        return [];
      });
  }

  itemSelected(item: TreeNode): void {
    this.setRowInactive();
    this.currentSelectedNode = item;
    this.setRowActive();
  }

  itemPreview(item: TreeNode): void {
    this.setUpdateMode(true);
    this.prevData = item;
    this.set_color(item);
    this.showForm();
  }

  showForm(): void {
    this.branchFormShow = this.isBranchMode;
    this.departmentFormShow = !this.isBranchMode;
  }

  hideForm(): void {
    this.branchFormShow = false;
    this.departmentFormShow = false;
  }

  selectedNodeChanged(selectedNode: TreeNode): void {    
    if (selectedNode.isRootNode()){
       this.isBranchMode = true;
       this.go1LevelUpButton.isActive=false;
       this.go1LevelDownButton.isActive=true;
    }    
    else{ this.isBranchMode = false;
      this.go1LevelUpButton.isActive=true;
      this.go1LevelDownButton.isActive=true;
    }
    this.setRowInactive();
    this.currentSelectedNode = selectedNode;
    this.currentSelectedNodeParent = selectedNode.parentNode;
    this.tableItems = this.getDisplayTableItems(this.currentSelectedNode);
    this.setRowActive();
    this.nodeHeirarchyLevel=this.getNodeHeirarchyLevel(this.currentSelectedNode)
    this.nodeHeirarchyOrder=this.currentSelectedNode.isRootNode()
                            ? this.HIGHEST_HEIRARCHY_ORDER 
                            : this.getNodeHeirarchyOrder(this.currentSelectedNode.parentNode);
  }
  
  getDisplayTableItems(selectedNode: TreeNode): any {
    if (this.isBranchMode) return this.branchTree.nodes;   
    return selectedNode.parentNode.childNodes;
  }

  go1LevelDownInHeirarchy(selectedNode: TreeNode){
    this.setRowInactive(); 
    this.isBranchMode=false;    
    this.tableItems = selectedNode.childNodes;
    this.currentSelectedNodeParent = this.currentSelectedNode;
    this.nodeHeirarchyLevel=this.getNodeHeirarchyLevel(this.currentSelectedNode) + 1;
    this.nodeHeirarchyOrder=this.getNodeHeirarchyOrder(this.currentSelectedNode);
  }

  setUpdateMode(mode:boolean){
    this.isUpdateMode = mode;
    this.processName = mode ? "変更" : "新規登録";    
  }

  setRowActive(): void {
    Vue.set(this.currentSelectedNode, "_rowVariant", "info");
  }

  setRowInactive(): void {
    Vue.set(this.currentSelectedNode, "_rowVariant", "");
  }

  getBranchDataTree(branchData: any): Tree {
    let branchDataTree = new Tree();
    branchData.forEach(branch => {
      let branchNode:TreeNode = this.getDepartmentDataNode(
          branch,
          null
	    );
	    if (branchNode) branchDataTree.addNode(branchNode);    
    });
    return branchDataTree;
  }

  getDepartmentDataNode(departmentData: any,parentDepartmentNode: TreeNode): TreeNode {
    
    if (!(departmentData instanceof Object)) return null;

    let departmentNode = new TreeNode(),    
        childrenNodes = new Array<TreeNode>(),
        subGroup:Array<any> = departmentData.hasOwnProperty('departments') 
                ? departmentData.departments : departmentData.sub_departments;
    
    departmentNode.parentNode = parentDepartmentNode;
    departmentNode.nodeData = departmentData;

    subGroup.forEach(subDepartment => {
      let subDepartmentNode: TreeNode = this.getDepartmentDataNode(
        subDepartment,
        departmentNode
      );
      if (subDepartmentNode) childrenNodes.push(subDepartmentNode);
    });
    departmentNode.childNodes = childrenNodes;
    return departmentNode;
  }

  getNodeHeirarchyLevel(node:TreeNode){
	  let level:number=1;
	  if(node.isRootNode())return level;
	  return (level+this.getNodeHeirarchyLevel(node.parentNode));
  }  

  getNodeHeirarchyOrder(node:TreeNode){
	  if(node.isRootNode())return node.nodeData.name;
	  return (this.getNodeHeirarchyOrder(node.parentNode)+" / "+node.nodeData.name);
  }
  
  eventHandler(event) {    
    switch(event.key){
      case Keys.F2:
        this.hideForm();
      break;
      case Keys.F9:       
       if(!this.currentSelectedNode.isRootNode())
         this.selectedNodeChanged(this.currentSelectedNode.parentNode);      
       else
         this.selectedNodeChanged(this.currentSelectedNode);     
       break;
      case Keys.F10:      
       if(this.currentSelectedNode.isLeafNode()) {
          this.go1LevelDownInHeirarchy(this.currentSelectedNode)
          this.go1LevelDownButton.isActive=false;
          this.go1LevelUpButton.isActive=true;
          return;
       }
       this.selectedNodeChanged(this.currentSelectedNode.childNodes[0]);
       break;
      case Keys.F4:
        this.create();
      break;
    }
  }

  create(){
    this.prevData=new TreeNode();
    this.setUpdateMode(false);
    this.showForm();
  }

  created(): void {
    this.title_lang = this.$t("Auth.pages.department.title").toString();
    this.filter_lang = this.$t("Auth.pages.department.filter").toString();
    this.hierarchy_lang = this.$t("Auth.pages.department.hierarchy").toString();
    this.superior_lang = this.$t("Auth.pages.department.superior").toString();

    this.name_lang = this.$t("Auth.pages.department.name").toString();
    this.$set(this.fields[1], "label", this.name_lang);
    this.kana_lang = this.$t("Auth.pages.department.kana").toString();
    this.$set(this.fields[2], "label", this.kana_lang);
    this.short_name_lang = this.$t(
      "Auth.pages.department.short_name"
    ).toString();
    this.$set(this.fields[3], "label", this.short_name_lang);
    this.order_lang = this.$t("Auth.pages.department.order").toString();
    this.$set(this.fields[4], "label", this.order_lang);
    this.postal_lang = this.$t("Auth.pages.department.postal").toString();
    this.$set(this.fields[5], "label", this.postal_lang);
    this.info_lang = this.$t("Auth.pages.department.info").toString();
    this.$set(this.fields[6], "label", this.info_lang);
    this.warehouse_lang = this.$t("Auth.pages.department.warehouse").toString();
    this.$set(this.fields[7], "label", this.warehouse_lang);
    this.receipt_lang = this.$t("Auth.pages.department.receipt").toString();
    this.$set(this.fields[8], "label", this.receipt_lang);
  }
  
  clickButton(item) {
    if (item.index == "1") {
      //to do
    } else if (item.index == "3") {
      //this.formShow = true
      this.processName = item.name;
      this.reset_color();
      this.prevData = new TreeNode();
    } else if (item.index == "2") {
      this.clickCancel();
    }
  }

  //all called functions
  reset_color() {
    // if(this.prevData !== null){
    // 	Vue.set(this.prevData,"_rowVariant","")
    // }
  }

  set_color(item) {
    if (this.processName === "変更" || this.processName === "") {
      this.prevData = item;
      Vue.set(item, "_rowVariant", "info");
    }
  }

  clickCancel() {
    this.reset_color();
    //this.formShow = false
    this.processName = "";
    this.prevData = new TreeNode();
    this.hideForm();
  }

  //to be deleted
  submit(value) {   
    if(this.isBranchMode)
     this.saveOrUpdateBranch(value);
    else
     this.saveOrUpdateDepartment(value);
  }

  confirmSubmit() {
    //this.formShow = false
    if (this.processName == "変更") {
      this.$set(this.prevData, "code", this.submittedData.code);
      this.$set(this.prevData, "name", this.submittedData.name);
      this.$set(this.prevData, "kana", this.submittedData.kana);
      this.$set(this.prevData, "short_name", this.submittedData.short_name);
      this.$set(this.prevData, "order", this.submittedData.order);
      this.$set(this.prevData, "postal", this.submittedData.postal);
      this.$set(this.prevData, "add_1", this.submittedData.add_1);
      this.$set(this.prevData, "add_2", this.submittedData.add_2);
      this.$set(this.prevData, "phone_number", this.submittedData.phone_number);
      this.$set(this.prevData, "fax_number", this.submittedData.fax_number);
    } else {
      //this.items.push(this.submittedData) //comment out 2018/10/13 7:11 PM
    }
    this.processName = "";
  }

  saveOrUpdateDepartment(department: any) {  
    if(!department.hasOwnProperty("id")){      
      department.branch=this.currentSelectedNodeParent.isRootNode()
                        ?this.currentSelectedNodeParent.nodeData
                        :{id:this.currentSelectedNode.nodeData.branch};
      department.parent_department = this.currentSelectedNodeParent.isRootNode()
                        ?null
                        :{id:this.currentSelectedNodeParent.nodeData.id};
      department.created_by={
        id:1
      };
      department.created_at={
          id:"JMST09"
      };
      department.updated_by = {
          id:1
      };
      department.updated_at={
          id:"JMST09"
      }
    }
    else{
      department.branch={id:department.branch};
      department.created_by={
        id:1
      };
      department.updated_by = {
          id:1
      };
      department.parent_department={id:department.parent_department};
      department.updated_at={
          id:"JMST07"
      }
    }    
    this['$axios'].$patch('/api/department/',department)
    							.then(departmentData=> {
                    this.fetchData();
                    this.hideForm();
                    if(this.isUpdateMode)
                      this.currentSelectedNode.nodeData=department
    							}).catch(ex=>console.log("exception: "+ex))
  }

  saveOrUpdateBranch(branch: any) { 
    branch.created_by={
          id:1
    };
    branch.created_at={
        id:"JMST09"
    };
    branch.updated_by = {
        id:1
    };
    branch.updated_at={
        id:"JMST07"
    }         
    this['$axios'].$patch('/api/branch/',branch)
                  .then(branchData=> {
                    this.fetchData();
                    this.hideForm();
                    if(this.isUpdateMode)
                      this.currentSelectedNode.nodeData=branch
                  }).catch(ex=>console.log("exception: "+ex))
  }

}
</script>
<style lang="scss">
@import "@/assets/scss/common.scss";
</style>
<style lang="scss" scoped>
@import "@/assets/scss/mixins.scss";
section {
  &.tree {
    input {
      &.input-width {
        text-align: center;
        &-30 {
          @include give-width(30px !important);
        }
      }
    }
    .field-tree {
      .col-border {
        height: 620px;
        .tree-list-border {
          overflow-y: auto;
          @include give-padding(0 1px 0 0);
          height: 100%;
          border: 1px ridge black;
        }
      }
    }
  }
  &.position-absolute {
    top: 0;
    left: 0;
    right: 0;
  }
}
div {
  &.custom-control {
    label {
      font-size: $big;
    }
  }
}
</style>