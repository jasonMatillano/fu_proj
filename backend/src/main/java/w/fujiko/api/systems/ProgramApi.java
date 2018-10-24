package w.fujiko.api.systems;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.service.systems.ProgramService;

@RestController
@RequestMapping("/api/program")
public class ProgramApi 
	extends Api<ProgramService,Program,String> {

		@GetMapping("/grantedprograms/{userid}/{categoryid}")
		public @ResponseBody List<Program> getGrantedProgramInCategoryOfUser(@PathVariable(value="userid") final Integer userid,@PathVariable(value="categoryid") final char categoryid) {
			return service.getGrantedProgramInCategoryOfUser(userid,categoryid);
		}
}