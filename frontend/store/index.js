import { Utils } from "~/assets/ts/Utils.ts"
import { Fujiko } from "~/assets/ts/interface.ts"
import { Token } from "~/assets/ts/memory.ts"
import axios from 'axios'
import qs from 'querystring';

const modAxios = axios.create({
  baseURL: 'http://192.168.99.100:8080/fujiko',
});


export const state = () => ({
  locales: ['en', 'ja'],
  locale: 'en',
  token: '',
  authenticated: false,
  fujiko: Fujiko
})

export const mutations = {
  SET_LANG(state, locale) {
    if (state.locales.indexOf(locale) !== -1) {
      state.locale = locale
    }
  },

  SET_TOKEN(state, token) {
    document.cookie="_US="+token
    state.fujiko = Token.decode(token)
    state.token = token
    state.authenticated = true
  },

  REMOVE_TOKEN(state) {
    document.cookie ='_US=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    state.token = ''
    state.authenticated = false
  }
}

export const actions = {
  async logout({ commit }) {
    commit('REMOVE_TOKEN');
    return true;
  },
	async login({ commit, state }, data) {
    return modAxios.post('/security/login-processing', qs.stringify(data), {
        headers: { 
          'Content-Type': 'application/x-www-form-urlencoded' ,
        }
    }).then(response => {
      let auth = response.data,
          successful = false;

      if(auth.authenticated) {
        commit('SET_TOKEN', auth.token)
        successful = true
      }

      return { ok: true, response: successful }
    }).catch(error => {
      return { ok: false, response: error }
    }) 
	}
}