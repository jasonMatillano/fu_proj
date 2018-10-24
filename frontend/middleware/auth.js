import { Token } from "~/assets/ts/memory.ts"

export default function ({ store, redirect, beforeNuxtRender, req  }) {
	let token = ""
	let h = "", getCookie = (e) => {
		token = e.split("=")[1] || null
	}

	if (process.server) {
		h = req.headers.cookie
    	if(h != undefined)
        	h.split(";").filter(e => e.match("_US")).forEach(getCookie)

    	if(token) {
			store.state.fujiko = Token.decode(token)
	    	store.state.authenticated = true
	    	store.state.token = token
	    } else {
	    	store.state.authenticated = false
	    }
    } else {
	    if(token) {
	    	store.commit("SET_TOKEN", token)
	    }
    }

	if (!store.state.authenticated) {
		return redirect('/')
	}
}