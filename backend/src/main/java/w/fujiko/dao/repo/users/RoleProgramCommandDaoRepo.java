package w.fujiko.dao.repo.users;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import w.fujiko.dao.users.RoleProgramCommandDao;
import w.fujiko.model.masters.users.RoleProgramCommandPk;
import w.fujiko.model.masters.users.RoleProgramCommand;
import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class RoleProgramCommandDaoRepo 
	extends UniversalCrudRepo<RoleProgramCommand ,RoleProgramCommandPk>
	implements RoleProgramCommandDao {

		public RoleProgramCommandDaoRepo() {
			super();
			type = RoleProgramCommand.class;
		}
}
