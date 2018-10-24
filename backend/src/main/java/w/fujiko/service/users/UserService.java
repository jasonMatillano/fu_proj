/**
 * 
 */
package w.fujiko.service.users;


import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.users.User;

/**
 * @author Try-Parser
 *
 */
@Service
public interface UserService extends UniversalCrud<User,Integer> {
	public Optional<User> getByUserId(Integer id);
	public Optional<User> getByName(String email);
}
