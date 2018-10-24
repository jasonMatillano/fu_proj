<template>
	<b-container fluid id="wg-master" class="px-0">
		<b-row class="content-title m-0 py-2">
			<b-col>
				<h5 class="m-0">{{title_lang}}</h5>
				<hr>
			</b-col>
		</b-row>
		<b-row class="content-body m-0">
			<b-col xl="5">
				<section class="field-input mb-2">
					<b-col>
						<b-form inline>
							<label class="mr-sm-2" for="user">{{user_id_lang}}: </label>
							<b-input class="mb-2 mr-sm-2 mb-sm-0 input-width-65" id="user" size="sm"/>
							<label class="mr-sm-2" for="">宇多田ヒカル</label>
						</b-form>	
					</b-col>
				</section>
				<section class="field-table">
					<b-table striped
				         	 small
					         :items="items"
					         :fields="fields"
					         class="table-bordered-all">
						<template slot="program" slot-scope="row">
							<tr v-for="s in row.value">
								<td class="border-right-0 border-left-0 border-bottom-0">[{{ s.id }}] {{s.program_name}}</td>
							</tr>
						</template>
						<template v-for="item in items">
							{{item}}
						</template>
						<template slot="useFlg" slot-scope="row">
							<tr v-for="s in row.item.program" >
								<td class="border-right-0 border-left-0 border-bottom-0 p-0">
									<b-form-checkbox
									   :checked='s.check' class="table-col-margin-20">
									</b-form-checkbox>
								</td>
							</tr>
						</template>
						<template slot="referenceFlg" slot-scope="row">
							<tr v-for="s in row.item.program">
								<td class="border-right-0 border-left-0 border-bottom-0 p-0">
									<b-form-checkbox
									   :checked='s.check2' class="table-col-margin-20">
									</b-form-checkbox>
								</td>			
							</tr>
						</template>
					</b-table>	
				</section>
			</b-col>
		</b-row>
		<c_footer class="content-footer"></c_footer>
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
	export default class Program extends Vue {

		// change lange
		title_lang = ''
		user_id_lang = ''

		category_name_lang = ''
		program_lang = ''
		useFlg_lang = ''
		referenceFlg_lang = ''

		created() {
			this.title_lang = this.$t("Auth.pages.role.program.title").toString()
			this.user_id_lang = this.$t("Auth.pages.role.program.user_id").toString()

			this.category_name_lang = this.$t("Auth.pages.role.program.category_name").toString()
			this.program_lang = this.$t("Auth.pages.role.program.program").toString()
			this.useFlg_lang = this.$t("Auth.pages.role.program.useFlg").toString()
			this.referenceFlg_lang = this.$t("Auth.pages.role.program.referenceFlg").toString()

			this.$set(this.fields[0], "label", this.category_name_lang)
			this.$set(this.fields[1], "label", this.program_lang)
			this.$set(this.fields[2], "label", this.useFlg_lang)
			this.$set(this.fields[3], "label", this.referenceFlg_lang)
		}

		fields = [ 
		  	{key:'category_name',label:'プログラムカテゴリ名',class: 'text-center p-0 table-col-width-140'},
		    {key:'program',label:'プログラムID / プログラム名',class: 'p-0'},
		    {key:'useFlg',label:'使用',class: "text-center p-0 table-col-width-60"},
		    {key:'referenceFlg',label:'参照のみ',class: "text-center table-col-width-60 p-0"}
		]
		items = [
			{ 
				id: 'MST0001',
				category_name: '見積処理',
				program: [
					{
						id: 'XXXXXX',
						program_name: '見積伝票入力',
						check: true,
						check2: true,
						action:[
							{
								id: 'XXXXXX',
								name: 'F1　登録'
							},
						],

					},
					{
						id: 'XXXXXX',
						program_name: '見積明細一覧',
						check: false,
						check2: true,
					},
					{
						id: 'XXXXXX',
						program_name: '見積伝票一覧',
						check: true,
						check2: true,

					},
					{
						id: 'XXXXXX',
						program_name: '売上仕入一覧',
						check: false,
						check2: true,
					},
					{
						id: 'XXXXXX',
						program_name: '見積別売上仕入対比表',
						check: true,
						check2: true,
					},
				],		
			},
			{ 
				id: 'MST0002',
				category_name: '在庫管理',
				program: [
					{
						id: 'XXXXXX',
						program_name: '移動伝票入力',
						check: true,
						check2: false,

					},
					{
						id: 'XXXXXX',
						program_name: '在庫移動明細一覧',
						check: true,
						check2: false,
					},
					{
						id: 'XXXXXX',
						program_name: '棚卸差異表',
						check: true,
						check2: false,

					},
					{
						id: 'XXXXXX',
						program_name: '棚卸一覧表',
						check: false,
						check2: false,
					},
					{
						id: 'XXXXXX',
						program_name: '棚卸確定更新',
						check: true,
						check2: false,

					},
					{
						id: 'XXXXXX',
						program_name: '在庫一覧',
						check: false,
						check2: false,
					},
					{
						id: 'XXXXXX',
						program_name: '棚卸確定更新',
						check: false,
						check2: false,
					},
					{
						id: 'XXXXXX',
						program_name: '在庫一覧',
						check: false,
						check2: false,
					},
					{
						id: 'XXXXXX',
						program_name: '在庫金額一覧',
						check: false,
						check2: false,
					},

				],		
			},
			{ 
				id: 'MST0001',
				category_name: '見積処理',
				program: [
					{
						id: 'XXXXXX',
						program_name: '見積伝票入力',
						check: true,
						check2: true,

					},
					{
						id: 'XXXXXX',
						program_name: '見積明細一覧',
						check: false,
						check2: true,
					},
					{
						id: 'XXXXXX',
						program_name: '見積伝票一覧',
						check: true,
						check2: true,

					},
					{
						id: 'XXXXXX',
						program_name: '売上仕入一覧',
						check: false,
						check2: true,
					},
					{
						id: 'XXXXXX',
						program_name: '見積別売上仕入対比表',
						check: true,
						check2: true,
					},
				],		
			},
			{ 
				id: 'MST0001',
				category_name: '見積処理',
				program: [
					{
						id: 'XXXXXX',
						program_name: '見積伝票入力',
						check: true,
						check2: true,

					},
					{
						id: 'XXXXXX',
						program_name: '見積明細一覧',
						check: false,
						check2: true,
					},
					{
						id: 'XXXXXX',
						program_name: '見積伝票一覧',
						check: true,
						check2: true,

					},
					{
						id: 'XXXXXX',
						program_name: '売上仕入一覧',
						check: false,
						check2: true,
					},
					{
						id: 'XXXXXX',
						program_name: '見積別売上仕入対比表',
						check: true,
						check2: true,
					},
				],		
			},
		]
	}
</script>
<style lang="scss">
	@import '@/assets/scss/common.scss';

	table{
		thead tr th, tbody tr td{
			&.table-col-width, .table-col{
				@for $i from 1 through 20 {
				  	&-#{$i}{
						@include give-width(percentage($i/100));
					}
				}
				&-60{
					@include give-width(60px);
				}
				&-margin{
					&-20{
						@include give-margin(0 0 0 10px);
					}
				}
			}
		}
		tbody{
			tr{
			    @include give-margin(0 0 -1px 0);
			    td{
			    	tr:first-child{
						td{
							border-top: 0;
						}
			    	}
			    }
			}
		}
		&.table{
			&-bordered{
				&-all{
					th, td{
						font-weight: normal;
						border: 1px solid #526a7f;
					}
				}
			}
			&-sm{
				th{
					padding: 0.3rem !important;
				}
			}
		}
	}
	div{
		&.custom-control{
			min-height: 1.299rem;
		}
		form{
			input{
				&.form{
					&-control{
						border: 1px solid black;
						&-sm{
							font-size: 0.75rem;
							font-weight: 600;
						}
					}
				}
				&.input-width{
					&-65{
						@include give-width(65px !important);
					}
				}
			}	
		}
	}
</style>