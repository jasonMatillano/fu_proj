package w.fujiko.api.users;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Response;

import w.fujiko.model.masters.users.RoleProgramCommand;
import w.fujiko.service.users.RoleProgramCommandService;
import w.fujiko.model.masters.users.RoleProgramCommandPk;
import w.fujiko.model.wrappers.RoleProgramCommandWrapper;

@RestController
@RequestMapping("/api/role/program/command/")
public class RoleProgramCommandApi 
    extends Api<RoleProgramCommandService,RoleProgramCommand,RoleProgramCommandPk> {

        @Autowired
        RoleProgramCommandService roleProgramCommandService;

        @PostMapping("/grantprogramcommandroles")
        public @ResponseBody ResponseEntity<?> saveAll(@Valid @RequestBody RoleProgramCommandWrapper wrapper, Errors error){
            if(error.hasErrors()) 
                return ResponseEntity.badRequest().body(error.getAllErrors());

            try {
                List<Response> responses=new ArrayList<>();
                for(RoleProgramCommand roleProgramCommand : wrapper.getRole_program_commands()){
                    responses.addAll(service.saveOrUpdate(roleProgramCommand));
                }
                
                return ResponseEntity.ok().body(responses);
            } catch(Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
            }
        }

        @DeleteMapping("/revokeprogramcommandroles")
        public @ResponseBody ResponseEntity<?> deleteAll(@Valid @RequestBody RoleProgramCommandWrapper wrapper, Errors error){
            if(error.hasErrors()) 
                return ResponseEntity.badRequest().body(error.getAllErrors());
                        
            try {
                List<Response> responses=new ArrayList<>();
                for(RoleProgramCommand roleProgramCommand : wrapper.getRole_program_commands()){
                    responses.addAll(service.delete(roleProgramCommand));
                }
                
                return ResponseEntity.ok().body(responses);
            } catch(Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
            }
        }

    }