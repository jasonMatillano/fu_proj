/**
 * 
 */
package w.fujiko.service.repo.users;



import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.users.UserDao;
import w.fujiko.model.masters.users.User;
import w.fujiko.service.users.UserService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class UserServiceRepo 
		extends UniversalServiceRepo<User, UserDao, Integer> 
		implements UserService {
	
	@Autowired UserDao userDao;

	@Override
	public Optional<User> getByUserId(Integer id) {
		return userDao.getByUserId(id);
	}
	public Optional<User> getByName(String email){
		return userDao.getByName(email);
	}
}
