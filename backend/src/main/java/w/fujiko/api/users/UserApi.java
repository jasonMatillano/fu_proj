package w.fujiko.api.users;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Response;
import w.fujiko.model.masters.users.User;
import w.fujiko.service.users.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApi extends Api<UserService, User, Integer> {
	
	@Autowired 
	private UserService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PatchMapping("/")
	public @ResponseBody ResponseEntity<?> saveOrUpdate(@Valid @RequestBody User t, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Integer id = t.getId();
			var user = service.getByUserId(id);
			String password = user.map(e->e.getPassword()).orElse("");
			if(id != null) {
				
				
				if(t.getPassword() == null) {
					t.setPassword(password);
				}else {
					t.setPassword(passwordEncoder.encode(t.getPassword()));
				}
			}else {
				t.setPassword(passwordEncoder.encode(t.getPassword()));
			}
			
			System.out.println("Password: "+password);
			
			
			List<Response> response = service.saveOrUpdate(t);
			return ResponseEntity.ok().body(response);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body("{ status: 500, message: bad data, code: BD }");
		}
	}
}
