"use strict";

interface Program{
    id:string
}
interface ProgramCommand{
    id:string,
    role_program_commands:Array<any>
}

import {PermissionState} from "./PermissionState.ts"

export class RoleProgramCommandWrapper {

    private _hadPermission:boolean;
    private _permission:boolean;

    constructor(private _programCommand:ProgramCommand) {        
        this._hadPermission = (this._programCommand.role_program_commands != undefined && this._programCommand.role_program_commands.length>0);
        this._permission=this._hadPermission
    }
    
    set programCommand(programCommand:ProgramCommand){
        this._programCommand=programCommand;
    }
    get programCommand():ProgramCommand{
        return this._programCommand;
    }
    set permission(permission:boolean){
        this._permission=permission;
    }
    get permission():boolean{
        return this._permission;
    }
    set hadPermission(hadPermission:boolean){
        this._permission=hadPermission;
    }
    get hadPermission():boolean{
        return this._hadPermission;
    }
    permissionState():PermissionState {
        if(this._hadPermission && !this._permission)
            return PermissionState.Revoked;
        else if(!this._hadPermission && this._permission)
            return PermissionState.Granted;
        return PermissionState.Retained;
    }

}