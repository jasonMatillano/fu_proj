'use strict'

import { Keys } from './keys.ts'

export class Keyboard {
	private static _instance: Keyboard;
	private que_proc: {};

	constructor() {
		this.que_proc = {}
		let self = this
		window.addEventListener('keydown', function(evt) {
			if(self.check(Keys[evt.keyCode]))
				evt.preventDefault()
			self.pressed(evt.keyCode, evt)
		})
	}

	public static get keys () { 
		return Keys 
	}

    public static get getInstance() {
        return this._instance || (this._instance = new this());
	}

	addPress (key: Keys, cb) {
        this.que_proc[key] = this.que_proc[key] || [];
        this.que_proc[key].push(cb);
	}

	removePress (key, cb) {
		if (this.que_proc[key]) {
            for (let i = this.que_proc[key].length; i--;) {
                if (this.que_proc[key][i] === cb) {
                    this.que_proc[key].splice(i, 1);
                    break;
                }
            };
        }
	}

	pressed (key, data) {
		if (this.que_proc[key])
            this.que_proc[key].forEach(fn => fn(data));
	}

	private check(code) {
		return Object.keys(this.que_proc).find(elem => {
			return elem == Keys[code]
		})
	}	
}

export class Listener extends Keyboard {
	private static _instance_l: Listener;
	private counter: number = 1;

	constructor() {
		super()
	}

	public static get getInstance() {
		return this._instance_l || (this._instance_l = new this());
	}

	setComp(obj) {
		obj.$refs[1].$children[0].focus()
		this.listen(obj)
	}

	private listen (obj) {
		let self = this, len = Object.keys(obj.$refs).length, ltnr;

		ltnr = this.addPress(Keys.ENTER, function(e) {
			if(self.counter < len) 
				self.counter += 1;
			else
				self.counter = 1;
			
			obj.$refs[self.counter].$children[0].focus()
		})
	}
}