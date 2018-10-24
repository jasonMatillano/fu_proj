"use strict";

export interface KeyPressable {
    addKeyPressListener(key:number,onKeyPress:(this:void,keyCode:any)=>void);
}