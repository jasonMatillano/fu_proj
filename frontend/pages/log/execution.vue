<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">実行ログ照会</h5>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="9" md="12">
				<section class="field-input">
					<b-row class="mb-2">
						<b-col sm="12" md="4" xl="3">
							<b-form-input v-model="filter" placeholder="フィルタ" size="sm"/>
						</b-col>
						<b-col sm="12" md="8" xl="9">
							<b-form inline class="float-right">
								<label class="mr-sm-2" for="from">日付: </label>
								<b-input class="mb-2 mr-sm-2 mb-sm-0" id="from" placeholder="yyyy/mm/dd"  size="sm"/>
								<div class="mr-sm-2"> ～ </div>
								<b-input class="mb-2 mr-sm-2 mb-sm-0" placeholder="yyyy/mm/dd"  size="sm"/>
							</b-form>
						</b-col>
					</b-row>
				</section>
				<section class="field-table">
					<b-table striped
					         bordered
					         small
					         hover
					         :filter="filter"
					         :items="items"
					         :fields="fields"
					         @row-clicked="clickTable">
					</b-table>	
				</section>
			</b-col>
		</b-row>
		<c_footer class="content-footer" @data="buttonProcess"></c_footer>
	</b-container>
</template>
<script lang="ts">
	import { Component, Vue } from 'nuxt-property-decorator'

	import c_footer from '@/components/footer.vue'

	@Component({
		components:{
			c_footer,
		},
	})
	export default class Execution extends Vue {
		filter = null
		executionData = []
		fields= [ 
		  	{key:'time',label:'実行日時',class: 'text-center table-col-width-15',sortable: true},
		    {key:'p_id',label:'プログラムID',class: 'text-center table-col-width-10',sortable: true},
		    {key:'p_name',label:'プログラム名',sortable: true},
		    {key:'command',label:'コマンド',sortable: true},
		    {key:'u_id',label:'ユーザID ',class: 'text-center table-col-width-8',sortable: true},
		    {key:'u_name',label:'ユーザ名',sortable: true},
		    {key:'ip_address',label: 'IP',class:'table-col-width-10 text-center',sortable: true}
		]

		items= [
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F1 開く',u_id: '0000',u_name: '日本太郎',ip_address: '192.168.0.0'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F2 終了',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F1 開く',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F2 終了',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F1 開く',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F2 終了',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F1 開く',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F2 終了',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F1 開く',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F2 終了',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F1 開く',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F2 終了',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		{time: "2018/07/27 12:12:12",p_id: 'MST1001',p_name: "XXXXXXXXXXXXXXXX",command: 'F3 削除',u_id: '0000',u_name: '日本太郎'},
		]

		buttonProcess(value){
			alert("");
		}

		clickTable(item){
			this.reset()
			this.executionData = item
			Vue.set(item, "_rowVariant", "info");
		}

		reset(){
			if(this.executionData !== null){
				Vue.set(this.executionData,"_rowVariant","")
			}
		}
	}

</script>

<style lang="scss">
	@import '@/assets/scss/common.scss';

</style>