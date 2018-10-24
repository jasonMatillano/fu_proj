/**
 * 
 */
package w.fujiko.service.repo.users;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.users.RoleProgramCommandDao;
import w.fujiko.model.masters.users.RoleProgramCommandPk;
import w.fujiko.model.masters.users.RoleProgramCommand;
import w.fujiko.service.users.RoleProgramCommandService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class RoleProgramCommandServiceRepo 
		extends UniversalServiceRepo<RoleProgramCommand, RoleProgramCommandDao, RoleProgramCommandPk> 
		implements RoleProgramCommandService {}
