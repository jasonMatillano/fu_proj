'use strict'

export class Utils {
	private static _instance: Utils;
	private localIPs: {} = {};

	constructor() {}

	public static get getInstance() {
		return this._instance || (this._instance = new this());
	}

	getUserPref() {
		let user_pref = navigator.userAgent,
			pref = {
				os: "",
				bit: "",
				position: {},
				position_error: {},
				browser: ""
			},
	  		spl_pref = user_pref.split(/[(^)]/)

	  	pref.os = spl_pref[1].split(";")[0]
	  	pref.bit = spl_pref[1].split(";")[2]
		
	    if (user_pref.search("MSIE") != -1) {
     	    pref.browser = "IE"
	    } else if (user_pref.search("Chrome") != -1) {
	        pref.browser = "Chrome"
	    } else if (user_pref.search("Firefox") != -1) {
	        pref.browser = "Firefox"
	    } else if (user_pref.search("Safari") != -1) {
	        pref.browser = "Safari"
	    } else if (user_pref.search("Opera") != -1) {
	    	pref.browser = "Opera"
	    } else {
	    	pref.browser = "Unknown"
	    }

	    return (_ => {
			navigator.geolocation.getCurrentPosition(pos => {
			    pos["map"] = "http://maps.googleapis.com/maps/api/staticmap?center="+pos.coords.latitude + "," +pos.coords.longitude +"&zoom=14&size=400x300&key=AIzaSyBu-916DdpKAjTmJNIgngS6HL_kDIKU0aU";
				pref.position = pos 
				_(pref)
			}, error => {
				pref.position_error = error
				_(pref)
			});
	    })
	}

	getIp(cb) {
		let myPeerConnection = (<any>window).RTCPeerConnection || (<any>window).mozRTCPeerConnection || (<any>window).webkitRTCPeerConnection;
		let pc = new myPeerConnection({
	        iceServers: []
	    }),
	    noop = function() {},
	    ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
	    key,
	    self = this;

		let iterateIP = (ip => {
	        if (!self.localIPs[ip]) cb(ip)
	        self.localIPs[ip] = true;
	    })

	    pc.createDataChannel("");

	    pc.createOffer().then(function(sdp) {
	        sdp.sdp.split('\n').forEach(function(line) {
	            if (line.indexOf('candidate') < 0) return;
	            line.match(ipRegex).forEach(iterateIP);
	        });
	        
	        pc.setLocalDescription(sdp, noop, noop);
	    }).catch(function(reason) {
	        // An error occurred, so handle the failure to connect
	    });

	    pc.onicecandidate = function(ice) {
	        if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
	        ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
	    };
	}

	setLocale(lang: String) {
		document.cookie = "_locale_="+lang
	}
}
