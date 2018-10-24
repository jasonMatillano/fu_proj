package w.fujiko.model.wrappers;

import java.util.List;
import w.fujiko.model.masters.users.RoleProgramCommand;

public class RoleProgramCommandWrapper{

    private List<RoleProgramCommand> role_program_commands;

    public RoleProgramCommandWrapper(){

    }
    
    public List<RoleProgramCommand> getRole_program_commands(){
        return this.role_program_commands;
    }
    public void setRole_program_commands(List<RoleProgramCommand> roleProgramCommands){
        this.role_program_commands = roleProgramCommands;
    }
}
