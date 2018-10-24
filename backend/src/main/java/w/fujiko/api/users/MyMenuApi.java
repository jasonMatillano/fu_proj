package w.fujiko.api.users;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.model.masters.users.MyMenu;
import w.fujiko.service.users.MyMenuService;

@RestController
@RequestMapping("/api/mymenu")
public class MyMenuApi 
	extends Api<MyMenuService,MyMenu,Integer> {

		@GetMapping("/get/{userid}/")
		public @ResponseBody List<MyMenu> getCustomizeMenuOfUser(@PathVariable(value="userid") final Integer userid) {
			return service.getCustomizeMenuOfUser(userid);
		}
}