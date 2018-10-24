<template>
	<div class="content">
		<b-row>
			<b-col sm="12" class="display">
				<div class="logo">
					<img src="~assets/images/head_logo.jpg" alt="Fujiko Sales System">
				</div>
				<div class="auth">
					<div class="header">
						{{ $t("Auth.system.name") }} 
					</div>
					<div v-if="error" class="login_error">
						{{ $t("Auth.error") }}
					</div>
					<form @submit.prevent="login">
						<input type="text" v-model="username" :placeholder="$t('Auth.username')" />
						<input type="password" v-model="password" :placeholder="$t('Auth.password')" />
						<input type="submit" :value="$t('Auth.submit')" />
					</form>
					<div class="extra">
						{{ $t("Auth.system.info") }} 
					</div>
					<div class="userinfo">
						<hr>
						<b-row>
							<b-col cols="6"> {{ $t("Auth.user_pref.locale") }} </b-col>
							<b-col cols="6"> : <Lang /> </b-col>
						</b-row>
						<hr>
						<b-row>
							<b-col cols="6"> {{ $t("Auth.user_pref.ip") }} </b-col>
							<b-col cols="6"> : {{ userinfo.ip }} </b-col>
						</b-row>
						<hr>
						<b-row>
							<b-col cols="6"> {{ $t("Auth.user_pref.browser") }} </b-col>
							<b-col cols="6"> : {{ userinfo.browser }} </b-col>
						</b-row>
						<hr>
						<b-row>
							<b-col cols="6"> {{ $t("Auth.user_pref.os") }} </b-col>
							<b-col cols="6"> : {{ userinfo.os }} </b-col>
						</b-row>
						<hr>
						<b-row>
							<b-col cols="12"> {{ $t("Auth.user_pref.location") }} : </b-col>
							<b-col cols="12">  <img v-if="" :src="userinfo.map" :alt="$t('Auth.user_pref.location')" />  </b-col>
						</b-row>
					</div>
				</div>
				<no-ssr>
					<vue-particles
				        color="#fff"
				        :particleOpacity="1"
				        :particlesNumber="80"
				        shapeType="circle"
				        :particleSize="5"
				        linesColor="#fff"
				        :linesWidth="1"
				        :lineLinked="true"
				        :lineOpacity="100"
				        :linesDistance="150"
				        :moveSpeed="5"
				        :hoverEffect="true"
				        :hoverMode="hov"
				        :clickEffect="true"
				        :clickMode="c_mode" />
			    </no-ssr>
      		</b-col>
		</b-row>
	</div>
</template>

<script lang="ts">
	import Lang from "@/components/lang.vue"
	import { Utils } from "@/assets/ts/Utils.ts"
	import { Component, Vue } from 'nuxt-property-decorator'

	@Component({
		components: { Lang }
	})
	export default class extends Vue {
		mode = ["bubble", "grab", "repulse"]
		hov = "repulse"
		c_mode = "push"
		username = ""
		password = ""
		userinfo = {
			os: "",
			map: "",
			browser: "",
			ip: ""
		}
		error = false

		layout () { return 'auth' } 

		login() {
			let self = this
			this['$store'].dispatch('login', {
				"username": this.username,
				"password": this.password	
			}).then(e => {
				console.log(e)
				if(e.ok && e.response) {
					this["$router"].push({ name: 'menu'})
				} else {
					this.error = true
				}
			})
		}

		mounted () {
			let util = Utils.getInstance, self = this

			util.getUserPref()

			 (e => {
				console.log(e)
				self.userinfo.os = e.os
				self.userinfo.browser = e.browser + " ," + e.bit + " bit"
				self.userinfo.map = e.position.map
			})

			util.getIp(e => self.userinfo.ip = e)

			this.hov = this.mode[Math.floor((Math.random() * 3) + 1)-1];
			this.mode.push("push")
			this.mode = this.mode.filter(elem => elem != this.hov)
			this.c_mode = this.mode[Math.floor((Math.random() * 3) + 1)-1];
		}
	}
</script>


<style lang="scss" scoped>
	.userinfo {
		text-align: left;
		font-size: small;
		margin-top: 2rem;
	}

	#particles-js {	
		top:0;
		left: 0;
		height: 100%;
		width: 100%;
		background-size: cover;
		position: absolute;
		z-index: -1;
	}

	.content {
		background-color: #f3f1e9;
		height: 100vh;

		.row {
			margin: unset;
		}

		.display {
			z-index: 999;
			margin: 0 auto;
		}

		.auth {
			width: 400px;
			margin: 0 auto;
			background-color: #fff;
			text-align: center;
			padding: 2rem;
			border: 1px solid #ced4da;
			border-radius: 0.25rem;
			transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
			
			.header {
				font-size: 200%;
				font-weight: bolder;
				margin-bottom: 2rem;
			}

			.extra {
				font-size: small;
			}

			.login_error {
				padding: 1rem;
				color: red;
				font-weight: bolder;
			}
		}

		.logo {
			width: 400px;
			margin: 0 auto;
			text-align: center;
		}

		@media only screen and (max-width: 433px) {
			.auth, .logo {
		   		width: 280px;
		   	}
		}

		img {
			margin: 2rem;
		}

		input {
			margin-bottom: 1rem;
			display: block;
			width: 100%;
			padding: 0.375rem 0.75rem;
			font-size: 1rem;
			line-height: 1.5;
			color: #495057;
			background-color: #fff;
			background-clip: padding-box;
			border: 1px solid #ced4da;
			border-radius: 0.25rem;
			transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;

			&[type=submit] {
				background-color: #34495e;
				color: #fff;
			}
		}

	}
</style>