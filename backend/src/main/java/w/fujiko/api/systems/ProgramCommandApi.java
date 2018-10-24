package w.fujiko.api.systems;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.model.masters.systems.ProgramCommand;
import w.fujiko.service.systems.ProgramCommandService;

@RestController
@RequestMapping("/api/program/command/")
public class ProgramCommandApi 
	extends Api<ProgramCommandService,ProgramCommand,Integer> {

		@GetMapping("/grantedcommands/{id}")
		public @ResponseBody List<ProgramCategory> getGrantedProgramCommandByUser(@PathVariable(value="id") final Integer userId) {
			List<ProgramCategory> grantedCommands = service.getGrantedProgramCommandByUser(userId);			
			System.out.print("GrantedCommands: "+grantedCommands);
			return grantedCommands;
		}

	}